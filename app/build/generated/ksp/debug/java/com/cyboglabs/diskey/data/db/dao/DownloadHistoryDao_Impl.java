package com.cyboglabs.diskey.data.db.dao;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.cyboglabs.diskey.data.db.entity.DownloadHistoryEntity;
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
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class DownloadHistoryDao_Impl implements DownloadHistoryDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<DownloadHistoryEntity> __insertionAdapterOfDownloadHistoryEntity;

  private final SharedSQLiteStatement __preparedStmtOfTrimOldest;

  public DownloadHistoryDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfDownloadHistoryEntity = new EntityInsertionAdapter<DownloadHistoryEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `download_history` (`id`,`filename`,`deviceAddress`,`startedAtMs`,`completedAtMs`,`sizeBytes`,`success`,`errorMessage`) VALUES (nullif(?, 0),?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final DownloadHistoryEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getFilename());
        statement.bindString(3, entity.getDeviceAddress());
        statement.bindLong(4, entity.getStartedAtMs());
        statement.bindLong(5, entity.getCompletedAtMs());
        statement.bindLong(6, entity.getSizeBytes());
        final int _tmp = entity.getSuccess() ? 1 : 0;
        statement.bindLong(7, _tmp);
        if (entity.getErrorMessage() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getErrorMessage());
        }
      }
    };
    this.__preparedStmtOfTrimOldest = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM download_history WHERE id IN (SELECT id FROM download_history ORDER BY startedAtMs ASC LIMIT ?)";
        return _query;
      }
    };
  }

  @Override
  public Object insert(final DownloadHistoryEntity entry,
      final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfDownloadHistoryEntity.insertAndReturnId(entry);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object trimOldest(final int count, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfTrimOldest.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, count);
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
          __preparedStmtOfTrimOldest.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<DownloadHistoryEntity>> getRecentHistory() {
    final String _sql = "SELECT * FROM download_history ORDER BY startedAtMs DESC LIMIT 100";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"download_history"}, new Callable<List<DownloadHistoryEntity>>() {
      @Override
      @NonNull
      public List<DownloadHistoryEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfFilename = CursorUtil.getColumnIndexOrThrow(_cursor, "filename");
          final int _cursorIndexOfDeviceAddress = CursorUtil.getColumnIndexOrThrow(_cursor, "deviceAddress");
          final int _cursorIndexOfStartedAtMs = CursorUtil.getColumnIndexOrThrow(_cursor, "startedAtMs");
          final int _cursorIndexOfCompletedAtMs = CursorUtil.getColumnIndexOrThrow(_cursor, "completedAtMs");
          final int _cursorIndexOfSizeBytes = CursorUtil.getColumnIndexOrThrow(_cursor, "sizeBytes");
          final int _cursorIndexOfSuccess = CursorUtil.getColumnIndexOrThrow(_cursor, "success");
          final int _cursorIndexOfErrorMessage = CursorUtil.getColumnIndexOrThrow(_cursor, "errorMessage");
          final List<DownloadHistoryEntity> _result = new ArrayList<DownloadHistoryEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final DownloadHistoryEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpFilename;
            _tmpFilename = _cursor.getString(_cursorIndexOfFilename);
            final String _tmpDeviceAddress;
            _tmpDeviceAddress = _cursor.getString(_cursorIndexOfDeviceAddress);
            final long _tmpStartedAtMs;
            _tmpStartedAtMs = _cursor.getLong(_cursorIndexOfStartedAtMs);
            final long _tmpCompletedAtMs;
            _tmpCompletedAtMs = _cursor.getLong(_cursorIndexOfCompletedAtMs);
            final long _tmpSizeBytes;
            _tmpSizeBytes = _cursor.getLong(_cursorIndexOfSizeBytes);
            final boolean _tmpSuccess;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfSuccess);
            _tmpSuccess = _tmp != 0;
            final String _tmpErrorMessage;
            if (_cursor.isNull(_cursorIndexOfErrorMessage)) {
              _tmpErrorMessage = null;
            } else {
              _tmpErrorMessage = _cursor.getString(_cursorIndexOfErrorMessage);
            }
            _item = new DownloadHistoryEntity(_tmpId,_tmpFilename,_tmpDeviceAddress,_tmpStartedAtMs,_tmpCompletedAtMs,_tmpSizeBytes,_tmpSuccess,_tmpErrorMessage);
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
