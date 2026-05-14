package com.cyboglabs.diskey.domain.model;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007\u00a8\u0006\b"}, d2 = {"Lcom/cyboglabs/diskey/domain/model/DownloadState;", "", "(Ljava/lang/String;I)V", "IDLE", "QUEUED", "DOWNLOADING", "COMPLETED", "FAILED", "app_debug"})
public enum DownloadState {
    /*public static final*/ IDLE /* = new IDLE() */,
    /*public static final*/ QUEUED /* = new QUEUED() */,
    /*public static final*/ DOWNLOADING /* = new DOWNLOADING() */,
    /*public static final*/ COMPLETED /* = new COMPLETED() */,
    /*public static final*/ FAILED /* = new FAILED() */;
    
    DownloadState() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public static kotlin.enums.EnumEntries<com.cyboglabs.diskey.domain.model.DownloadState> getEntries() {
        return null;
    }
}