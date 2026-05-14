package com.cyboglabs.diskey.data.repository;

import com.cyboglabs.diskey.data.db.dao.AudioFileDao;
import com.cyboglabs.diskey.data.db.dao.PlaybackHistoryDao;
import com.cyboglabs.diskey.data.db.entity.AudioFileEntity;
import com.cyboglabs.diskey.domain.model.AudioFile;
import com.cyboglabs.diskey.domain.repository.AudioFileRepository;
import kotlinx.coroutines.flow.Flow;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0096@\u00a2\u0006\u0002\u0010\u000bJ\u0016\u0010\f\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\nH\u0096@\u00a2\u0006\u0002\u0010\u000bJ\u0014\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00100\u000fH\u0016J\u0018\u0010\u0012\u001a\u0004\u0018\u00010\u00112\u0006\u0010\r\u001a\u00020\nH\u0096@\u00a2\u0006\u0002\u0010\u000bJ\u001c\u0010\u0013\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00100\u000f2\u0006\u0010\t\u001a\u00020\nH\u0016J\u0016\u0010\u0014\u001a\u00020\u00152\u0006\u0010\r\u001a\u00020\nH\u0096@\u00a2\u0006\u0002\u0010\u000bJ\u001e\u0010\u0016\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\n2\u0006\u0010\u0017\u001a\u00020\nH\u0096@\u00a2\u0006\u0002\u0010\u0018J$\u0010\u0019\u001a\u00020\b2\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\u0006\u0010\t\u001a\u00020\nH\u0096@\u00a2\u0006\u0002\u0010\u001bJ\f\u0010\u001c\u001a\u00020\u0011*\u00020\u001dH\u0002J\u0014\u0010\u001e\u001a\u00020\u001d*\u00020\u00112\u0006\u0010\t\u001a\u00020\nH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001f"}, d2 = {"Lcom/cyboglabs/diskey/data/repository/AudioFileRepositoryImpl;", "Lcom/cyboglabs/diskey/domain/repository/AudioFileRepository;", "audioFileDao", "Lcom/cyboglabs/diskey/data/db/dao/AudioFileDao;", "playbackHistoryDao", "Lcom/cyboglabs/diskey/data/db/dao/PlaybackHistoryDao;", "(Lcom/cyboglabs/diskey/data/db/dao/AudioFileDao;Lcom/cyboglabs/diskey/data/db/dao/PlaybackHistoryDao;)V", "deleteAllForDevice", "", "deviceAddress", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteFile", "filename", "getDownloadedFiles", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/cyboglabs/diskey/domain/model/AudioFile;", "getFile", "getFilesForDevice", "getResumePosition", "", "markAsDownloaded", "localPath", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "saveFiles", "files", "(Ljava/util/List;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "toDomain", "Lcom/cyboglabs/diskey/data/db/entity/AudioFileEntity;", "toEntity", "app_debug"})
public final class AudioFileRepositoryImpl implements com.cyboglabs.diskey.domain.repository.AudioFileRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.cyboglabs.diskey.data.db.dao.AudioFileDao audioFileDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.cyboglabs.diskey.data.db.dao.PlaybackHistoryDao playbackHistoryDao = null;
    
    @javax.inject.Inject()
    public AudioFileRepositoryImpl(@org.jetbrains.annotations.NotNull()
    com.cyboglabs.diskey.data.db.dao.AudioFileDao audioFileDao, @org.jetbrains.annotations.NotNull()
    com.cyboglabs.diskey.data.db.dao.PlaybackHistoryDao playbackHistoryDao) {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<java.util.List<com.cyboglabs.diskey.domain.model.AudioFile>> getFilesForDevice(@org.jetbrains.annotations.NotNull()
    java.lang.String deviceAddress) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<java.util.List<com.cyboglabs.diskey.domain.model.AudioFile>> getDownloadedFiles() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getFile(@org.jetbrains.annotations.NotNull()
    java.lang.String filename, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.cyboglabs.diskey.domain.model.AudioFile> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object saveFiles(@org.jetbrains.annotations.NotNull()
    java.util.List<com.cyboglabs.diskey.domain.model.AudioFile> files, @org.jetbrains.annotations.NotNull()
    java.lang.String deviceAddress, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object markAsDownloaded(@org.jetbrains.annotations.NotNull()
    java.lang.String filename, @org.jetbrains.annotations.NotNull()
    java.lang.String localPath, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object deleteFile(@org.jetbrains.annotations.NotNull()
    java.lang.String filename, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object deleteAllForDevice(@org.jetbrains.annotations.NotNull()
    java.lang.String deviceAddress, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getResumePosition(@org.jetbrains.annotations.NotNull()
    java.lang.String filename, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion) {
        return null;
    }
    
    private final com.cyboglabs.diskey.domain.model.AudioFile toDomain(com.cyboglabs.diskey.data.db.entity.AudioFileEntity $this$toDomain) {
        return null;
    }
    
    private final com.cyboglabs.diskey.data.db.entity.AudioFileEntity toEntity(com.cyboglabs.diskey.domain.model.AudioFile $this$toEntity, java.lang.String deviceAddress) {
        return null;
    }
}