package com.cyboglabs.diskey.data.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import com.cyboglabs.diskey.data.db.entity.DeviceEntity;
import kotlinx.coroutines.flow.Flow;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0014\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\bH\'J\u0018\u0010\u000b\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\f\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\nH\u00a7@\u00a2\u0006\u0002\u0010\u000eJ\u001e\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u0011H\u00a7@\u00a2\u0006\u0002\u0010\u0012J\u001e\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0014\u001a\u00020\u0015H\u00a7@\u00a2\u0006\u0002\u0010\u0016J\u001e\u0010\u0017\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0018\u001a\u00020\u0019H\u00a7@\u00a2\u0006\u0002\u0010\u001a\u00a8\u0006\u001b"}, d2 = {"Lcom/cyboglabs/diskey/data/db/dao/DeviceDao;", "", "deleteDevice", "", "address", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllDevices", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/cyboglabs/diskey/data/db/entity/DeviceEntity;", "getDevice", "insertOrUpdate", "device", "(Lcom/cyboglabs/diskey/data/db/entity/DeviceEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateBattery", "level", "", "(Ljava/lang/String;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateLastConnected", "timeMs", "", "(Ljava/lang/String;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updatePairingState", "paired", "", "(Ljava/lang/String;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
@androidx.room.Dao()
public abstract interface DeviceDao {
    
    @androidx.room.Query(value = "SELECT * FROM devices ORDER BY lastConnectedMs DESC")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.cyboglabs.diskey.data.db.entity.DeviceEntity>> getAllDevices();
    
    @androidx.room.Query(value = "SELECT * FROM devices WHERE address = :address LIMIT 1")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getDevice(@org.jetbrains.annotations.NotNull()
    java.lang.String address, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.cyboglabs.diskey.data.db.entity.DeviceEntity> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertOrUpdate(@org.jetbrains.annotations.NotNull()
    com.cyboglabs.diskey.data.db.entity.DeviceEntity device, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "UPDATE devices SET batteryLevel = :level WHERE address = :address")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updateBattery(@org.jetbrains.annotations.NotNull()
    java.lang.String address, int level, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "UPDATE devices SET lastConnectedMs = :timeMs WHERE address = :address")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updateLastConnected(@org.jetbrains.annotations.NotNull()
    java.lang.String address, long timeMs, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "UPDATE devices SET isPaired = :paired WHERE address = :address")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updatePairingState(@org.jetbrains.annotations.NotNull()
    java.lang.String address, boolean paired, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM devices WHERE address = :address")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteDevice(@org.jetbrains.annotations.NotNull()
    java.lang.String address, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}