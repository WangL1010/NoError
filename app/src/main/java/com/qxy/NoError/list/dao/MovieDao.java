package com.qxy.NoError.list.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.qxy.NoError.list.bean.Movie;

import java.util.List;

/**
 * 对数据库中的电影表进行增删改查
 */
@Dao
public interface MovieDao {
    /**
     * 插入某个数据
     * @param movie
     */
    @Insert
    void insert(Movie movie);

    /**
     * 插入多个数据
     * @param movies
     */
    @Insert
    void insertAll(Movie... movies);

    /**
     * 删除某个数据
     * @param movie
     */
    @Delete
    void delete(Movie movie);

    /**
     * 删除表中所有数据
     */
    @Query("DELETE FROM movie_table")
    void deleteAll();

    /**
     * 查询表中所有数据
     * @return
     */
    @Query("SELECT * FROM movie_table")
    List<Movie> queryAllMovies();



}
