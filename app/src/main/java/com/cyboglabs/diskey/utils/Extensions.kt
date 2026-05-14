package com.cyboglabs.diskey.utils

import android.content.Context
import android.os.Build
import android.widget.Toast
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun ByteArray.toHexString(): String = joinToString(" ") { "%02X".format(it) }

fun ByteArray.toUnsignedInt(offset: Int = 0, length: Int = 2): Int {
    var result = 0
    for (i in 0 until length) {
        result = result or ((this[offset + i].toInt() and 0xFF) shl (8 * i))
    }
    return result
}

fun Long.toFormattedDate(pattern: String = "yyyy-MM-dd HH:mm:ss"): String {
    val sdf = SimpleDateFormat(pattern, Locale.getDefault())
    return sdf.format(Date(this * 1000L))
}

fun Long.toRelativeTimeString(): String {
    val nowMs = System.currentTimeMillis()
    val diffMs = nowMs - (this * 1000L)
    return when {
        diffMs < 60_000 -> "just now"
        diffMs < 3_600_000 -> "${diffMs / 60_000}m ago"
        diffMs < 86_400_000 -> "${diffMs / 3_600_000}h ago"
        else -> "${diffMs / 86_400_000}d ago"
    }
}

fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun <T> Flow<T>.catchAndLog(tag: String = "Flow"): Flow<T> = catch { e ->
    Timber.e(e, "$tag: unhandled error in flow")
}

fun Int.toRssiDescription(): String = when {
    this >= -60 -> "Excellent"
    this >= -70 -> "Good"
    this >= -80 -> "Fair"
    else -> "Poor"
}

fun Boolean.toOnOff(): String = if (this) "On" else "Off"

val isAtLeastApi31: Boolean get() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
val isAtLeastApi33: Boolean get() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU
