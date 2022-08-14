package com.qxy.NoError.user.moder;

import android.util.Log;

import com.qxy.NoError.MyApplication;
import com.qxy.NoError.list.bean.ListData;
import com.qxy.NoError.user.bean.FanListData;
import com.qxy.NoError.user.bean.FollowListData;
import com.qxy.NoError.user.net.IUserServer;
import com.qxy.NoError.user.net.UserResponseData;
import com.qxy.NoError.utils.NetUtils;

import java.time.LocalDate;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * 用户页面数据请求
 */
public class UserModel {
    private static final String TAG = "UserModel";


    public void getFanListData(Integer cursor,Integer count,CallBack2DealData callback){
        String openId= MyApplication.getInstance().get(MyApplication.OPEN_ID);
        getFanListData(openId,cursor,count,callback);
    }

    /**
     * 请求粉丝列表
     * @param openId
     * @param cursor
     * @param count
     * @param callback
     */
    public void getFanListData(String openId,Integer cursor,Integer count,CallBack2DealData callback){
        IUserServer iUserServer= NetUtils.createRetrofit(IUserServer.class);
        Observable<UserResponseData<FanListData>> fansListData = iUserServer.getFansListData(openId, cursor, count);
        fansListData.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserResponseData<FanListData>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.d(TAG, "onSubscribe: ");
                    }

                    @Override
                    public void onNext(@NonNull UserResponseData<FanListData> fanListDataUserResponseData) {
                        Log.d(TAG, "onNext: ");
                        callback.dealData(fanListDataUserResponseData.data.list);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d(TAG, "onError: ");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete: ");
                    }
                });


    }

    /**
     * 用于view model的回调接口
     */
    public interface CallBack2DealData<T> {

        /**
         * 处理数据
         * @param list 列表数据：FanListData，FollowListData，VideoListData
         */
       void dealData(List<T> list);
    }
}
