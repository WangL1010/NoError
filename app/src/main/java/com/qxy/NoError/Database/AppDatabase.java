package com.qxy.NoError.Database;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.qxy.NoError.MyApplication;
import com.qxy.NoError.list.bean.ListData;
import com.qxy.NoError.list.dao.IListDataDao;

/**
 * 定义数据库
 */
@TypeConverters(objectConverter.class)
@Database(entities = {ListData.class},version = 1,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private volatile static AppDatabase instance;

    public static AppDatabase getInstance() {
        if (instance == null) {
            synchronized (AppDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(MyApplication.getAppContext(),
                                    AppDatabase.class,
                                    "app_database")
                            //.allowMainThreadQueries() //不要允许主线程查询吧
                            .build();
                }
            }
        }
        return instance;
    }

    /**
     * 获取list dao对象
     * @return list dao对象
     */
    public abstract IListDataDao getListDataDao();
}
