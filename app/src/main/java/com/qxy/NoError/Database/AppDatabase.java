package com.qxy.NoError.Database;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.qxy.NoError.MyApplication;
import com.qxy.NoError.list.bean.ListData;
import com.qxy.NoError.list.dao.ListDataDao;

/**
 * 定义数据库
 */
@TypeConverters(objectConverter.class)
@Database(entities = {ListData.class},version = 1,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase appDatabase;

    public static AppDatabase getDatabase() {
        if (appDatabase == null) {
            appDatabase = Room.databaseBuilder(MyApplication.getAppContext(), AppDatabase.class, "database-name")
                    .allowMainThreadQueries()
                    .build();
        }
        return appDatabase;
    }

    public abstract ListDataDao getListDataDao();

}