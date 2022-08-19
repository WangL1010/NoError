package com.qxy.NoError.user.net;

import com.qxy.NoError.MyApplication;
import com.qxy.NoError.user.bean.FanListData;
import com.qxy.NoError.user.bean.FollowListData;
import com.qxy.NoError.user.bean.UserOpenInfo;
import com.qxy.NoError.user.bean.VideoListData;


import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface IUserServer {

    /**
     * 请求用户公开信息
     *
     * @param body 请求体
     * @return
     */
    @POST("oauth/userinfo/")
    @Headers({
            "Content-Type: application/json"
    })
    Observable<UserResponseData> getUserOpenInfo(@Body ResBody body);

    /**
     * 请求粉丝列表数据
     * @param openId 用户唯一标志
     * @param cursor 每页数量
     * @param count 分页游标
     * @return
     */
    @GET("fans/list/")
    @Headers({
            "Content-Type: application/json"
    })
    Observable<ListResponseData<FanListData>> getFansListData(@Query("open_id") String openId,
                                                              @Query("cursor") Integer cursor,
                                                              @Query("count") Integer count);

    /**
     * 请求关注列表数据
     * @param openId 用户唯一标志
     * @param cursor 每页数量
     * @param count 分页游标
     * @return
     */
    @GET("following/list/")
    @Headers({
            "Content-Type: application/json"
    })
    Observable<ListResponseData<FollowListData>> getFollowListData(@Query("open_id") String openId,
                                                                   @Query("cursor") Integer cursor,
                                                                   @Query("count") Integer count);

    Observable<ListResponseData<VideoListData>> getVideoListData(@Query("open_id") String openId,
                                                                 @Query("cursor") Integer cursor,
                                                                 @Query("count") Integer count);
}