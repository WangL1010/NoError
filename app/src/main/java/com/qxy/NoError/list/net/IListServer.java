package com.qxy.NoError.list.net;

import com.qxy.NoError.list.bean.Movie;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;

/**
 * 请求榜单数据的接口
 * @author 徐鑫
 */
public interface IListServer {

    /**
     *
     * @return
     */
    @GET("discovery/ent/rank/item/?type=1")
    Observable<ResponseData<Movie>> getMovieList();

}
