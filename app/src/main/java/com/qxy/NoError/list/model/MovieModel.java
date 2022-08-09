package com.qxy.NoError.list.model;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.qxy.NoError.list.bean.Movie;
import com.qxy.NoError.list.net.IListServer;
import com.qxy.NoError.list.net.ResponseData;
import com.qxy.NoError.utils.NetUtils;

import java.time.LocalDate;
import java.util.List;

import cn.hutool.json.JSONUtil;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * 向view model 提供电影榜单数据
 *
 * @author 徐鑫
 */
public class MovieModel {

    private static final String TAG = "MovieModel";

    public void getMovieList(CallBack callBack) {
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

    /**
     * 用于view model的回调接口
     */
    public interface CallBack {
        /**
         * 处理数据
         *
         * @param localDate   排行榜时间
         * @param movieList   排行榜数据
         * @param description 请求数据失败的描述信息，请求数据成功没有描述
         */
        void dealData(LocalDate localDate, String description, List<Movie> movieList);
    }
}
