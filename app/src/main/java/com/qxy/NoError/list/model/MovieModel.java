package com.qxy.NoError.list.model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.qxy.NoError.list.bean.Movie;
import com.qxy.NoError.list.net.IListServer;
import com.qxy.NoError.list.net.ResponseData;
import com.qxy.NoError.utils.NetUtils;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * 向view model 提供电影榜单数据
 *
 * @author 徐鑫
 */
public class MovieModel {

    private static final String TAG = "MovieModel";

    public void getMovieList(CallBack2DealData callBack) {
        IListServer iListServer = NetUtils.createRetrofit(IListServer.class);
        Observable<ResponseData<Movie>> observable = iListServer.getMovieList();
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseData<Movie>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void onNext(@NonNull ResponseData<Movie> movieResponseData) {
                        Log.d(TAG, "onNext: 请求成功，数据如下\n" + JSONUtil.toJsonStr(movieResponseData));
                        callBack.dealData(LocalDate.parse(movieResponseData.data.activeTime)
                                , movieResponseData.data.description
                                , movieResponseData.data.list);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d(TAG, "onError: 请求出错，错误信息：" + e.getMessage() + "造成原因：" + e.getCause());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public HashMap<String, Bitmap> getBitmapsFromPaths(List<String> paths) {
        HashMap<String, Bitmap> bitmapHash = new HashMap<>(paths.size());
        for (String path :
                paths) {
            getBitmapFromPath(path, bitmap -> bitmapHash.put(path, bitmap));
        }
        return bitmapHash;
    }

    private void getBitmapFromPath(String path, CallBack2DealBitmaps dealData) {
        if (StrUtil.isEmpty(path)) {
            dealData.dealData(null);
            return;
        }
        Request request = new Request.Builder().url(path).build();
        Call call = NetUtils.getOkHttpClient().newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@androidx.annotation.NonNull Call call, @androidx.annotation.NonNull IOException e) {

            }

            @Override
            public void onResponse(@androidx.annotation.NonNull Call call, @androidx.annotation.NonNull Response response) throws IOException {
                ResponseBody body = response.body();
                if (body == null) {
                    dealData.dealData(null);
                    return;
                }
                byte[] bytes = body.bytes();
                Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                dealData.dealData(bitmap);
            }
        });
    }

    /**
     * 用于view model的回调接口
     */
    public interface CallBack2DealData {
        /**
         * 处理数据
         *
         * @param localDate   排行榜时间
         * @param movieList   排行榜数据
         * @param description 请求数据失败的描述信息，请求数据成功没有描述
         */
        void dealData(LocalDate localDate, String description, List<Movie> movieList);
    }

    /**
     * 回调接口，用于处理bitmap
     */
    public interface CallBack2DealBitmaps {
        /**
         * 处理bitmap
         * @param bitmap 数据
         */
        void dealData(Bitmap bitmap);
    }
}
