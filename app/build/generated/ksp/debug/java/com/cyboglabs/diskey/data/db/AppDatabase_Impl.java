package com.cyboglabs.diskey.data.db;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import com.cyboglabs.diskey.data.db.dao.AudioFileDao;
import com.cyboglabs.diskey.data.db.dao.AudioFileDao_Impl;
import com.cyboglabs.diskey.data.db.dao.DeviceDao;
import com.cyboglabs.diskey.data.db.dao.DeviceDao_Impl;
import com.cyboglabs.diskey.data.db.dao.DownloadHistoryDao;
import com.cyboglabs.diskey.data.db.dao.DownloadHistoryDao_Impl;
import com.cyboglabs.diskey.data.db.dao.PlaybackHistoryDao;
import com.cyboglabs.diskey.data.db.dao.PlaybackHistoryDao_Impl;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class AppDatabase_Impl extends AppDatabase {
  private volatile DeviceDao _deviceDao;

  private volatile AudioFileDao _audioFileDao;

  private volatile DownloadHistoryDao _downloadHistoryDao;

  private volatile PlaybackHistoryDao _playbackHistoryDao;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `devices` (`address` TEXT NOT NULL, `name` TEXT NOT NULL, `uuid` TEXT NOT NULL, `firmwareVersion` TEXT NOT NULL, `isPaired` INTEGER NOT NULL, `lastConnectedMs` INTEGER NOT NULL, `batteryLevel` INTEGER NOT NULL, PRIMARY KEY(`address`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `audio_files` (`filename` TEXT NOT NULL, `sizeBytes` INTEGER NOT NULL, `createdAtEpoch` INTEGER NOT NULL, `durationMs` INTEGER NOT NULL, `index` INTEGER NOT NULL, `localPath` TEXT, `isDownloaded` INTEGER NOT NULL, `deviceAddress` TEXT NOT NULL, `downloadedAtMs` INTEGER NOT NULL, PRIMARY KEY(`filename`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `download_history` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `filename` TEXT NOT NULL, `deviceAddress` TEXT NOT NULL, `startedAtMs` INTEGER NOT NULL, `completedAtMs` INTEGER NOT NULL, `sizeBytes` INTEGER NOT NULL, `success` INTEGER NOT NULL, `errorMessage` TEXT)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `playback_history` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `filename` TEXT NOT NULL, `playedAtMs` INTEGER NOT NULL, `durationPlayedMs` INTEGER NOT NULL, `positionMs` INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '0e9c2ea7b11696a77fc16d68f193d4ea')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `devices`");
        db.execSQL("DROP TABLE IF EXISTS `audio_files`");
        db.execSQL("DROP TABLE IF EXISTS `download_history`");
        db.execSQL("DROP TABLE IF EXISTS `playback_history`");
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onDestructiveMigration(db);
          }
        }
      }

      @Override
      public void onCreate(@NonNull final SupportSQLiteDatabase db) {
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onCreate(db);
          }
        }
      }

      @Override
      public void onOpen(@NonNull final SupportSQLiteDatabase db) {
        mDatabase = db;
        internalInitInvalidationTracker(db);
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onOpen(db);
          }
        }
      }

      @Override
      public void onPreMigrate(@NonNull final SupportSQLiteDatabase db) {
        DBUtil.dropFtsSyncTriggers(db);
      }

      @Override
      public void onPostMigrate(@NonNull final SupportSQLiteDatabase db) {
      }

      @Override
      @NonNull
      public RoomOpenHelper.ValidationResult onValidateSchema(
          @NonNull final SupportSQLiteDatabase db) {
        final HashMap<String, TableInfo.Column> _columnsDevices = new HashMap<String, TableInfo.Column>(7);
        _columnsDevices.put("address", new TableInfo.Column("address", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDevices.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDevices.put("uuid", new TableInfo.Column("uuid", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDevices.put("firmwareVersion", new TableInfo.Column("firmwareVersion", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDevices.put("isPaired", new TableInfo.Column("isPaired", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDevices.put("lastConnectedMs", new TableInfo.Column("lastConnectedMs", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDevices.put("batteryLevel", new TableInfo.Column("batteryLevel", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysDevices = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesDevices = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoDevices = new TableInfo("devices", _columnsDevices, _foreignKeysDevices, _indicesDevices);
        final TableInfo _existingDevices = TableInfo.read(db, "devices");
        if (!_infoDevices.equals(_existingDevices)) {
          return new RoomOpenHelper.ValidationResult(false, "devices(com.cyboglabs.diskey.data.db.entity.DeviceEntity).\n"
                  + " Expected:\n" + _infoDevices + "\n"
                  + " Found:\n" + _existingDevices);
        }
        final HashMap<String, TableInfo.Column> _columnsAudioFiles = new HashMap<String, TableInfo.Column>(9);
        _columnsAudioFiles.put("filename", new TableInfo.Column("filename", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAudioFiles.put("sizeBytes", new TableInfo.Column("sizeBytes", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAudioFiles.put("createdAtEpoch", new TableInfo.Column("createdAtEpoch", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAudioFiles.put("durationMs", new TableInfo.Column("durationMs", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAudioFiles.put("index", new TableInfo.Column("index", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAudioFiles.put("localPath", new TableInfo.Column("localPath", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAudioFiles.put("isDownloaded", new TableInfo.Column("isDownloaded", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAudioFiles.put("deviceAddress", new TableInfo.Column("deviceAddress", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAudioFiles.put("downloadedAtMs", new TableInfo.Column("downloadedAtMs", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysAudioFiles = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesAudioFiles = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoAudioFiles = new TableInfo("audio_files", _columnsAudioFiles, _foreignKeysAudioFiles, _indicesAudioFiles);
        final TableInfo _existingAudioFiles = TableInfo.read(db, "audio_files");
        if (!_infoAudioFiles.equals(_existingAudioFiles)) {
          return new RoomOpenHelper.ValidationResult(false, "audio_files(com.cyboglabs.diskey.data.db.entity.AudioFileEntity).\n"
                  + " Expected:\n" + _infoAudioFiles + "\n"
                  + " Found:\n" + _existingAudioFiles);
        }
        final HashMap<String, TableInfo.Column> _columnsDownloadHistory = new HashMap<String, TableInfo.Column>(8);
        _columnsDownloadHistory.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDownloadHistory.put("filename", new TableInfo.Column("filename", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDownloadHistory.put("deviceAddress", new TableInfo.Column("deviceAddress", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDownloadHistory.put("startedAtMs", new TableInfo.Column("startedAtMs", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDownloadHistory.put("completedAtMs", new TableInfo.Column("completedAtMs", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDownloadHistory.put("sizeBytes", new TableInfo.Column("sizeBytes", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDownloadHistory.put("success", new TableInfo.Column("success", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDownloadHistory.put("errorMessage", new TableInfo.Column("errorMessage", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysDownloadHistory = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesDownloadHistory = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoDownloadHistory = new TableInfo("download_history", _columnsDownloadHistory, _foreignKeysDownloadHistory, _indicesDownloadHistory);
        final TableInfo _existingDownloadHistory = TableInfo.read(db, "download_history");
        if (!_infoDownloadHistory.equals(_existingDownloadHistory)) {
          return new RoomOpenHelper.ValidationResult(false, "download_history(com.cyboglabs.diskey.data.db.entity.DownloadHistoryEntity).\n"
                  + " Expected:\n" + _infoDownloadHistory + "\n"
                  + " Found:\n" + _existingDownloadHistory);
        }
        final HashMap<String, TableInfo.Column> _columnsPlaybackHistory = new HashMap<String, TableInfo.Column>(5);
        _columnsPlaybackHistory.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPlaybackHistory.put("filename", new TableInfo.Column("filename", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPlaybackHistory.put("playedAtMs", new TableInfo.Column("playedAtMs", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPlaybackHistory.put("durationPlayedMs", new TableInfo.Column("durationPlayedMs", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPlaybackHistory.put("positionMs", new TableInfo.Column("positionMs", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysPlaybackHistory = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesPlaybackHistory = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoPlaybackHistory = new TableInfo("playback_history", _columnsPlaybackHistory, _foreignKeysPlaybackHistory, _indicesPlaybackHistory);
        final TableInfo _existingPlaybackHistory = TableInfo.read(db, "playback_history");
        if (!_infoPlaybackHistory.equals(_existingPlaybackHistory)) {
          return new RoomOpenHelper.ValidationResult(false, "playback_history(com.cyboglabs.diskey.data.db.entity.PlaybackHistoryEntity).\n"
                  + " Expected:\n" + _infoPlaybackHistory + "\n"
                  + " Found:\n" + _existingPlaybackHistory);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "0e9c2ea7b11696a77fc16d68f193d4ea", "a761a58a5611d988cd3f44ea4023236b");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "devices","audio_files","download_history","playback_history");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `devices`");
      _db.execSQL("DELETE FROM `audio_files`");
      _db.execSQL("DELETE FROM `download_history`");
      _db.execSQL("DELETE FROM `playback_history`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(DeviceDao.class, DeviceDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(AudioFileDao.class, AudioFileDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(DownloadHistoryDao.class, DownloadHistoryDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(PlaybackHistoryDao.class, PlaybackHistoryDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }

  @Override
  public DeviceDao deviceDao() {
    if (_deviceDao != null) {
      return _deviceDao;
    } else {
      synchronized(this) {
        if(_deviceDao == null) {
          _deviceDao = new DeviceDao_Impl(this);
        }
        return _deviceDao;
      }
    }
  }

  @Override
  public AudioFileDao audioFileDao() {
    if (_audioFileDao != null) {
      return _audioFileDao;
    } else {
      synchronized(this) {
        if(_audioFileDao == null) {
          _audioFileDao = new AudioFileDao_Impl(this);
        }
        return _audioFileDao;
      }
    }
  }

  @Override
  public DownloadHistoryDao downloadHistoryDao() {
    if (_downloadHistoryDao != null) {
      return _downloadHistoryDao;
    } else {
      synchronized(this) {
        if(_downloadHistoryDao == null) {
          _downloadHistoryDao = new DownloadHistoryDao_Impl(this);
        }
        return _downloadHistoryDao;
      }
    }
  }

  @Override
  public PlaybackHistoryDao playbackHistoryDao() {
    if (_playbackHistoryDao != null) {
      return _playbackHistoryDao;
    } else {
      synchronized(this) {
        if(_playbackHistoryDao == null) {
          _playbackHistoryDao = new PlaybackHistoryDao_Impl(this);
        }
        return _playbackHistoryDao;
      }
    }
  }
}
