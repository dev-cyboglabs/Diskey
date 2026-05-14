package com.cyboglabs.diskey.domain.repository;

import com.cyboglabs.diskey.domain.model.Device;
import kotlinx.coroutines.flow.Flow;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\b\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a6@\u00a2\u0006\u0002\u0010\u0006J\u0014\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\bH&J\u0018\u0010\u000b\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0004\u001a\u00020\u0005H\u00a6@\u00a2\u0006\u0002\u0010\u0006J\u0010\u0010\f\u001a\u0004\u0018\u00010\u0005H\u00a6@\u00a2\u0006\u0002\u0010\rJ\u001e\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u0005H\u00a6@\u00a2\u0006\u0002\u0010\u0010J\u0016\u0010\u0011\u001a\u00020\u00032\u0006\u0010\u0012\u001a\u00020\nH\u00a6@\u00a2\u0006\u0002\u0010\u0013J\u001e\u0010\u0014\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0015\u001a\u00020\u0016H\u00a6@\u00a2\u0006\u0002\u0010\u0017\u00a8\u0006\u0018"}, d2 = {"Lcom/cyboglabs/diskey/domain/repository/DeviceRepository;", "", "deleteDevice", "", "address", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllDevices", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/cyboglabs/diskey/domain/model/Device;", "getDevice", "getPairedAddress", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "markPaired", "name", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "saveDevice", "device", "(Lcom/cyboglabs/diskey/domain/model/Device;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateBattery", "level", "", "(Ljava/lang/String;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public abstract interface DeviceRepository {
    
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.cyboglabs.diskey.domain.model.Device>> getAllDevices();
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getDevice(@org.jetbrains.annotations.NotNull()
    java.lang.String address, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.cyboglabs.diskey.domain.model.Device> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object saveDevice(@org.jetbrains.annotations.NotNull()
    com.cyboglabs.diskey.domain.model.Device device, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updateBattery(@org.jetbrains.annotations.NotNull()
    java.lang.String address, int level, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object markPaired(@org.jetbrains.annotations.NotNull()
    java.lang.String address, @org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getPairedAddress(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.String> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteDevice(@org.jetbrains.annotations.NotNull()
    java.lang.String address, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}