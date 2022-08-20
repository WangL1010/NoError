package com.qxy.NoError.user.moder;

import android.util.Log;

import com.qxy.NoError.MyApplication;
import com.qxy.NoError.user.bean.FanListData;
import com.qxy.NoError.user.bean.FollowListData;
import com.qxy.NoError.user.bean.UserOpenInfo;
import com.qxy.NoError.user.bean.VideoListData;
import com.qxy.NoError.user.net.IUserServer;
import com.qxy.NoError.user.net.ResBody;
import com.qxy.NoError.user.net.ListResponseData;
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
public class UserModel<T> {
    private static final String TAG = "UserModel";


    public void getFanListData(Integer cursor,Integer count,CallBack2DealData<FanListData> callback){
        String openId= MyApplication.getInstance().get(MyApplication.OPEN_ID);
        getFanListData(openId,cursor,count,callback);
    }

    public void getFollowListData(Integer cursor,Integer count,CallBack2DealData<FollowListData> callback){
        String openId= MyApplication.getInstance().get(MyApplication.OPEN_ID);
        Log.d(TAG, "getFollowListData: openId:"+openId);
        getFollowListData(openId,cursor,count,callback);
    }

    public void getVideoListData(Integer cursor,Integer count,CallBack2DealData<VideoListData> callback){
        String openId= MyApplication.getInstance().get(MyApplication.OPEN_ID);
        Log.d(TAG, "getVideoListData: openId:"+openId);
        getVideoListData(openId,cursor,count,callback);
    }

    /**
     * 请求粉丝列表
     * @param openId 用户唯一标志
     * @param cursor 分页游标
     * @param count 每页数量
     * @param callback 回调接口
     */
    public void getFanListData(String openId,Integer cursor,Integer count,CallBack2DealData<FanListData> callback){
        IUserServer iUserServer= NetUtils.createRetrofit(IUserServer.class,MyApplication.ACCESS_TOKEN);
        Observable<ListResponseData<FanListData>> fansListData = iUserServer.getFansListData(openId, cursor, count);
        fansListData.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ListResponseData<FanListData>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.d(TAG, "onSubscribe: ");
                    }

                    @Override
                    public void onNext(@NonNull ListResponseData<FanListData> fanListDataListResponseData) {
                        Log.d(TAG, "getFanListData:onNext: "+fanListDataListResponseData.data.error_code);
                        Log.d(TAG, "getFanListData:onNext: "+fanListDataListResponseData.data.description);

                        callback.success(fanListDataListResponseData.data.list);
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
    public void getFollowListData(String openId,Integer cursor,Integer count,CallBack2DealData<FollowListData> callback){
        IUserServer iUserServer= NetUtils.createRetrofit(IUserServer.class,MyApplication.ACCESS_TOKEN);
        Observable<ListResponseData<FollowListData>> followListData = iUserServer.getFollowListData(openId, cursor, count);
        followListData.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ListResponseData<FollowListData>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.d(TAG, "onSubscribe: ");
                    }

                    @Override
                    public void onNext(@NonNull ListResponseData<FollowListData> followListDataListResponseData) {
                        Log.d(TAG, "getFollowListData:onNext: "+followListDataListResponseData.extra.description);
                        Log.d(TAG, "getFollowListData:onNext: "+followListDataListResponseData.extra.errorCode);
                        callback.success(followListDataListResponseData.data.list);
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
    public void getVideoListData(String openId,Integer cursor,Integer count,CallBack2DealData<VideoListData> callback){
        IUserServer iUserServer=NetUtils.createRetrofit(IUserServer.class,MyApplication.ACCESS_TOKEN);
        Observable<ListResponseData<VideoListData>> videoListData = iUserServer.getVideoListData(openId, cursor, count);
        videoListData.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ListResponseData<VideoListData>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.d(TAG, "getVideoListData:onSubscribe: "+d.toString());
                    }

                    @Override
                    public void onNext(@NonNull ListResponseData<VideoListData> videoListDataListResponseData) {
                        Log.d(TAG, "getVideoListData:onNext: "+videoListDataListResponseData.data.error_code);
                        Log.d(TAG, "getVideoListData:onNext: "+videoListDataListResponseData.data.list.toString());
                        callback.success(videoListDataListResponseData.data.list);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d(TAG, "getVideoListData:onError: "+e.toString());
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "getVideoListData:onComplete: ");
                    }
                });
    }


    public void getUserOpenInfo(CallBack2DealData<UserOpenInfo> callback){
        IUserServer iUserServer= NetUtils.createRetrofit(IUserServer.class, MyApplication.ACCESS_TOKEN);
        MyApplication instance=MyApplication.getInstance();
        ResBody body=new ResBody(instance.get(MyApplication.ACCESS_TOKEN),instance.get(MyApplication.OPEN_ID));
        Log.d(TAG, "getUserOpenInfo: "+body.toString());
        Observable<UserResponseData> userOpenInfo = iUserServer.getUserOpenInfo(body);
        userOpenInfo.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserResponseData>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.d(TAG, "onSubscribe: ");
                    }

                    @Override
                    public void onNext(@NonNull UserResponseData userResponseData) {
                        String message = userResponseData.message;
                        UserOpenInfo userOpenInfo1 = userResponseData.data;
                        Log.d(TAG, "getUserOpenInfo:onNext: "+message);
                        Log.d(TAG, "getUserOpenInfo:onNext: "+userOpenInfo1);
                        callback.success(userOpenInfo1);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d(TAG, "onError: "+e.toString());
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete: ");
                    }
                });
    }

    /**
     * 从数据库中获取用户公开信息
     * @param callback
     */
    public void getUserOpenInfoFromDataBase(CallBack2DealData<UserOpenInfo> callback){

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

        /**
         * 处理错误
         *
         * @param message 错误信息
         */
        void fail(String message);
    }
}
