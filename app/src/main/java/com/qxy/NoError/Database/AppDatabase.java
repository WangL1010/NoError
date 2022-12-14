package com.qxy.NoError.Database;

import android.os.Environment;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.qxy.NoError.MyApplication;
import com.qxy.NoError.list.bean.ListData;
import com.qxy.NoError.list.dao.IListDataDao;
import com.qxy.NoError.user.bean.UserOpenInfo;
import com.qxy.NoError.user.dao.IUserOpenInfoDao;

import java.io.File;

/**
 * 定义数据库
 */
@TypeConverters(objectConverter.class)
@Database(entities = {ListData.class,UserOpenInfo.class},version = 2,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static volatile AppDatabase instance;

    private static String dbPath = "database-name";

    public static AppDatabase getInstance() {
        /*
          判断设备是否挂载SD卡，如果挂载，数据库位置为
          dbPath=“/storage/emulated/0/noerror/database-name”
         */
//        String state = Environment.getExternalStorageState();
//        if (state.equals(Environment.MEDIA_MOUNTED)) {
//            dbPath = Environment.getExternalStorageDirectory().getPath() + File.separator + "noerror" + File.separator + dbPath;
//        }
        /*
          获取database单例
         */
        if (instance == null) {
            synchronized (AppDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(MyApplication.getAppContext(), AppDatabase.class, dbPath)
                            //.allowMainThreadQueries() //不要允许主线程查询吧
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return instance;
    }

    /**
     * 获取榜单dao的操作对象
     * @return
     */
    public abstract IListDataDao getListDataDao();

    public abstract IUserOpenInfoDao getUserOpenInfoDao();
}
