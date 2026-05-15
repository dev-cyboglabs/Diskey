package com.cyboglabs.diskey.data.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.cyboglabs.diskey.data.db.entity.AudioFileEntity;
import kotlinx.coroutines.flow.Flow;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\t\n\u0002\b\u0002\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0014\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\nH\'J\u0018\u0010\r\u001a\u0004\u0018\u00010\f2\u0006\u0010\b\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u001c\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\n2\u0006\u0010\u0004\u001a\u00020\u0005H\'J\u001c\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\fH\u00a7@\u00a2\u0006\u0002\u0010\u0012J\u001c\u0010\u0013\u001a\u00020\u00032\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH\u00a7@\u00a2\u0006\u0002\u0010\u0015J&\u0010\u0016\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\u0017\u001a\u00020\u00052\u0006\u0010\u0018\u001a\u00020\u0019H\u00a7@\u00a2\u0006\u0002\u0010\u001a\u00a8\u0006\u001b"}, d2 = {"Lcom/cyboglabs/diskey/data/db/dao/AudioFileDao;", "", "deleteAllForDevice", "", "deviceAddress", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteFile", "filename", "getDownloadedFiles", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/cyboglabs/diskey/data/db/entity/AudioFileEntity;", "getFile", "getFilesForDevice", "getFilesForDeviceList", "insertOrUpdate", "file", "(Lcom/cyboglabs/diskey/data/db/entity/AudioFileEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertOrUpdateAll", "files", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "markDownloaded", "localPath", "timeMs", "", "(Ljava/lang/String;Ljava/lang/String;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
@androidx.room.Dao()
public abstract interface AudioFileDao {
    
    @androidx.room.Query(value = "SELECT * FROM audio_files WHERE deviceAddress = :deviceAddress ORDER BY createdAtEpoch DESC")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.cyboglabs.diskey.data.db.entity.AudioFileEntity>> getFilesForDevice(@org.jetbrains.annotations.NotNull()
    java.lang.String deviceAddress);
    
    @androidx.room.Query(value = "SELECT * FROM audio_files WHERE deviceAddress = :deviceAddress ORDER BY createdAtEpoch DESC")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getFilesForDeviceList(@org.jetbrains.annotations.NotNull()
    java.lang.String deviceAddress, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.cyboglabs.diskey.data.db.entity.AudioFileEntity>> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM audio_files WHERE filename = :filename LIMIT 1")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getFile(@org.jetbrains.annotations.NotNull()
    java.lang.String filename, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.cyboglabs.diskey.data.db.entity.AudioFileEntity> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM audio_files WHERE isDownloaded = 1 ORDER BY downloadedAtMs DESC")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.cyboglabs.diskey.data.db.entity.AudioFileEntity>> getDownloadedFiles();
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertOrUpdate(@org.jetbrains.annotations.NotNull()
    com.cyboglabs.diskey.data.db.entity.AudioFileEntity file, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertOrUpdateAll(@org.jetbrains.annotations.NotNull()
    java.util.List<com.cyboglabs.diskey.data.db.entity.AudioFileEntity> files, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "UPDATE audio_files SET isDownloaded = 1, localPath = :localPath, downloadedAtMs = :timeMs WHERE filename = :filename")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object markDownloaded(@org.jetbrains.annotations.NotNull()
    java.lang.String filename, @org.jetbrains.annotations.NotNull()
    java.lang.String localPath, long timeMs, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM audio_files WHERE filename = :filename")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteFile(@org.jetbrains.annotations.NotNull()
    java.lang.String filename, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM audio_files WHERE deviceAddress = :deviceAddress")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteAllForDevice(@org.jetbrains.annotations.NotNull()
    java.lang.String deviceAddress, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}