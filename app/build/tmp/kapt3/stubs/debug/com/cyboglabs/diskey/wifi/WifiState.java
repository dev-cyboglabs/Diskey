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

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/cyboglabs/diskey/wifi/WifiState;", "", "(Ljava/lang/String;I)V", "DISCONNECTED", "CONNECTING", "CONNECTED", "ERROR", "app_debug"})
public enum WifiState {
    /*public static final*/ DISCONNECTED /* = new DISCONNECTED() */,
    /*public static final*/ CONNECTING /* = new CONNECTING() */,
    /*public static final*/ CONNECTED /* = new CONNECTED() */,
    /*public static final*/ ERROR /* = new ERROR() */;
    
    WifiState() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public static kotlin.enums.EnumEntries<com.cyboglabs.diskey.wifi.WifiState> getEntries() {
        return null;
    }
}