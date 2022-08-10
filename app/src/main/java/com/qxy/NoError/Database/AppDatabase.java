package com.qxy.NoError.Database;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.qxy.NoError.MyApplication;
import com.qxy.NoError.list.bean.ListData;

/**
 * 定义数据库
 */
@Database(entities = {ListData.class},version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase appDatabase;

    public static AppDatabase getDatabase() {
        if (appDatabase == null) {
            appDatabase = Room.databaseBuilder(MyApplication.getAppContext(), AppDatabase.class, "database-name").allowMainThreadQueries()
                    .build();
        }
        return appDatabase;
    }

}
