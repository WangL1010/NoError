package com.qxy.NoError;

import android.app.Application;
import android.content.Context;

import com.bytedance.sdk.open.douyin.DouYinOpenApiFactory;
import com.bytedance.sdk.open.douyin.DouYinOpenConfig;


/**
 * 主要功能：自定义{@link Application}
 * since: 2018/12/25
 */
public class MyApplication extends Application {

    public static Context appContext;
    @Override
    public void onCreate() {
        super.onCreate();

        appContext=getApplicationContext();
        String clientkey = "awdtqezt1yg6hmne"; // 需要到开发者网站申请并替换
        DouYinOpenApiFactory.init(new DouYinOpenConfig(clientkey));
    }

    public static Context getAppContext(){
        return appContext;
    }
}
