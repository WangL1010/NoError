package com.qxy.NoError.user.moder;

import android.util.Log;

import com.qxy.NoError.MyApplication;
import com.qxy.NoError.user.bean.FanListData;
import com.qxy.NoError.user.bean.FollowListData;
import com.qxy.NoError.user.bean.UserOpenInfo;
import com.qxy.NoError.user.bean.VideoListData;
import com.qxy.NoError.user.net.IUserServer;
import com.qxy.NoError.user.net.ResBody;
import com.qxy.NoError.user.net.UserResponseData;
import com.qxy.NoError.utils.NetUtils;

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

    public void getFollowListData(Integer cursor,Integer count,CallBack2DealData callback){
        String openId= MyApplication.getInstance().get(MyApplication.OPEN_ID);
        getFollowListData(openId,cursor,count,callback);
    }

    public void getVideoListData(Integer cursor,Integer count,CallBack2DealData callback){
        String openId= MyApplication.getInstance().get(MyApplication.OPEN_ID);
        getVideoListData(openId,cursor,count,callback);
    }

    public void getUserOpenInfo(Integer cursor,Integer count,CallBack2DealData callback){
        String openId= MyApplication.getInstance().get(MyApplication.OPEN_ID);
        getUserOpenInfo(openId,cursor,count,callback);
    }
    /**
     * 请求粉丝列表
     * @param openId 用户唯一标志
     * @param cursor 分页游标
     * @param count 每页数量
     * @param callback 回调接口
     */
    public void getFanListData(String openId,Integer cursor,Integer count,CallBack2DealData callback){
        IUserServer iUserServer= NetUtils.createRetrofit(IUserServer.class,MyApplication.CLIENT_TOKEN);
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
                        callback.success(fanListDataUserResponseData.data.list);
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
     * 请求关注列表
     * @param openId 用户唯一标志
     * @param cursor 分页游标
     * @param count 每页数量
     * @param callback 回调接口
     */
    public void getFollowListData(String openId,Integer cursor,Integer count,CallBack2DealData callback){
        IUserServer iUserServer= NetUtils.createRetrofit(IUserServer.class,MyApplication.CLIENT_TOKEN);
        Observable<UserResponseData<FollowListData>> followListData = iUserServer.getFollowListData(openId, cursor, count);
        followListData.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserResponseData<FollowListData>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.d(TAG, "onSubscribe: ");
                    }

                    @Override
                    public void onNext(@NonNull UserResponseData<FollowListData> followListDataUserResponseData) {
                        Log.d(TAG, "onNext: ");
                        callback.success(followListDataUserResponseData.data.list);
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
     * 请求视频列表
     * @param openId 用户唯一标志
     * @param cursor 分页游标
     * @param count 每页数量
     * @param callback 回调接口
     */
    public void getVideoListData(String openId, Integer cursor, Integer count, CallBack2DealData callback){
        IUserServer iUserServer= NetUtils.createRetrofit(IUserServer.class, MyApplication.ACCESS_TOKEN);
        Observable<UserResponseData<VideoListData>> videoListData = iUserServer.getVideoListData(openId, cursor, count);
        videoListData.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserResponseData<VideoListData>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.d(TAG, "onSubscribe: ");
                    }

                    @Override
                    public void onNext(@NonNull UserResponseData<VideoListData> videoListDataUserResponseData) {
                        Log.d(TAG, "onNext: ");
                        callback.success(videoListDataUserResponseData.data.list);
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

    public void getUserOpenInfo(String openId, Integer cursor, Integer count, CallBack2DealData callback){
        IUserServer iUserServer= NetUtils.createRetrofit(IUserServer.class, MyApplication.ACCESS_TOKEN);
        MyApplication instance=MyApplication.getInstance();
        ResBody body=new ResBody(instance.get(MyApplication.ACCESS_TOKEN),instance.get(MyApplication.OPEN_ID));
        Observable<UserOpenInfo> userOpenInfo = iUserServer.getUserOpenInfo(body);
        userOpenInfo.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserOpenInfo>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.d(TAG, "onSubscribe: ");
                    }

                    @Override
                    public void onNext(@NonNull UserOpenInfo userOpenInfo) {
                        Log.d(TAG, "onNext: ");
                        callback.success(userOpenInfo);
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
       void success(List<T> list);

        /**
         * 处理数据
         * @param t UserOpenInfo
         */
       void success(T t);
    }
}
