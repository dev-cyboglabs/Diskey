package com.cyboglabs.diskey.data.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.cyboglabs.diskey.data.db.entity.PlaybackHistoryEntity;
import kotlinx.coroutines.flow.Flow;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\bg\u0018\u00002\u00020\u0001J\u0014\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u0003H\'J\u0018\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\tH\u00a7@\u00a2\u0006\u0002\u0010\nJ\u0016\u0010\u000b\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\r\u00a8\u0006\u000e"}, d2 = {"Lcom/cyboglabs/diskey/data/db/dao/PlaybackHistoryDao;", "", "getRecentPlayback", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/cyboglabs/diskey/data/db/entity/PlaybackHistoryEntity;", "getResumePosition", "", "filename", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insert", "entry", "(Lcom/cyboglabs/diskey/data/db/entity/PlaybackHistoryEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
@androidx.room.Dao()
public abstract interface PlaybackHistoryDao {
    
    @androidx.room.Query(value = "SELECT * FROM playback_history ORDER BY playedAtMs DESC LIMIT 50")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.cyboglabs.diskey.data.db.entity.PlaybackHistoryEntity>> getRecentPlayback();
    
    @androidx.room.Query(value = "SELECT positionMs FROM playback_history WHERE filename = :filename ORDER BY playedAtMs DESC LIMIT 1")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getResumePosition(@org.jetbrains.annotations.NotNull()
    java.lang.String filename, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insert(@org.jetbrains.annotations.NotNull()
    com.cyboglabs.diskey.data.db.entity.PlaybackHistoryEntity entry, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
}