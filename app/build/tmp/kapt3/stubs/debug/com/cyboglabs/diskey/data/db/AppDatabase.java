package com.cyboglabs.diskey.data.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import com.cyboglabs.diskey.data.db.dao.AudioFileDao;
import com.cyboglabs.diskey.data.db.dao.DeviceDao;
import com.cyboglabs.diskey.data.db.dao.DownloadHistoryDao;
import com.cyboglabs.diskey.data.db.dao.PlaybackHistoryDao;
import com.cyboglabs.diskey.data.db.entity.AudioFileEntity;
import com.cyboglabs.diskey.data.db.entity.DeviceEntity;
import com.cyboglabs.diskey.data.db.entity.DownloadHistoryEntity;
import com.cyboglabs.diskey.data.db.entity.PlaybackHistoryEntity;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\'\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&J\b\u0010\u0005\u001a\u00020\u0006H&J\b\u0010\u0007\u001a\u00020\bH&J\b\u0010\t\u001a\u00020\nH&\u00a8\u0006\u000b"}, d2 = {"Lcom/cyboglabs/diskey/data/db/AppDatabase;", "Landroidx/room/RoomDatabase;", "()V", "audioFileDao", "Lcom/cyboglabs/diskey/data/db/dao/AudioFileDao;", "deviceDao", "Lcom/cyboglabs/diskey/data/db/dao/DeviceDao;", "downloadHistoryDao", "Lcom/cyboglabs/diskey/data/db/dao/DownloadHistoryDao;", "playbackHistoryDao", "Lcom/cyboglabs/diskey/data/db/dao/PlaybackHistoryDao;", "app_debug"})
@androidx.room.Database(entities = {com.cyboglabs.diskey.data.db.entity.DeviceEntity.class, com.cyboglabs.diskey.data.db.entity.AudioFileEntity.class, com.cyboglabs.diskey.data.db.entity.DownloadHistoryEntity.class, com.cyboglabs.diskey.data.db.entity.PlaybackHistoryEntity.class}, version = 1, exportSchema = true)
public abstract class AppDatabase extends androidx.room.RoomDatabase {
    
    public AppDatabase() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.cyboglabs.diskey.data.db.dao.DeviceDao deviceDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.cyboglabs.diskey.data.db.dao.AudioFileDao audioFileDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.cyboglabs.diskey.data.db.dao.DownloadHistoryDao downloadHistoryDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.cyboglabs.diskey.data.db.dao.PlaybackHistoryDao playbackHistoryDao();
}