package com.qxy.NoError.list.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.qxy.NoError.list.bean.Movie;
import com.qxy.NoError.list.bean.Variety;

import java.util.List;

/**
 * * 对数据库中的综艺表进行增删改查
 */
@Dao
public interface VarietyDao {
    /**
     * 插入某个数据
     *
     * @param variety
     */
    @Insert
    void insert(Variety variety);

    /**
     * 插入多个数据
     *
     * @param varieties
     */
    @Insert
    void insertAll(Variety... varieties);

    /**
     * 删除某个数据
     *
     * @param variety
     */
    @Delete
    void delete(Variety variety);

    /**
     * 删除表中所有数据
     */
    @Query("DELETE FROM variety_table")
    void deleteAll();

    /**
     * 查询表中所有数据
     *
     * @return
     */
    @Query("SELECT * FROM variety_table")
    List<Variety> queryAllMovies();
}
