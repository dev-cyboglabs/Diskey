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
import com.cyboglabs.diskey.data.db.entity.DeviceEntity;
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
public final class DeviceDao_Impl implements DeviceDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<DeviceEntity> __insertionAdapterOfDeviceEntity;

  private final SharedSQLiteStatement __preparedStmtOfUpdateBattery;

  private final SharedSQLiteStatement __preparedStmtOfUpdateLastConnected;

  private final SharedSQLiteStatement __preparedStmtOfUpdatePairingState;

  private final SharedSQLiteStatement __preparedStmtOfDeleteDevice;

  public DeviceDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfDeviceEntity = new EntityInsertionAdapter<DeviceEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `devices` (`address`,`name`,`uuid`,`firmwareVersion`,`isPaired`,`lastConnectedMs`,`batteryLevel`) VALUES (?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final DeviceEntity entity) {
        statement.bindString(1, entity.getAddress());
        statement.bindString(2, entity.getName());
        statement.bindString(3, entity.getUuid());
        statement.bindString(4, entity.getFirmwareVersion());
        final int _tmp = entity.isPaired() ? 1 : 0;
        statement.bindLong(5, _tmp);
        statement.bindLong(6, entity.getLastConnectedMs());
        statement.bindLong(7, entity.getBatteryLevel());
      }
    };
    this.__preparedStmtOfUpdateBattery = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE devices SET batteryLevel = ? WHERE address = ?";
        return _query;
      }
    };
    this.__preparedStmtOfUpdateLastConnected = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE devices SET lastConnectedMs = ? WHERE address = ?";
        return _query;
      }
    };
    this.__preparedStmtOfUpdatePairingState = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE devices SET isPaired = ? WHERE address = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteDevice = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM devices WHERE address = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insertOrUpdate(final DeviceEntity device,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfDeviceEntity.insert(device);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateBattery(final String address, final int level,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateBattery.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, level);
        _argIndex = 2;
        _stmt.bindString(_argIndex, address);
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
          __preparedStmtOfUpdateBattery.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object updateLastConnected(final String address, final long timeMs,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateLastConnected.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, timeMs);
        _argIndex = 2;
        _stmt.bindString(_argIndex, address);
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
          __preparedStmtOfUpdateLastConnected.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object updatePairingState(final String address, final boolean paired,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfUpdatePairingState.acquire();
        int _argIndex = 1;
        final int _tmp = paired ? 1 : 0;
        _stmt.bindLong(_argIndex, _tmp);
        _argIndex = 2;
        _stmt.bindString(_argIndex, address);
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
          __preparedStmtOfUpdatePairingState.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteDevice(final String address, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteDevice.acquire();
        int _argIndex = 1;
        _stmt.bindString(_argIndex, address);
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
          __preparedStmtOfDeleteDevice.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<DeviceEntity>> getAllDevices() {
    final String _sql = "SELECT * FROM devices ORDER BY lastConnectedMs DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"devices"}, new Callable<List<DeviceEntity>>() {
      @Override
      @NonNull
      public List<DeviceEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfAddress = CursorUtil.getColumnIndexOrThrow(_cursor, "address");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfUuid = CursorUtil.getColumnIndexOrThrow(_cursor, "uuid");
          final int _cursorIndexOfFirmwareVersion = CursorUtil.getColumnIndexOrThrow(_cursor, "firmwareVersion");
          final int _cursorIndexOfIsPaired = CursorUtil.getColumnIndexOrThrow(_cursor, "isPaired");
          final int _cursorIndexOfLastConnectedMs = CursorUtil.getColumnIndexOrThrow(_cursor, "lastConnectedMs");
          final int _cursorIndexOfBatteryLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "batteryLevel");
          final List<DeviceEntity> _result = new ArrayList<DeviceEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final DeviceEntity _item;
            final String _tmpAddress;
            _tmpAddress = _cursor.getString(_cursorIndexOfAddress);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpUuid;
            _tmpUuid = _cursor.getString(_cursorIndexOfUuid);
            final String _tmpFirmwareVersion;
            _tmpFirmwareVersion = _cursor.getString(_cursorIndexOfFirmwareVersion);
            final boolean _tmpIsPaired;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsPaired);
            _tmpIsPaired = _tmp != 0;
            final long _tmpLastConnectedMs;
            _tmpLastConnectedMs = _cursor.getLong(_cursorIndexOfLastConnectedMs);
            final int _tmpBatteryLevel;
            _tmpBatteryLevel = _cursor.getInt(_cursorIndexOfBatteryLevel);
            _item = new DeviceEntity(_tmpAddress,_tmpName,_tmpUuid,_tmpFirmwareVersion,_tmpIsPaired,_tmpLastConnectedMs,_tmpBatteryLevel);
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
  public Object getDevice(final String address,
      final Continuation<? super DeviceEntity> $completion) {
    final String _sql = "SELECT * FROM devices WHERE address = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, address);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<DeviceEntity>() {
      @Override
      @Nullable
      public DeviceEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfAddress = CursorUtil.getColumnIndexOrThrow(_cursor, "address");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfUuid = CursorUtil.getColumnIndexOrThrow(_cursor, "uuid");
          final int _cursorIndexOfFirmwareVersion = CursorUtil.getColumnIndexOrThrow(_cursor, "firmwareVersion");
          final int _cursorIndexOfIsPaired = CursorUtil.getColumnIndexOrThrow(_cursor, "isPaired");
          final int _cursorIndexOfLastConnectedMs = CursorUtil.getColumnIndexOrThrow(_cursor, "lastConnectedMs");
          final int _cursorIndexOfBatteryLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "batteryLevel");
          final DeviceEntity _result;
          if (_cursor.moveToFirst()) {
            final String _tmpAddress;
            _tmpAddress = _cursor.getString(_cursorIndexOfAddress);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpUuid;
            _tmpUuid = _cursor.getString(_cursorIndexOfUuid);
            final String _tmpFirmwareVersion;
            _tmpFirmwareVersion = _cursor.getString(_cursorIndexOfFirmwareVersion);
            final boolean _tmpIsPaired;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsPaired);
            _tmpIsPaired = _tmp != 0;
            final long _tmpLastConnectedMs;
            _tmpLastConnectedMs = _cursor.getLong(_cursorIndexOfLastConnectedMs);
            final int _tmpBatteryLevel;
            _tmpBatteryLevel = _cursor.getInt(_cursorIndexOfBatteryLevel);
            _result = new DeviceEntity(_tmpAddress,_tmpName,_tmpUuid,_tmpFirmwareVersion,_tmpIsPaired,_tmpLastConnectedMs,_tmpBatteryLevel);
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
