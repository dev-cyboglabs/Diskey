package com.cyboglabs.diskey.utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000\"\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u001a\u001c\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u0001\u001a\u001c\u0010\u0006\u001a\u00020\u0007*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u0007\u001a\u0014\u0010\b\u001a\u0004\u0018\u00010\u0004*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u0012\u0010\t\u001a\u00020\u0002*\u00020\u00042\u0006\u0010\n\u001a\u00020\u000b\u00a8\u0006\f"}, d2 = {"getIntOrDefault", "", "Lcom/google/gson/JsonObject;", "key", "", "default", "getLongOrDefault", "", "getStrOrNull", "parseJson", "gson", "Lcom/google/gson/Gson;", "app_debug"})
public final class JsonUtilsKt {
    
    /**
     * Parse a JSON string into a JsonObject; returns empty object on failure.
     */
    @org.jetbrains.annotations.NotNull()
    public static final com.google.gson.JsonObject parseJson(@org.jetbrains.annotations.NotNull()
    java.lang.String $this$parseJson, @org.jetbrains.annotations.NotNull()
    com.google.gson.Gson gson) {
        return null;
    }
    
    /**
     * Safe string get from JsonObject, returns null if key absent.
     */
    @org.jetbrains.annotations.Nullable()
    public static final java.lang.String getStrOrNull(@org.jetbrains.annotations.NotNull()
    com.google.gson.JsonObject $this$getStrOrNull, @org.jetbrains.annotations.NotNull()
    java.lang.String key) {
        return null;
    }
    
    /**
     * Safe long get from JsonObject, returns 0 if key absent.
     */
    public static final long getLongOrDefault(@org.jetbrains.annotations.NotNull()
    com.google.gson.JsonObject $this$getLongOrDefault, @org.jetbrains.annotations.NotNull()
    java.lang.String key, long p2_772401952) {
        return 0L;
    }
    
    /**
     * Safe int get from JsonObject, returns 0 if key absent.
     */
    public static final int getIntOrDefault(@org.jetbrains.annotations.NotNull()
    com.google.gson.JsonObject $this$getIntOrDefault, @org.jetbrains.annotations.NotNull()
    java.lang.String key, int p2_772401952) {
        return 0;
    }
}