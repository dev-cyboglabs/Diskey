package com.cyboglabs.diskey.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "diskey_prefs")

@Singleton
class AppPreferences @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val ds = context.dataStore

    companion object {
        val KEY_DEVICE_UUID = stringPreferencesKey("device_uuid")
        val KEY_PAIRED_ADDRESS = stringPreferencesKey("paired_address")
        val KEY_PAIRED_NAME = stringPreferencesKey("paired_name")
        val KEY_AUTO_RECONNECT = booleanPreferencesKey("auto_reconnect")
        val KEY_AUTO_SYNC = booleanPreferencesKey("auto_sync")
        val KEY_SAVE_WAV = booleanPreferencesKey("save_wav")
        val KEY_SCREEN_TIMEOUT_MIN = intPreferencesKey("screen_timeout_min")
        val KEY_AUTO_POWER_OFF_MIN = intPreferencesKey("auto_power_off_min")
        val KEY_DARK_MODE = booleanPreferencesKey("dark_mode")
        val KEY_LAST_DEVICE_ADDRESS = stringPreferencesKey("last_device_address")
        val KEY_APP_UUID = stringPreferencesKey("app_uuid")
    }

    val deviceUuid: Flow<String> = ds.data.map { it[KEY_DEVICE_UUID] ?: "" }
    val pairedAddress: Flow<String?> = ds.data.map { it[KEY_PAIRED_ADDRESS] }
    val pairedName: Flow<String?> = ds.data.map { it[KEY_PAIRED_NAME] }
    val autoReconnect: Flow<Boolean> = ds.data.map { it[KEY_AUTO_RECONNECT] ?: true }
    val autoSync: Flow<Boolean> = ds.data.map { it[KEY_AUTO_SYNC] ?: true }
    val saveWav: Flow<Boolean> = ds.data.map { it[KEY_SAVE_WAV] ?: false }
    val screenTimeoutMin: Flow<Int> = ds.data.map { it[KEY_SCREEN_TIMEOUT_MIN] ?: 5 }
    val autoPowerOffMin: Flow<Int> = ds.data.map { it[KEY_AUTO_POWER_OFF_MIN] ?: 30 }
    val darkMode: Flow<Boolean> = ds.data.map { it[KEY_DARK_MODE] ?: true }
    val lastDeviceAddress: Flow<String?> = ds.data.map { it[KEY_LAST_DEVICE_ADDRESS] }

    suspend fun saveDeviceUuid(uuid: String) = ds.edit { it[KEY_DEVICE_UUID] = uuid }
    suspend fun savePairedDevice(address: String, name: String) {
        ds.edit {
            it[KEY_PAIRED_ADDRESS] = address
            it[KEY_PAIRED_NAME] = name
            it[KEY_LAST_DEVICE_ADDRESS] = address
        }
    }
    suspend fun setAutoReconnect(enabled: Boolean) = ds.edit { it[KEY_AUTO_RECONNECT] = enabled }
    suspend fun setAutoSync(enabled: Boolean) = ds.edit { it[KEY_AUTO_SYNC] = enabled }
    suspend fun setSaveWav(enabled: Boolean) = ds.edit { it[KEY_SAVE_WAV] = enabled }
    suspend fun setScreenTimeout(minutes: Int) = ds.edit { it[KEY_SCREEN_TIMEOUT_MIN] = minutes }
    suspend fun setAutoPowerOff(minutes: Int) = ds.edit { it[KEY_AUTO_POWER_OFF_MIN] = minutes }
    suspend fun setDarkMode(enabled: Boolean) = ds.edit { it[KEY_DARK_MODE] = enabled }
    suspend fun clearPairing() = ds.edit {
        it.remove(KEY_PAIRED_ADDRESS)
        it.remove(KEY_PAIRED_NAME)
        it.remove(KEY_DEVICE_UUID)
    }
}
