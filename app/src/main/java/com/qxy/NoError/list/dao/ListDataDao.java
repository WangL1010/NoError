package com.qxy.NoError.list.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.qxy.NoError.list.bean.ListData;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;

/**
 * 对表listData_table进行数据操作
 */
@Dao
public interface ListDataDao {

    /**
     * 根据listData的id更新数据
     *
     * @param listData 需要更新的数据{@link ListData ListData}
     * @return 受影响的行数
     */
    @Update
    int update(ListData listData);

    /**
     * 插入数据
     *
     * @param listData 需要插入的数据{@link ListData ListData}
     */
    @Insert
    void insert(ListData listData);

    /**
     * 查找所有榜单数据
     *
     * @return 榜单数据
     */
    @Query("SELECT * FROM listData_table")
    List<ListData> getAllData();

    /**
     * 根据type删除数据
     *
     * @param type {@link ListData#type ListData.type}
     */
    @Query("DELETE FROM listData_table WHERE type=:type")
    void deleteByType(Integer type);

    /**
     * 根据type和version删除数据
     *
     * @param type    {@link ListData#type ListData.type}
     * @param version {@link ListData#version ListData.version}
     */
    @Query("DELETE FROM listData_table WHERE type=:type and version=:version")
    void deleteByTypeVersion(Integer type, Integer version);

    /**
     * 根据type查找数据
     *
     * @param type {@link ListData#type ListData.type}
     * @return 查找到的数据
     */
    @Query("SELECT * FROM listData_table WHERE type=:type")
    List<ListData> getDataByType(Integer type);


    /**
     * 根据type和version查找数据
     *
     * @param type    {@link ListData#type ListData.type}
     * @param version {@link ListData#version ListData.version}
     * @return 获取到的数据
     */
    @Query("SELECT * FROM listData_table WHERE type=:type and version=:version")
    List<ListData> getDataByTypeVersion(Integer type, Integer version);
}
