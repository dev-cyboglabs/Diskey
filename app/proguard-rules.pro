# Diskey ProGuard Rules

# Keep data models
-keep class com.cyboglabs.diskey.domain.model.** { *; }
-keep class com.cyboglabs.diskey.data.db.entity.** { *; }

# Nordic BLE
-keep class no.nordicsemi.android.ble.** { *; }

# Gson
-keepattributes Signature
-keepattributes *Annotation*
-dontwarn sun.misc.**
-keep class com.google.gson.** { *; }
-keep class * implements com.google.gson.TypeAdapterFactory
-keep class * implements com.google.gson.JsonSerializer
-keep class * implements com.google.gson.JsonDeserializer

# Moshi
-keep class com.squareup.moshi.** { *; }
-keep @com.squareup.moshi.JsonQualifier interface *

# Room
-keep class * extends androidx.room.RoomDatabase
-keep @androidx.room.Entity class *
-dontwarn androidx.room.paging.**

# Hilt
-keep class dagger.hilt.** { *; }
-keep class javax.inject.** { *; }
-keep class * extends dagger.hilt.android.HiltAndroidApp { *; }

# Kotlin
-keep class kotlin.** { *; }
-keep class kotlinx.coroutines.** { *; }

# FFmpegKit
-keep class com.arthenica.ffmpegkit.** { *; }

# ExoPlayer
-keep class androidx.media3.** { *; }
-dontwarn androidx.media3.**

# WorkManager
-keep class androidx.work.** { *; }

# General
-keepattributes SourceFile,LineNumberTable
-renamesourcefileattribute SourceFile
