package com.qxy.NoError.user.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.qxy.NoError.user.bean.UserOpenInfo;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

/**
 *对表userOpenInfo进行数据操作
 */
@Dao
public interface IUserOpenInfoDao {
    /**
     * 存储用户公开信息
     * @param userOpenInfo 用户信息
     * @return 异步操作对象
     */
    @Insert
    Completable insertData(UserOpenInfo userOpenInfo);

    /**
     * 删除表中数据
     * @return
     */
    @Query("delete from userOpenInfo")
    Single<Integer> deleteData();

    /**
     * 查询用户信息
     * @return
     */
    @Query("select * from userOpenInfo")
    Single<UserOpenInfo> getUserOpenInfo();
    /**
     * 更新数据
     * @param userOpenInfo 用户信息
     * @return 异步操作对象
     */
    @Update
    Single<Integer> updateData(UserOpenInfo userOpenInfo);

}
