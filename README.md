# Diskey — T240 BLE+WiFi Audio Recorder App

Production-quality Android application for the T240 custom audio recorder device. Supports BLE control commands, real-time audio streaming, WiFi TCP file transfer, OPUS playback, and OTA firmware updates.

---

## Tech Stack

| Layer | Technology |
|-------|-----------|
| Language | Kotlin |
| UI | Jetpack Compose + Material 3 |
| Architecture | MVVM Clean Architecture |
| DI | Hilt |
| Async | Kotlin Coroutines + Flow |
| BLE | Nordic BLE Library |
| Audio Playback | ExoPlayer / Media3 |
| OPUS Conversion | FFmpegKit |
| Database | Room |
| Preferences | DataStore |
| Navigation | Navigation Compose |
| Background Work | WorkManager |
| Logging | Timber |

---

## Project Structure

```
app/src/main/java/com/cyboglabs/diskey/
├── DisKeyApplication.kt          # Hilt application class
├── ble/
│   ├── BleConstants.kt           # UUIDs, timeouts, command codes
│   ├── BleDeviceManager.kt       # Nordic BleManager subclass
│   ├── BleConnectionManager.kt   # Handshake + lifecycle orchestration
│   ├── BleService.kt             # Foreground BLE service
│   ├── protocol/
│   │   ├── PacketBuilder.kt      # Binary command constructors
│   │   └── PacketParser.kt       # Raw bytes → typed BlePacket
│   └── scanner/
│       └── BleScanner.kt         # Flow-based BLE scan
├── wifi/
│   ├── WifiConstants.kt          # TCP host, port, heartbeat
│   ├── WifiTransferManager.kt    # TCP socket client + heartbeat
│   ├── WifiService.kt            # Foreground WiFi service
│   └── protocol/
│       ├── WifiPacket.kt         # Data class + command constants
│       └── WifiPacketSerializer.kt # MeChoWifi frame encode/decode
├── audio/
│   ├── OpusFileManager.kt        # Packet buffer + file reconstruction
│   ├── OpusConverter.kt          # OPUS → WAV via FFmpegKit
│   ├── AudioDownloadManager.kt   # Download queue + BLE receive
│   ├── AudioDownloadService.kt   # Foreground download service
│   └── AudioPlaybackService.kt   # Media3 MediaSessionService
├── ota/
│   ├── OtaConstants.kt
│   └── OtaManager.kt             # OTA firmware transfer
├── data/
│   ├── db/
│   │   ├── AppDatabase.kt
│   │   ├── dao/                  # DeviceDao, AudioFileDao, etc.
│   │   └── entity/               # Room entities
│   ├── datastore/
│   │   └── AppPreferences.kt     # DataStore preferences
│   └── repository/               # Repository implementations
├── domain/
│   ├── model/                    # Pure domain models
│   └── repository/               # Repository interfaces
├── presentation/
│   ├── MainActivity.kt
│   ├── navigation/Navigation.kt  # NavHost + Screen routes
│   ├── theme/                    # Material 3 theme, colors, typography
│   ├── splash/SplashScreen.kt
│   ├── scan/                     # BLE device scanner
│   ├── dashboard/                # Home dashboard
│   ├── files/                    # File browser + download
│   ├── player/                   # OPUS audio player
│   ├── settings/                 # App settings
│   ├── ota/                      # OTA update
│   └── debug/                    # Debug console + raw TX
├── di/
│   ├── AppModule.kt              # Repositories + Bluetooth adapter
│   └── DatabaseModule.kt         # Room DB + DAOs
├── workers/
│   ├── ReconnectWorker.kt        # Auto-reconnect on boot/connectivity
│   └── DownloadWorker.kt         # Background download trigger
└── utils/
    ├── CrcUtils.kt               # CRC-16/CCITT-FALSE
    ├── Extensions.kt             # Kotlin extensions
    ├── PermissionUtils.kt        # Android 12+ BLE permissions
    └── FileUtils.kt              # Scoped storage helpers
```

---

## Setup Instructions

### 1. Prerequisites

- Android Studio Ladybug (2024.2+)
- JDK 17
- Android SDK 35
- Physical Android device for BLE testing (emulator has no BLE)

### 2. Clone and Open

```bash
cd /Users/KABILAN/Desktop
open -a "Android Studio" Diskey/
```

### 3. Add Launcher Icons

Place your icons in:
- `app/src/main/res/mipmap-*/ic_launcher.png`
- `app/src/main/res/mipmap-*/ic_launcher_round.png`

Or use Android Studio's **Image Asset Studio** (right-click `res/` → New → Image Asset).

### 4. Build

```bash
./gradlew assembleDebug
```

### 5. Install

```bash
./gradlew installDebug
```

---

## BLE Protocol Documentation

### Service & Characteristics

| Role | UUID |
|------|------|
| Service | `00001910-0000-1000-8000-00805f9b34fb` |
| Notify (RX) | `00001911-0000-1000-8000-00805f9b34fb` |
| Write (TX) | `00001912-0000-1000-8000-00805f9b34fb` |

Write characteristic uses **WRITE_WITHOUT_RESPONSE** for maximum throughput.

### Command Packet Format

```
[type: 1B][cmd_hi: 1B][cmd_lo: 1B][payload: 0–N bytes]
```

| Command | Bytes |
|---------|-------|
| Start Recording | `01 00 14` |
| Stop Recording | `01 00 17` |
| Pause | `01 00 15` |
| Resume | `01 00 16` |
| Battery Level | `01 00 09` |
| Enable WiFi | `01 00 0A` |
| Get File List | `01 00 1B` |
| Download File | `01 00 1C [filename_utf8]` |

### Audio Data Packet Format

```
[type: 1B][cmd_hi: 1B][cmd_lo: 1B][index_hi: 1B][index_lo: 1B][audio_data: N bytes]
```

Packets are collected in a `TreeMap<Int, ByteArray>`, ordered by index, then concatenated to reconstruct the OPUS file.

### File Complete Packet

```
01 1D 00 [crc_lo: 1B] [crc_hi: 1B]
```

CRC-16/CCITT-FALSE of the complete assembled file bytes (little-endian).

---

## Handshake Sequence

```
Device → App:  01 01 00 00 + JSON{"uuid":"<device-uuid>"}
App → Device:  01 01 00 01 + JSON{"time":<epoch>,"uuid":"<app-uuid>"}
Device → App:  01 01 00 02 00 + JSON{...}   ← success
```

The device UUID is persisted in DataStore. Subsequent connections skip re-authentication if the stored UUID matches.

---

## WiFi Transfer Protocol

### Connection Flow

```
1. App sends BLE command: 01 00 0A (Enable WiFi)
2. Device creates hotspot:
   SSID: T240(WIFI)
   Password: first 8 characters of device UUID
3. App connects to hotspot
4. App opens TCP socket: 192.168.1.1:32769
5. App sends heartbeat: FF 00 every 3 seconds
```

### WiFi Packet Frame

```
[header: "MeChoWifiStart" 14B]
[command: 2B LE]
[sequence: 4B LE]
[data_length: 4B LE]
[crc: 2B LE CRC-16/CCITT-FALSE of data]
[data: N bytes]
[tail: "MeChoWifiEnd" 12B]
```

---

## OTA Update Flow

```
App → Device:  A0 00 [file_size: 4B] [crc: 4B]   ← OTA Start
App → Device:  A2 00 [index: 2B] [data: 512B]     ← OTA Packet (repeat)
App → Device:  A5 00                                ← OTA End
Device → App:  A4 00 [bytes_received: 4B]          ← Progress
Device → App:  A5 00                                ← Complete
```

---

## CRC Algorithm

**CRC-16/CCITT-FALSE**

- Polynomial: `0x1021`
- Init: `0xFFFF`
- Reflected input: No
- Reflected output: No
- XOR out: `0x0000`

See `utils/CrcUtils.kt` for implementation and test vectors.

---

## BLE Connection State Machine

```
DISCONNECTED
    │
    ▼ startScan()
SCANNING
    │
    ▼ connect(address)
CONNECTING
    │
    ▼ gatt connected
DISCOVERING_SERVICES
    │
    ▼ services found
ENABLING_NOTIFICATIONS
    │
    ▼ notifications enabled
HANDSHAKING
    │
    ▼ handshake complete
CONNECTED ◄─────────────────────────┐
    │                                │
    │ disconnect / error             │ reconnect()
    ▼                                │
RECONNECTING ──────────────────────►┘
    │ max retries exceeded
    ▼
ERROR
```

---

## Architecture Notes

- **Repository pattern** — ViewModels never access DAOs directly; all data flows through repository interfaces.
- **Single source of truth** — BLE `ConnectionState` and inbound packets flow as `StateFlow`/`SharedFlow` from `BleConnectionManager`.
- **Packet pipeline** — `BleDeviceManager` (Nordic) → `PacketParser` → `BleConnectionManager.packets` SharedFlow → collected by AudioDownloadManager, FileBrowserViewModel, DashboardViewModel simultaneously.
- **Foreground services** — BLE, download, WiFi, and playback each have dedicated foreground services to prevent OS from killing background work.
- **WorkManager** — Used only for opportunistic reconnect on boot/connectivity change; not for real-time work.

---

## Permissions Required

### Android 12+ (API 31+)
- `BLUETOOTH_SCAN` (with `neverForLocation`)
- `BLUETOOTH_CONNECT`
- `BLUETOOTH_ADVERTISE`

### Android < 12 (API 26–30)
- `BLUETOOTH`
- `BLUETOOTH_ADMIN`
- `ACCESS_FINE_LOCATION`

### All versions
- `INTERNET` (WiFi TCP)
- `ACCESS_WIFI_STATE`, `CHANGE_WIFI_STATE`
- `FOREGROUND_SERVICE` + type-specific variants
- `POST_NOTIFICATIONS` (API 33+)

---

## Running Tests

```bash
./gradlew test                  # Unit tests
./gradlew connectedAndroidTest  # Instrumented tests (requires device)
```

Unit tests cover:
- `CrcUtils` — all utility functions and edge cases
- `PacketBuilder` — all command byte sequences
- `WifiPacketSerializer` — encode/decode round-trip and corruption detection

---

## Known Limitations / TODO

- [ ] Real-time audio streaming (live OPUS buffering for playback during recording)
- [ ] Waveform visualization (replace random-height bars with actual OPUS amplitude data)
- [ ] WiFi connection manager UI (guide user through hotspot connection steps)
- [ ] Multi-device support (currently assumes single paired device)
- [ ] Device rename command (send via BLE settings characteristic)
- [ ] Export logs feature (write Timber log buffer to file)
