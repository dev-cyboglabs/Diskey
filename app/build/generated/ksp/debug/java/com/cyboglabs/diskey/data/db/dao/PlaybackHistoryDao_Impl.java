package com.cyboglabs.diskey.data.db.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.cyboglabs.diskey.data.db.entity.PlaybackHistoryEntity;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class PlaybackHistoryDao_Impl implements PlaybackHistoryDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<PlaybackHistoryEntity> __insertionAdapterOfPlaybackHistoryEntity;

  public PlaybackHistoryDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfPlaybackHistoryEntity = new EntityInsertionAdapter<PlaybackHistoryEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `playback_history` (`id`,`filename`,`playedAtMs`,`durationPlayedMs`,`positionMs`) VALUES (nullif(?, 0),?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final PlaybackHistoryEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getFilename());
        statement.bindLong(3, entity.getPlayedAtMs());
        statement.bindLong(4, entity.getDurationPlayedMs());
        statement.bindLong(5, entity.getPositionMs());
      }
    };
  }

  @Override
  public Object insert(final PlaybackHistoryEntity entry,
      final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfPlaybackHistoryEntity.insertAndReturnId(entry);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<PlaybackHistoryEntity>> getRecentPlayback() {
    final String _sql = "SELECT * FROM playback_history ORDER BY playedAtMs DESC LIMIT 50";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"playback_history"}, new Callable<List<PlaybackHistoryEntity>>() {
      @Override
      @NonNull
      public List<PlaybackHistoryEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfFilename = CursorUtil.getColumnIndexOrThrow(_cursor, "filename");
          final int _cursorIndexOfPlayedAtMs = CursorUtil.getColumnIndexOrThrow(_cursor, "playedAtMs");
          final int _cursorIndexOfDurationPlayedMs = CursorUtil.getColumnIndexOrThrow(_cursor, "durationPlayedMs");
          final int _cursorIndexOfPositionMs = CursorUtil.getColumnIndexOrThrow(_cursor, "positionMs");
          final List<PlaybackHistoryEntity> _result = new ArrayList<PlaybackHistoryEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final PlaybackHistoryEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpFilename;
            _tmpFilename = _cursor.getString(_cursorIndexOfFilename);
            final long _tmpPlayedAtMs;
            _tmpPlayedAtMs = _cursor.getLong(_cursorIndexOfPlayedAtMs);
            final long _tmpDurationPlayedMs;
            _tmpDurationPlayedMs = _cursor.getLong(_cursorIndexOfDurationPlayedMs);
            final long _tmpPositionMs;
            _tmpPositionMs = _cursor.getLong(_cursorIndexOfPositionMs);
            _item = new PlaybackHistoryEntity(_tmpId,_tmpFilename,_tmpPlayedAtMs,_tmpDurationPlayedMs,_tmpPositionMs);
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
  public Object getResumePosition(final String filename,
      final Continuation<? super Long> $completion) {
    final String _sql = "SELECT positionMs FROM playback_history WHERE filename = ? ORDER BY playedAtMs DESC LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, filename);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Long>() {
      @Override
      @Nullable
      public Long call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Long _result;
          if (_cursor.moveToFirst()) {
            if (_cursor.isNull(0)) {
              _result = null;
            } else {
              _result = _cursor.getLong(0);
            }
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

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
