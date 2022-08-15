package com.qxy.NoError.list.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.qxy.NoError.list.bean.ListData;

import java.util.List;

import cn.hutool.crypto.asymmetric.Sign;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

/**
 * 对表listData_table进行数据操作
 */
@Dao
public interface IListDataDao {

    /**
     * 获取所有榜单数据
     * @param type 数据类型 {@link ListData#type ListData.type}
     * @param version 榜单版本
     * @return 榜单数据
     */
    @Query("select * from listData_table where type = :type and version = :version")
    Single<List<ListData>> getListData(Integer type, Integer version);

    /**
     * 插入可变参数的榜单数据
     * @param listData 需要插入的榜单数据
     * @return 异步操作的对象
     */
    @Insert
    Completable insertData(ListData... listData);

    /**
     * 插入榜单数据集合
     * @param listData 需要插入的榜单数据集合
     * @return 异步操作对象
     */
    @Insert
    Completable insertList(List<ListData> listData);

    /**
     * 依据类型和版本删除数据
     * @param type 数据类型 {@link ListData#type ListData.type}
     * @param version 版本号
     * @return 异步操作对象，受影响的行数
     */
    @Query("delete from listData_table where type = :type and version = :version")
    Single<Integer> deleteListData(Integer type, Integer version);

    /**
     * 更新数据
     * @param list 需要更新的数据
     * @return 受影响的行数
     */
    @Update
    Single<Integer> updateListData(List<ListData> list);
}
