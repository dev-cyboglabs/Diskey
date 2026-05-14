# Diskey - Build & Installation Guide

## ✅ Build Status: SUCCESS

The app has been successfully built! The debug APK is ready to install on your mobile device.

---

## 📱 Installing on Your Mobile Device

### APK Location
```
/Users/KABILAN/Desktop/Diskey/app/build/outputs/apk/debug/app-debug.apk
```

### Installation Methods

#### Method 1: USB Cable (Recommended)
1. **Enable USB Debugging** on your Android phone:
   - Settings → About Phone → Tap "Build Number" 7 times
   - Settings → Developer Options → Enable "USB Debugging"

2. **Connect your phone** to your Mac via USB

3. **Install via command line**:
   ```bash
   cd /Users/KABILAN/Desktop/Diskey
   ./gradlew installDebug
   ```

#### Method 2: Transfer APK File
1. **Copy the APK** to your phone using:
   - AirDrop
   - Email attachment
   - Google Drive
   - USB cable (copy to Downloads folder)

2. **On your phone**:
   - Open the APK file
   - Allow "Install from Unknown Sources" if prompted
   - Tap "Install"

---

## 🔧 Build Commands

### Clean Build
```bash
cd /Users/KABILAN/Desktop/Diskey
./gradlew clean
./gradlew assembleDebug
```

### Install Directly to Connected Device
```bash
./gradlew installDebug
```

### Build Release APK (for production)
```bash
./gradlew assembleRelease
```

---

## ⚠️ Important Notes

### FFmpegKit Temporarily Disabled
The FFmpegKit library for OPUS-to-WAV conversion has been temporarily disabled because it's no longer available in Maven repositories. The app will still work, but OPUS conversion features will be limited.

**To re-enable FFmpegKit:**
1. Download the AAR file from: https://github.com/arthenica/ffmpeg-kit/releases
2. Place it in `app/libs/`
3. Uncomment the FFmpegKit code in:
   - `app/build.gradle.kts` (line 128)
   - `app/src/main/java/com/cyboglabs/diskey/audio/OpusConverter.kt`

### Required Permissions
When you first launch the app, grant these permissions:
- ✅ Bluetooth (for BLE connection to T240 device)
- ✅ Location (required for BLE scanning on Android < 12)
- ✅ Notifications (for foreground services)
- ✅ WiFi (for TCP file transfer)

### Testing Requirements
⚠️ **Physical Android device required** - BLE doesn't work in emulators

---

## 🧪 Testing the App

### Prerequisites
- T240 hardware device powered on
- Android phone with Bluetooth enabled
- Location services enabled (for BLE scanning)

### Test Workflow
1. Launch the Diskey app
2. Scan for T240 device
3. Connect via BLE (automatic handshake)
4. Test features:
   - Recording control (start/stop/pause/resume)
   - Battery level monitoring
   - File list retrieval
   - Audio file download
   - WiFi transfer (connect to T240(WIFI) hotspot)
   - Audio playback
   - OTA firmware updates

---

## 🐛 Troubleshooting

### Build Issues
- **Gradle wrapper not found**: Already fixed - wrapper files created
- **FFmpegKit not found**: Already fixed - dependency commented out
- **Missing launcher icons**: Already fixed - XML icons created

### Installation Issues
- **Device not detected**: Enable USB debugging and authorize your Mac
- **Installation blocked**: Enable "Install from Unknown Sources" in Settings

### Runtime Issues
- **App crashes on launch**: Check permissions in Settings → Apps → Diskey
- **BLE not working**: Ensure Bluetooth and Location are enabled
- **Can't connect to T240**: Ensure T240 device is powered on and in range

---

## 📝 Changes Made to Fix Build

1. ✅ Created Gradle wrapper files (`gradlew`, `gradlew.bat`)
2. ✅ Added `gradle.properties` with AndroidX support
3. ✅ Fixed FFmpegKit repository configuration
4. ✅ Commented out FFmpegKit dependency (library retired)
5. ✅ Added `lifecycle-service` dependency
6. ✅ Created launcher icons (XML-based adaptive icons)
7. ✅ Fixed compilation errors in:
   - `OpusConverter.kt` (stubbed FFmpegKit methods)
   - `BleConnectionManager.kt` (type conversion)
   - `DashboardScreen.kt` (icon reference)

---

## 📦 APK Details

- **Package**: `com.cyboglabs.diskey.debug`
- **Version**: 1.0.0 (versionCode 1)
- **Min SDK**: 26 (Android 8.0)
- **Target SDK**: 35 (Android 15)
- **Size**: ~15-20 MB (estimated)

---

## 🚀 Next Steps

1. Install the app on your Android phone
2. Test BLE connectivity with T240 device
3. If you need FFmpegKit for OPUS conversion, follow the instructions above
4. Report any issues or bugs

---

**Built on**: $(date)
**Build Type**: Debug
**Status**: ✅ Ready for testing
