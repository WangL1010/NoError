package com.qxy.NoError.list.net;

import com.qxy.NoError.MyApplication;
import com.qxy.NoError.list.bean.ListData;
import com.qxy.NoError.list.bean.Version;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * 请求榜单数据的接口
 * @author 徐鑫
 */
public interface IListServer {

    /**
     * 获取榜单数据
     *
     * @param type 榜单类型，1-电影，2-电视剧，3-综艺
     * @return 异步对象
     */
    @GET("discovery/ent/rank/item/")
    Observable<ResponseData<ListData>> getListData(@Query("type") Integer type);

    /**
     * 获取榜单数据
     *
     * @param type    榜单类型，1-电影，2-电视剧，3-综艺
     * @param version 历史榜单版本
     * @return 异步对象
     */
    @GET("discovery/ent/rank/item/")
    @Headers({
            "Content-Type: application/json"
    })
    Observable<ResponseData<ListData>> getListData(@Query("type") Integer type, @Query("version") Integer version);

    /**
     * 从网络中获取版本信息
     *
     * @param type 榜单类型{@link ListData#type ListData.type}
     * @param cursor 当前页
     * @param count 数据数量
     * @return 响应的数据
     */
    @GET("discovery/ent/rank/version/")
    @Headers({
            "Content-Type: application/json"
    })
    Single<ResponseData<Version>> getVersion(@Query("type") Integer type, @Query("cursor") Integer cursor, @Query("count") Integer count);

}
