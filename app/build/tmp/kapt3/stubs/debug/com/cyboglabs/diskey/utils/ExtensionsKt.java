package com.cyboglabs.diskey.utils;

import android.content.Context;
import android.os.Build;
import android.widget.Toast;
import kotlinx.coroutines.flow.Flow;
import timber.log.Timber;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000<\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\u001a&\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00060\u0005\"\u0004\b\u0000\u0010\u0006*\b\u0012\u0004\u0012\u0002H\u00060\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b\u001a\u0012\u0010\t\u001a\u00020\n*\u00020\u000b2\u0006\u0010\f\u001a\u00020\b\u001a\u0014\u0010\r\u001a\u00020\b*\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\b\u001a\n\u0010\u0010\u001a\u00020\b*\u00020\u0011\u001a\n\u0010\u0012\u001a\u00020\b*\u00020\u0001\u001a\n\u0010\u0013\u001a\u00020\b*\u00020\u000e\u001a\n\u0010\u0014\u001a\u00020\b*\u00020\u0015\u001a\u001e\u0010\u0016\u001a\u00020\u0015*\u00020\u00112\b\b\u0002\u0010\u0017\u001a\u00020\u00152\b\b\u0002\u0010\u0018\u001a\u00020\u0015\"\u0011\u0010\u0000\u001a\u00020\u00018F\u00a2\u0006\u0006\u001a\u0004\b\u0000\u0010\u0002\"\u0011\u0010\u0003\u001a\u00020\u00018F\u00a2\u0006\u0006\u001a\u0004\b\u0003\u0010\u0002\u00a8\u0006\u0019"}, d2 = {"isAtLeastApi31", "", "()Z", "isAtLeastApi33", "catchAndLog", "Lkotlinx/coroutines/flow/Flow;", "T", "tag", "", "showToast", "", "Landroid/content/Context;", "message", "toFormattedDate", "", "pattern", "toHexString", "", "toOnOff", "toRelativeTimeString", "toRssiDescription", "", "toUnsignedInt", "offset", "length", "app_debug"})
public final class ExtensionsKt {
    
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String toHexString(@org.jetbrains.annotations.NotNull()
    byte[] $this$toHexString) {
        return null;
    }
    
    public static final int toUnsignedInt(@org.jetbrains.annotations.NotNull()
    byte[] $this$toUnsignedInt, int offset, int length) {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String toFormattedDate(long $this$toFormattedDate, @org.jetbrains.annotations.NotNull()
    java.lang.String pattern) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String toRelativeTimeString(long $this$toRelativeTimeString) {
        return null;
    }
    
    public static final void showToast(@org.jetbrains.annotations.NotNull()
    android.content.Context $this$showToast, @org.jetbrains.annotations.NotNull()
    java.lang.String message) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public static final <T extends java.lang.Object>kotlinx.coroutines.flow.Flow<T> catchAndLog(@org.jetbrains.annotations.NotNull()
    kotlinx.coroutines.flow.Flow<? extends T> $this$catchAndLog, @org.jetbrains.annotations.NotNull()
    java.lang.String tag) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String toRssiDescription(int $this$toRssiDescription) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String toOnOff(boolean $this$toOnOff) {
        return null;
    }
    
    public static final boolean isAtLeastApi31() {
        return false;
    }
    
    public static final boolean isAtLeastApi33() {
        return false;
    }
}