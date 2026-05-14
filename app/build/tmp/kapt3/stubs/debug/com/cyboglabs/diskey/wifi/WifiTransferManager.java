package com.cyboglabs.diskey.wifi;

import android.content.Context;
import com.cyboglabs.diskey.wifi.protocol.WifiPacket;
import com.cyboglabs.diskey.wifi.protocol.WifiPacketCommands;
import com.cyboglabs.diskey.wifi.protocol.WifiPacketSerializer;
import dagger.hilt.android.qualifiers.ApplicationContext;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.flow.SharedFlow;
import kotlinx.coroutines.flow.StateFlow;
import timber.log.Timber;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicInteger;
import javax.inject.Inject;
import javax.inject.Singleton;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\f\b\u0007\u0018\u00002\u00020\u0001B\u0011\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\"\u0010\u001e\u001a\u00020\u001f2\b\b\u0002\u0010 \u001a\u00020!2\b\b\u0002\u0010\"\u001a\u00020#H\u0086@\u00a2\u0006\u0002\u0010$J\u0006\u0010%\u001a\u00020\u001fJ\u0006\u0010&\u001a\u00020#J\u000e\u0010\'\u001a\u00020\u001f2\u0006\u0010(\u001a\u00020!J\u0006\u0010)\u001a\u00020\u001fJ\u000e\u0010*\u001a\u00020\u001f2\u0006\u0010+\u001a\u00020\u0007J\b\u0010,\u001a\u00020\u001fH\u0002J\u0010\u0010-\u001a\u00020\u001f2\u0006\u0010.\u001a\u00020\u0019H\u0002R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00070\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\n0\u001b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001d\u00a8\u0006/"}, d2 = {"Lcom/cyboglabs/diskey/wifi/WifiTransferManager;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "_inboundPackets", "Lkotlinx/coroutines/flow/MutableSharedFlow;", "Lcom/cyboglabs/diskey/wifi/protocol/WifiPacket;", "_state", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/cyboglabs/diskey/wifi/WifiState;", "heartbeatJob", "Lkotlinx/coroutines/Job;", "inboundPackets", "Lkotlinx/coroutines/flow/SharedFlow;", "getInboundPackets", "()Lkotlinx/coroutines/flow/SharedFlow;", "outputStream", "Ljava/io/DataOutputStream;", "receiveJob", "scope", "Lkotlinx/coroutines/CoroutineScope;", "seqCounter", "Ljava/util/concurrent/atomic/AtomicInteger;", "socket", "Ljava/net/Socket;", "state", "Lkotlinx/coroutines/flow/StateFlow;", "getState", "()Lkotlinx/coroutines/flow/StateFlow;", "connect", "", "host", "", "port", "", "(Ljava/lang/String;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "disconnect", "nextSequence", "requestFileDownload", "filename", "requestFileList", "sendPacket", "packet", "startHeartbeat", "startReceiving", "sock", "app_debug"})
public final class WifiTransferManager {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.CoroutineScope scope = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.concurrent.atomic.AtomicInteger seqCounter = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.cyboglabs.diskey.wifi.WifiState> _state = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.cyboglabs.diskey.wifi.WifiState> state = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableSharedFlow<com.cyboglabs.diskey.wifi.protocol.WifiPacket> _inboundPackets = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.SharedFlow<com.cyboglabs.diskey.wifi.protocol.WifiPacket> inboundPackets = null;
    @org.jetbrains.annotations.Nullable()
    private java.net.Socket socket;
    @org.jetbrains.annotations.Nullable()
    private java.io.DataOutputStream outputStream;
    @org.jetbrains.annotations.Nullable()
    private kotlinx.coroutines.Job heartbeatJob;
    @org.jetbrains.annotations.Nullable()
    private kotlinx.coroutines.Job receiveJob;
    
    @javax.inject.Inject()
    public WifiTransferManager(@dagger.hilt.android.qualifiers.ApplicationContext()
    @org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.cyboglabs.diskey.wifi.WifiState> getState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.SharedFlow<com.cyboglabs.diskey.wifi.protocol.WifiPacket> getInboundPackets() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object connect(@org.jetbrains.annotations.NotNull()
    java.lang.String host, int port, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    public final void disconnect() {
    }
    
    public final void sendPacket(@org.jetbrains.annotations.NotNull()
    com.cyboglabs.diskey.wifi.protocol.WifiPacket packet) {
    }
    
    public final int nextSequence() {
        return 0;
    }
    
    public final void requestFileList() {
    }
    
    public final void requestFileDownload(@org.jetbrains.annotations.NotNull()
    java.lang.String filename) {
    }
    
    private final void startHeartbeat() {
    }
    
    private final void startReceiving(java.net.Socket sock) {
    }
}