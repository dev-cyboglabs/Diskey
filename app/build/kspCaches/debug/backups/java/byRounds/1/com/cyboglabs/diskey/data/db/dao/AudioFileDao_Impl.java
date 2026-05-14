package com.cyboglabs.diskey.data.db.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.cyboglabs.diskey.data.db.entity.AudioFileEntity;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class AudioFileDao_Impl implements AudioFileDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<AudioFileEntity> __insertionAdapterOfAudioFileEntity;

  private final SharedSQLiteStatement __preparedStmtOfMarkDownloaded;

  private final SharedSQLiteStatement __preparedStmtOfDeleteFile;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllForDevice;

  public AudioFileDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfAudioFileEntity = new EntityInsertionAdapter<AudioFileEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `audio_files` (`filename`,`sizeBytes`,`createdAtEpoch`,`durationMs`,`index`,`localPath`,`isDownloaded`,`deviceAddress`,`downloadedAtMs`) VALUES (?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final AudioFileEntity entity) {
        statement.bindString(1, entity.getFilename());
        statement.bindLong(2, entity.getSizeBytes());
        statement.bindLong(3, entity.getCreatedAtEpoch());
        statement.bindLong(4, entity.getDurationMs());
        statement.bindLong(5, entity.getIndex());
        if (entity.getLocalPath() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getLocalPath());
        }
        final int _tmp = entity.isDownloaded() ? 1 : 0;
        statement.bindLong(7, _tmp);
        statement.bindString(8, entity.getDeviceAddress());
        statement.bindLong(9, entity.getDownloadedAtMs());
      }
    };
    this.__preparedStmtOfMarkDownloaded = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE audio_files SET isDownloaded = 1, localPath = ?, downloadedAtMs = ? WHERE filename = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteFile = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM audio_files WHERE filename = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteAllForDevice = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM audio_files WHERE deviceAddress = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insertOrUpdate(final AudioFileEntity file,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfAudioFileEntity.insert(file);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertOrUpdateAll(final List<AudioFileEntity> files,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfAudioFileEntity.insert(files);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object markDownloaded(final String filename, final String localPath, final long timeMs,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfMarkDownloaded.acquire();
        int _argIndex = 1;
        _stmt.bindString(_argIndex, localPath);
        _argIndex = 2;
        _stmt.bindLong(_argIndex, timeMs);
        _argIndex = 3;
        _stmt.bindString(_argIndex, filename);
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfMarkDownloaded.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteFile(final String filename, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteFile.acquire();
        int _argIndex = 1;
        _stmt.bindString(_argIndex, filename);
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfDeleteFile.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteAllForDevice(final String deviceAddress,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllForDevice.acquire();
        int _argIndex = 1;
        _stmt.bindString(_argIndex, deviceAddress);
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfDeleteAllForDevice.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<AudioFileEntity>> getFilesForDevice(final String deviceAddress) {
    final String _sql = "SELECT * FROM audio_files WHERE deviceAddress = ? ORDER BY createdAtEpoch DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, deviceAddress);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"audio_files"}, new Callable<List<AudioFileEntity>>() {
      @Override
      @NonNull
      public List<AudioFileEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfFilename = CursorUtil.getColumnIndexOrThrow(_cursor, "filename");
          final int _cursorIndexOfSizeBytes = CursorUtil.getColumnIndexOrThrow(_cursor, "sizeBytes");
          final int _cursorIndexOfCreatedAtEpoch = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAtEpoch");
          final int _cursorIndexOfDurationMs = CursorUtil.getColumnIndexOrThrow(_cursor, "durationMs");
          final int _cursorIndexOfIndex = CursorUtil.getColumnIndexOrThrow(_cursor, "index");
          final int _cursorIndexOfLocalPath = CursorUtil.getColumnIndexOrThrow(_cursor, "localPath");
          final int _cursorIndexOfIsDownloaded = CursorUtil.getColumnIndexOrThrow(_cursor, "isDownloaded");
          final int _cursorIndexOfDeviceAddress = CursorUtil.getColumnIndexOrThrow(_cursor, "deviceAddress");
          final int _cursorIndexOfDownloadedAtMs = CursorUtil.getColumnIndexOrThrow(_cursor, "downloadedAtMs");
          final List<AudioFileEntity> _result = new ArrayList<AudioFileEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final AudioFileEntity _item;
            final String _tmpFilename;
            _tmpFilename = _cursor.getString(_cursorIndexOfFilename);
            final long _tmpSizeBytes;
            _tmpSizeBytes = _cursor.getLong(_cursorIndexOfSizeBytes);
            final long _tmpCreatedAtEpoch;
            _tmpCreatedAtEpoch = _cursor.getLong(_cursorIndexOfCreatedAtEpoch);
            final long _tmpDurationMs;
            _tmpDurationMs = _cursor.getLong(_cursorIndexOfDurationMs);
            final int _tmpIndex;
            _tmpIndex = _cursor.getInt(_cursorIndexOfIndex);
            final String _tmpLocalPath;
            if (_cursor.isNull(_cursorIndexOfLocalPath)) {
              _tmpLocalPath = null;
            } else {
              _tmpLocalPath = _cursor.getString(_cursorIndexOfLocalPath);
            }
            final boolean _tmpIsDownloaded;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsDownloaded);
            _tmpIsDownloaded = _tmp != 0;
            final String _tmpDeviceAddress;
            _tmpDeviceAddress = _cursor.getString(_cursorIndexOfDeviceAddress);
            final long _tmpDownloadedAtMs;
            _tmpDownloadedAtMs = _cursor.getLong(_cursorIndexOfDownloadedAtMs);
            _item = new AudioFileEntity(_tmpFilename,_tmpSizeBytes,_tmpCreatedAtEpoch,_tmpDurationMs,_tmpIndex,_tmpLocalPath,_tmpIsDownloaded,_tmpDeviceAddress,_tmpDownloadedAtMs);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object getFile(final String filename,
      final Continuation<? super AudioFileEntity> $completion) {
    final String _sql = "SELECT * FROM audio_files WHERE filename = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, filename);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<AudioFileEntity>() {
      @Override
      @Nullable
      public AudioFileEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfFilename = CursorUtil.getColumnIndexOrThrow(_cursor, "filename");
          final int _cursorIndexOfSizeBytes = CursorUtil.getColumnIndexOrThrow(_cursor, "sizeBytes");
          final int _cursorIndexOfCreatedAtEpoch = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAtEpoch");
          final int _cursorIndexOfDurationMs = CursorUtil.getColumnIndexOrThrow(_cursor, "durationMs");
          final int _cursorIndexOfIndex = CursorUtil.getColumnIndexOrThrow(_cursor, "index");
          final int _cursorIndexOfLocalPath = CursorUtil.getColumnIndexOrThrow(_cursor, "localPath");
          final int _cursorIndexOfIsDownloaded = CursorUtil.getColumnIndexOrThrow(_cursor, "isDownloaded");
          final int _cursorIndexOfDeviceAddress = CursorUtil.getColumnIndexOrThrow(_cursor, "deviceAddress");
          final int _cursorIndexOfDownloadedAtMs = CursorUtil.getColumnIndexOrThrow(_cursor, "downloadedAtMs");
          final AudioFileEntity _result;
          if (_cursor.moveToFirst()) {
            final String _tmpFilename;
            _tmpFilename = _cursor.getString(_cursorIndexOfFilename);
            final long _tmpSizeBytes;
            _tmpSizeBytes = _cursor.getLong(_cursorIndexOfSizeBytes);
            final long _tmpCreatedAtEpoch;
            _tmpCreatedAtEpoch = _cursor.getLong(_cursorIndexOfCreatedAtEpoch);
            final long _tmpDurationMs;
            _tmpDurationMs = _cursor.getLong(_cursorIndexOfDurationMs);
            final int _tmpIndex;
            _tmpIndex = _cursor.getInt(_cursorIndexOfIndex);
            final String _tmpLocalPath;
            if (_cursor.isNull(_cursorIndexOfLocalPath)) {
              _tmpLocalPath = null;
            } else {
              _tmpLocalPath = _cursor.getString(_cursorIndexOfLocalPath);
            }
            final boolean _tmpIsDownloaded;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsDownloaded);
            _tmpIsDownloaded = _tmp != 0;
            final String _tmpDeviceAddress;
            _tmpDeviceAddress = _cursor.getString(_cursorIndexOfDeviceAddress);
            final long _tmpDownloadedAtMs;
            _tmpDownloadedAtMs = _cursor.getLong(_cursorIndexOfDownloadedAtMs);
            _result = new AudioFileEntity(_tmpFilename,_tmpSizeBytes,_tmpCreatedAtEpoch,_tmpDurationMs,_tmpIndex,_tmpLocalPath,_tmpIsDownloaded,_tmpDeviceAddress,_tmpDownloadedAtMs);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<AudioFileEntity>> getDownloadedFiles() {
    final String _sql = "SELECT * FROM audio_files WHERE isDownloaded = 1 ORDER BY downloadedAtMs DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"audio_files"}, new Callable<List<AudioFileEntity>>() {
      @Override
      @NonNull
      public List<AudioFileEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfFilename = CursorUtil.getColumnIndexOrThrow(_cursor, "filename");
          final int _cursorIndexOfSizeBytes = CursorUtil.getColumnIndexOrThrow(_cursor, "sizeBytes");
          final int _cursorIndexOfCreatedAtEpoch = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAtEpoch");
          final int _cursorIndexOfDurationMs = CursorUtil.getColumnIndexOrThrow(_cursor, "durationMs");
          final int _cursorIndexOfIndex = CursorUtil.getColumnIndexOrThrow(_cursor, "index");
          final int _cursorIndexOfLocalPath = CursorUtil.getColumnIndexOrThrow(_cursor, "localPath");
          final int _cursorIndexOfIsDownloaded = CursorUtil.getColumnIndexOrThrow(_cursor, "isDownloaded");
          final int _cursorIndexOfDeviceAddress = CursorUtil.getColumnIndexOrThrow(_cursor, "deviceAddress");
          final int _cursorIndexOfDownloadedAtMs = CursorUtil.getColumnIndexOrThrow(_cursor, "downloadedAtMs");
          final List<AudioFileEntity> _result = new ArrayList<AudioFileEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final AudioFileEntity _item;
            final String _tmpFilename;
            _tmpFilename = _cursor.getString(_cursorIndexOfFilename);
            final long _tmpSizeBytes;
            _tmpSizeBytes = _cursor.getLong(_cursorIndexOfSizeBytes);
            final long _tmpCreatedAtEpoch;
            _tmpCreatedAtEpoch = _cursor.getLong(_cursorIndexOfCreatedAtEpoch);
            final long _tmpDurationMs;
            _tmpDurationMs = _cursor.getLong(_cursorIndexOfDurationMs);
            final int _tmpIndex;
            _tmpIndex = _cursor.getInt(_cursorIndexOfIndex);
            final String _tmpLocalPath;
            if (_cursor.isNull(_cursorIndexOfLocalPath)) {
              _tmpLocalPath = null;
            } else {
              _tmpLocalPath = _cursor.getString(_cursorIndexOfLocalPath);
            }
            final boolean _tmpIsDownloaded;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsDownloaded);
            _tmpIsDownloaded = _tmp != 0;
            final String _tmpDeviceAddress;
            _tmpDeviceAddress = _cursor.getString(_cursorIndexOfDeviceAddress);
            final long _tmpDownloadedAtMs;
            _tmpDownloadedAtMs = _cursor.getLong(_cursorIndexOfDownloadedAtMs);
            _item = new AudioFileEntity(_tmpFilename,_tmpSizeBytes,_tmpCreatedAtEpoch,_tmpDurationMs,_tmpIndex,_tmpLocalPath,_tmpIsDownloaded,_tmpDeviceAddress,_tmpDownloadedAtMs);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
