package com.cyboglabs.diskey.utils

import com.google.gson.Gson
import com.google.gson.JsonObject

/** Parse a JSON string into a JsonObject; returns empty object on failure. */
fun String.parseJson(gson: Gson): JsonObject {
    return try {
        gson.fromJson(this, JsonObject::class.java) ?: JsonObject()
    } catch (e: Exception) {
        JsonObject()
    }
}

/** Safe string get from JsonObject, returns null if key absent. */
fun JsonObject.getStrOrNull(key: String): String? =
    if (has(key) && !get(key).isJsonNull) get(key).asString else null

/** Safe long get from JsonObject, returns 0 if key absent. */
fun JsonObject.getLongOrDefault(key: String, default: Long = 0L): Long =
    if (has(key) && !get(key).isJsonNull) runCatching { get(key).asLong }.getOrDefault(default) else default

/** Safe int get from JsonObject, returns 0 if key absent. */
fun JsonObject.getIntOrDefault(key: String, default: Int = 0): Int =
    if (has(key) && !get(key).isJsonNull) runCatching { get(key).asInt }.getOrDefault(default) else default
