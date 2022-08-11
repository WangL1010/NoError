package com.qxy.NoError.list.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.qxy.NoError.list.bean.ListData;

import java.util.List;

/**
 * 对表listData_table进行数据操作
 */
@Dao
public interface ListDataDao {

    //添加数据
    @Insert
    void insert(ListData listData);

    //删除所有数据
    @Query("DELETE FROM listData_table")
    void deleteAll();

    //查找所有数据
    @Query("SELECT * FROM listData_table")
    List<ListData> getAllData();
}
