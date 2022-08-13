package com.qxy.NoError.Database;

import android.os.Environment;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.qxy.NoError.MyApplication;
import com.qxy.NoError.list.bean.ListData;
import com.qxy.NoError.list.dao.ListDataDao;

import java.io.File;

/**
 * 定义数据库
 */
@TypeConverters(objectConverter.class)
@Database(entities = {ListData.class},version = 1,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase appDatabase;

    private static String dbPath = "database-name";

    public static AppDatabase getDatabase() {
        /**
         * 判断设备是否挂载SD卡，如果挂载，数据库位置为
         * dbPath=“/storage/emulated/0/noerror/database-name”
         */
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)){
            dbPath=Environment.getExternalStorageDirectory().getPath()+ File.separator+"noerror"+File.separator+dbPath;
        }
        /**
         * 获取database单例
         */
        if (appDatabase==null){
            synchronized (AppDatabase.class){
                if (appDatabase == null) {
                    appDatabase = Room.databaseBuilder(MyApplication.getAppContext(), AppDatabase.class, dbPath)
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return appDatabase;
    }

    public abstract ListDataDao getListDataDao();

}
