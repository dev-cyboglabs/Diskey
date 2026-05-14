package com.cyboglabs.diskey.data.repository;

import com.cyboglabs.diskey.data.datastore.AppPreferences;
import com.cyboglabs.diskey.data.db.dao.DeviceDao;
import com.cyboglabs.diskey.data.db.entity.DeviceEntity;
import com.cyboglabs.diskey.domain.model.Device;
import com.cyboglabs.diskey.domain.repository.DeviceRepository;
import kotlinx.coroutines.flow.Flow;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0096@\u00a2\u0006\u0002\u0010\u000bJ\u0014\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u000e0\rH\u0016J\u0018\u0010\u0010\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\t\u001a\u00020\nH\u0096@\u00a2\u0006\u0002\u0010\u000bJ\u0010\u0010\u0011\u001a\u0004\u0018\u00010\nH\u0096@\u00a2\u0006\u0002\u0010\u0012J\u001e\u0010\u0013\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\nH\u0096@\u00a2\u0006\u0002\u0010\u0015J\u0016\u0010\u0016\u001a\u00020\b2\u0006\u0010\u0017\u001a\u00020\u000fH\u0096@\u00a2\u0006\u0002\u0010\u0018J\u001e\u0010\u0019\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u001a\u001a\u00020\u001bH\u0096@\u00a2\u0006\u0002\u0010\u001cJ\f\u0010\u001d\u001a\u00020\u000f*\u00020\u001eH\u0002J\f\u0010\u001f\u001a\u00020\u001e*\u00020\u000fH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006 "}, d2 = {"Lcom/cyboglabs/diskey/data/repository/DeviceRepositoryImpl;", "Lcom/cyboglabs/diskey/domain/repository/DeviceRepository;", "deviceDao", "Lcom/cyboglabs/diskey/data/db/dao/DeviceDao;", "appPreferences", "Lcom/cyboglabs/diskey/data/datastore/AppPreferences;", "(Lcom/cyboglabs/diskey/data/db/dao/DeviceDao;Lcom/cyboglabs/diskey/data/datastore/AppPreferences;)V", "deleteDevice", "", "address", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllDevices", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/cyboglabs/diskey/domain/model/Device;", "getDevice", "getPairedAddress", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "markPaired", "name", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "saveDevice", "device", "(Lcom/cyboglabs/diskey/domain/model/Device;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateBattery", "level", "", "(Ljava/lang/String;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "toDomain", "Lcom/cyboglabs/diskey/data/db/entity/DeviceEntity;", "toEntity", "app_debug"})
public final class DeviceRepositoryImpl implements com.cyboglabs.diskey.domain.repository.DeviceRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.cyboglabs.diskey.data.db.dao.DeviceDao deviceDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.cyboglabs.diskey.data.datastore.AppPreferences appPreferences = null;
    
    @javax.inject.Inject()
    public DeviceRepositoryImpl(@org.jetbrains.annotations.NotNull()
    com.cyboglabs.diskey.data.db.dao.DeviceDao deviceDao, @org.jetbrains.annotations.NotNull()
    com.cyboglabs.diskey.data.datastore.AppPreferences appPreferences) {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<java.util.List<com.cyboglabs.diskey.domain.model.Device>> getAllDevices() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getDevice(@org.jetbrains.annotations.NotNull()
    java.lang.String address, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.cyboglabs.diskey.domain.model.Device> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object saveDevice(@org.jetbrains.annotations.NotNull()
    com.cyboglabs.diskey.domain.model.Device device, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object updateBattery(@org.jetbrains.annotations.NotNull()
    java.lang.String address, int level, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object markPaired(@org.jetbrains.annotations.NotNull()
    java.lang.String address, @org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getPairedAddress(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.String> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object deleteDevice(@org.jetbrains.annotations.NotNull()
    java.lang.String address, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    private final com.cyboglabs.diskey.domain.model.Device toDomain(com.cyboglabs.diskey.data.db.entity.DeviceEntity $this$toDomain) {
        return null;
    }
    
    private final com.cyboglabs.diskey.data.db.entity.DeviceEntity toEntity(com.cyboglabs.diskey.domain.model.Device $this$toEntity) {
        return null;
    }
}