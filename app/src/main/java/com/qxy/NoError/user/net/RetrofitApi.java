package com.qxy.NoError.user.net;

import com.qxy.NoError.user.bean.UserInfo;
import com.qxy.NoError.user.bean.UserInfo2Body;

import java.util.Map;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;

public interface RetrofitApi {

    @POST("oauth/userinfo/")
    Call<UserInfo> getUserInfo(@HeaderMap Map<String,String> headers, @Body UserInfo2Body body);
}
