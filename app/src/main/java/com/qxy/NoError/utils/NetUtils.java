package com.qxy.NoError.utils;

import androidx.annotation.NonNull;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
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

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @NonNull
                    @Override
                    public Response intercept(@NonNull Chain chain) throws IOException {
                        Request request = chain.request()
                                .newBuilder()
                                .addHeader("access-token", "clt.ef1bb6c5725e7bef8f54da9f31ef5660xHNjOj9Za3t0UohNOYBogP0pAvNS")
                                .addHeader("Content-Type", "application/json")
                                .build();
                        return chain.proceed(request);
                    }
                }).build();
        Retrofit build = new Retrofit.Builder()
                .baseUrl(IP_PRE)
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(JacksonConverterFactory.create(mapper))
                .client(okHttpClient)
                .build();
        return build.create(tClass);
    }

    public static OkHttpClient getOkHttpClient() {
        return new OkHttpClient.Builder()
                .build();
    }
}
