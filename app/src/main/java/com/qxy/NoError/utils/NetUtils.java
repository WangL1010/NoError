package com.qxy.NoError.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * 网络相关的工具类
 *
 * @author 徐鑫
 */
public class NetUtils {
    private static final String IP_PRE = "https://open.douyin.com/";

    public static <T> T createRetrofit(Class<T> tClass) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);

        Retrofit build = new Retrofit.Builder()
                .baseUrl(IP_PRE)
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(JacksonConverterFactory.create(mapper))
                .build();
        return build.create(tClass);
    }
}
