package com.qxy.NoError.api;

import com.qxy.NoError.model.UserInfo;
import com.qxy.NoError.model.UserInfo2Body;

import java.util.Map;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface RetrofitApi {

    @POST("oauth/userinfo/")
    Call<UserInfo> getUserInfo(@HeaderMap Map<String,String> headers, @Body UserInfo2Body body);
}
