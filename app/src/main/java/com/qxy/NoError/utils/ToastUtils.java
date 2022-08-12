package com.qxy.NoError.utils;

import android.widget.Toast;

import com.qxy.NoError.MyApplication;

/**
 * 土司工具
 * @author 徐鑫
 */
public class ToastUtils {
    public static void show(String msg) {
        Toast.makeText(MyApplication.getAppContext(), msg, Toast.LENGTH_SHORT).show();
    }
}
