package com.qxy.NoError.user.repository;

import android.util.Log;

import com.qxy.NoError.user.net.RetrofitApi;
import com.qxy.NoError.user.net.RetrofitManager;
import com.qxy.NoError.user.bean.UserInfo;
import com.qxy.NoError.user.bean.UserInfo2Body;
import com.qxy.NoError.user.net.IUserInfoCallback;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Repository层
 * 通过Retrofit或room数据库获取数据
 */
public class UserInfoRepository {
    Retrofit retrofit;
    RetrofitApi retrofitApi;
    IUserInfoCallback mIUserInfoLoad;
    public static final String TAG="UserInfoRepository";
    
    
    public UserInfoRepository(){
        retrofit= RetrofitManager.getInstance().getRetrofit();
        retrofitApi=retrofit.create(RetrofitApi.class);
    }

    /**
     * 关联回调
     * @param iUserInfoLoad
     */
    public void registerCallback(IUserInfoCallback iUserInfoLoad){
        mIUserInfoLoad=iUserInfoLoad;
    }


    /**
     * 获取数据并把数据传给viewModel
     * @param headers
     * @param body
     */
    public void getUserInfo(Map<String,String> headers, UserInfo2Body body){
        Call<UserInfo> task = retrofitApi.getUserInfo(headers, body);
        task.enqueue(new Callback<UserInfo>() {
            @Override
            public void onResponse(Call<UserInfo> call, Response<UserInfo> response) {
                try {
                        /**
                         * 把数据回调给ViewModel
                         */
                        mIUserInfoLoad.loadUserInfo(response.body());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Log.d(TAG, "onResponse: "+response.body());
            }

            @Override
            public void onFailure(Call<UserInfo> call, Throwable t) {
                Throwable a = t;
            }
        });
    }

}
