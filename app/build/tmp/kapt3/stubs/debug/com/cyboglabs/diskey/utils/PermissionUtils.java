package com.cyboglabs.diskey.utils;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import androidx.core.content.ContextCompat;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0011\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u00a2\u0006\u0002\u0010\u0006J\u0011\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u00a2\u0006\u0002\u0010\u0006J\u0011\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u00a2\u0006\u0002\u0010\u0006J!\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u00a2\u0006\u0002\u0010\u000eJ\u000e\u0010\u000f\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fJ\u0016\u0010\u0010\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\u0005\u00a8\u0006\u0012"}, d2 = {"Lcom/cyboglabs/diskey/utils/PermissionUtils;", "", "()V", "getRequiredBlePermissions", "", "", "()[Ljava/lang/String;", "getRequiredNotificationPermissions", "getRequiredStoragePermissions", "hasAllPermissions", "", "context", "Landroid/content/Context;", "permissions", "(Landroid/content/Context;[Ljava/lang/String;)Z", "hasBlePermissions", "hasPermission", "permission", "app_debug"})
public final class PermissionUtils {
    @org.jetbrains.annotations.NotNull()
    public static final com.cyboglabs.diskey.utils.PermissionUtils INSTANCE = null;
    
    private PermissionUtils() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String[] getRequiredBlePermissions() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String[] getRequiredNotificationPermissions() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String[] getRequiredStoragePermissions() {
        return null;
    }
    
    public final boolean hasPermission(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    java.lang.String permission) {
        return false;
    }
    
    public final boolean hasAllPermissions(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    java.lang.String[] permissions) {
        return false;
    }
    
    public final boolean hasBlePermissions(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return false;
    }
}