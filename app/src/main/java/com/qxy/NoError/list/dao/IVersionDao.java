package com.qxy.NoError.list.dao;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Update;

import com.qxy.NoError.list.bean.Version;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

/**
 * 存储榜单版本信息
 *
 * @author 徐鑫
 */
@Dao
public interface IVersionDao {

    /**
     * 查询数据库中的版本信息
     *
     * @param type
     * @return
     */
    @Query("select * from version where type = :type")
    Single<List<Version>> getVersionForPage(int type);

    /**
     * 更新数据库
     *
     * @param versions
     * @return
     */
    @Update
    Single<Integer> updateVersion(List<Version> versions);

}
