Your device exposes a custom protocol over **BLE** and **Wi-Fi**.
The overall workflow is:

1. Android app connects over BLE
2. Perform handshake + pairing
3. Control recording over BLE
4. Get file list
5. Transfer audio files
6. Play `.opus` audio on Android
7. Optionally use Wi-Fi for faster transfer

Here’s how the whole system works in practical Android terms.

---

# 1. Device Architecture

Your recorder device supports two communication channels:

| Channel          | Purpose                            |
| ---------------- | ---------------------------------- |
| BLE              | Control commands + real-time audio |
| Wi-Fi TCP Socket | Fast file transfer + OTA           |

---

# 2. BLE Information

## BLE Service

Use this service UUID:

```text id="uy9s6o"
00001910-0000-1000-8000-00805f9b34fb
```

## BLE Characteristics

### Write Characteristic

```text id="4tw3v2"
00001912-0000-1000-8000-00805f9b34fb
```

Properties:

* WRITE_WITHOUT_RESPONSE

### Notify Characteristic

```text id="o5gf0n"
00001911-0000-1000-8000-00805f9b34fb
```

Properties:

* NOTIFY

---

# 3. Android BLE Connection Flow

Your app should do this:

```text id="v6y6tq"
Scan Device
   ↓
Connect GATT
   ↓
Discover Services
   ↓
Enable NOTIFY
   ↓
Handshake
   ↓
Ready
```

---

# 4. Handshake Process

This is mandatory.

Without handshake the device disconnects.

---

## Step 1 — Enable Notifications

Enable notifications on:

```text id="ih87zs"
00001911-0000-1000-8000-00805f9b34fb
```

After enabling notifications:

The device sends:

```text id="0d4fh4"
0x01 0x01 0x00 0x00 + JSON
```

Example JSON:

```json id="jlwm2t"
{
  "uuid":"623d289d-0a37-5260-b0f1-976e9bc9ea4e"
}
```

Save this UUID.

---

## Step 2 — Send App UUID

You send:

```json id="n6d0ol"
{
  "time":1730254726,
  "uuid":"YOUR_APP_UUID"
}
```

Packet:

```text id="h3qb2d"
0x01 0x01 0x00 0x01 + json
```

---

## Step 3 — Device Returns Status

If successful:

```text id="2x4l8l"
0x01 0x01 0x00 0x02 0x00 + json
```

Now device is connected and authenticated.

---

# 5. Start Recording

Send:

```text id="11m54s"
0x01 0x14 0x00
```

The device replies:

```json id="l27klx"
{
  "file":"R20200101-000011.opus",
  "creat_time":1577808011
}
```

Then it starts streaming live OPUS frames over BLE.

---

# 6. Stop Recording

Send:

```text id="h5hcrf"
0x01 0x17 0x00
```

Device responds:

```text id="rwyjlwm"
0x01 0x17 0x00 crcL crcH
```

---

# 7. Get Audio File List

This is the important part for file playback/download.

Send:

```text id="k6w2z5"
0x01 0x1B 0x00
```

The device returns JSON objects like:

```json id="0z7jfw"
{
  "file":"R20200101-000013.opus",
  "size":7680,
  "creat_time":1577808013,
  "duration_ms":3000,
  "index":4
}
```

Store these in your Android app.

Display them in RecyclerView.

---

# 8. Download Audio File

To request one file:

```text id="n07o2i"
0x01 0x1C 0x00 "R20200101-000013.opus"
```

The device starts sending audio packets.

---

# 9. BLE Audio Packet Format

Each BLE packet:

| Field | Size    |
| ----- | ------- |
| type  | 1 byte  |
| cmd   | 2 bytes |
| index | 2 bytes |
| audio | N bytes |

Example:

```text id="jl2kpt"
[type][cmd][frameIndex][opusData]
```

---

# 10. Rebuild File on Android

You must:

1. Receive packets
2. Sort by frame index
3. Append opus bytes
4. Save into `.opus` file

Example:

```kotlin id="9g2g8e"
FileOutputStream(file, true).write(audioBytes)
```

When transfer completes:

Device sends:

```text id="6t88sp"
0x01 0x1D 0x00 crcL crcH
```

Now file is complete.

---

# 11. Playing OPUS Audio on Android

Android does NOT reliably play raw `.opus` streams directly unless wrapped correctly.

You have 3 options.

---

# Option A — Convert OPUS → WAV (Recommended)

Best approach.

Use:

* FFmpeg
* MobileFFmpeg

Example:

```bash id="qqgg4j"
ffmpeg -i input.opus output.wav
```

Then play WAV using:

* MediaPlayer
* ExoPlayer

---

# Option B — Use ExoPlayer

ExoPlayer supports OPUS inside OGG container.

If your file is valid OGG/OPUS:

```kotlin id="7k5fgu"
val player = ExoPlayer.Builder(context).build()
```

---

# Option C — Decode OPUS Manually

Advanced approach.

Use:

* libopus
* JNI wrapper

Usually unnecessary.

---

# 12. Recommended Android Architecture

## BLE Layer

Responsibilities:

* scan/connect
* notifications
* send commands

Use:

* `BluetoothGatt`
* `BluetoothGattCallback`

---

## Repository Layer

Responsibilities:

* handshake
* parse packets
* rebuild files

---

## Audio Layer

Responsibilities:

* playback
* conversion
* caching

Use:

* ExoPlayer
* FFmpegKit

---

# 13. Recommended Flow for Your App

## File Sync Mode

```text id="0lfwq0"
Connect BLE
   ↓
Handshake
   ↓
Get File List
   ↓
Select File
   ↓
Download OPUS
   ↓
Save File
   ↓
Convert to WAV
   ↓
Play Audio
```

---

# 14. BLE vs Wi-Fi

## BLE

Pros:

* easy connection
* low power

Cons:

* slow transfer
* max 320 bytes/frame

Good for:

* commands
* small audio
* live streaming

---

## Wi-Fi

Pros:

* very fast
* 40KB/frame

Cons:

* more setup

Good for:

* downloading recordings
* large files

---

# 15. Recommended Strategy

Use:

| Feature              | Technology |
| -------------------- | ---------- |
| Device control       | BLE        |
| Recording control    | BLE        |
| Live streaming       | BLE        |
| File list            | BLE        |
| Large audio download | Wi-Fi      |
| Playback             | ExoPlayer  |

---

# 16. Best Android Stack

Recommended:

| Purpose         | Library            |
| --------------- | ------------------ |
| BLE             | Nordic BLE Library |
| Audio Playback  | ExoPlayer          |
| OPUS Conversion | FFmpegKit          |
| JSON            | Gson / Moshi       |
| Coroutines      | Kotlin Coroutines  |

---

# 17. Critical Thing to Understand

The device is NOT exposing files like a USB drive.

You are implementing a custom protocol:

```text id="lv80nm"
Android App
    ↔ BLE Commands
Device Firmware
    ↔ SD Card Files
```

Your app must:

* send commands
* receive packets
* rebuild files manually

---

# 18. Simplest MVP You Can Build First

Start with:

### Phase 1

* BLE connect
* handshake
* battery level

### Phase 2

* get file list

### Phase 3

* download file

### Phase 4

* save `.opus`

### Phase 5

* playback

### Phase 6

* Wi-Fi transfer optimization

---

# 19. Most Important Commands

| Purpose         | Command     |
| --------------- | ----------- |
| Handshake       | `0x01 0x00` |
| Battery         | `0x09 0x00` |
| Start Recording | `0x14 0x00` |
| Stop Recording  | `0x17 0x00` |
| Get File List   | `0x1B 0x00` |
| Download File   | `0x1C 0x00` |
| File Complete   | `0x1D 0x00` |
| Enable WiFi     | `0x0A 0x00` |
| Disable WiFi    | `0x0B 0x00` |
