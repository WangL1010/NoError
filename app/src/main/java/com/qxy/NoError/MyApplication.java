package com.qxy.NoError;

import android.app.Application;

import com.bytedance.sdk.open.douyin.DouYinOpenApiFactory;
import com.bytedance.sdk.open.douyin.DouYinOpenConfig;
import com.qxy.NoError.utils.Constants;

import java.util.HashMap;
import java.util.Map;

import cn.hutool.json.JSONUtil;


/**
 * 主要功能：自定义{@link Application}
 * since: 2018/12/25
 */
public class MyApplication extends Application {

    public static final String AUTH_CODE = "auth_code";
    public static final String ACCESS_TOKEN = "access-token";
    public static final String CLIENT_TOKEN = "client-token";

    public static MyApplication instance;

    public static MyApplication getInstance() {
        //MyApplication时最先被加载的，所以在调用getInstance时，必然不会返回null
        return instance;
    }

    /**
     * 存储一些全局变量，即使需要存储object类，也将其转换为json格式存储，减少内存的使用
     */
    private final Map<String, String> map = new HashMap<>();

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        // 需要到开发者网站申请并替换
        DouYinOpenApiFactory.init(new DouYinOpenConfig(Constants.CLIENT_KEY));
    }

    public void put(String key, String data) {
        map.put(key, data);
    }

    public void putObj(String key, Object obj) {
        map.put(key, JSONUtil.toJsonStr(obj));
    }

    public String get(String key) {
        return map.get(key);
    }

    public <T> T getObj(String key, Class<T> tClass) {
        String json = map.get(key);
        return JSONUtil.toBean(json, tClass);
    }
}
