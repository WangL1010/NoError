package com.qxy.NoError.api;

import com.qxy.NoError.Utils.Constants;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 创建Retrofit单例
 */
public class RetrofitManager {
    private static final RetrofitManager outInstance= new RetrofitManager();
    private final Retrofit mRetrofit;

    private RetrofitManager(){
        //创建retrofit
        OkHttpClient okHttpClient=new OkHttpClient().newBuilder()
                .callTimeout(3, TimeUnit.SECONDS)
                .build();
        mRetrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }

    public static RetrofitManager getInstance(){
        return outInstance;
    }
    public Retrofit getRetrofit(){
        return  mRetrofit;
    }
}
