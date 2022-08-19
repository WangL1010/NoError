package com.qxy.NoError.list.model;

import android.util.Log;

import com.qxy.NoError.Database.AppDatabase;
import com.qxy.NoError.MyApplication;
import com.qxy.NoError.list.bean.ListData;
import com.qxy.NoError.list.bean.Version;
import com.qxy.NoError.list.dao.IListDataDao;
import com.qxy.NoError.list.net.IListServer;
import com.qxy.NoError.list.net.ResponseData;
import com.qxy.NoError.utils.NetUtils;

import java.util.List;

import cn.hutool.json.JSONUtil;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * 榜单服务层
 *
 * @author 徐鑫
 */
public class ListModel {
    private static final String TAG = "ListModel";

    public void getListData(Integer type, Integer version, CallBack2DealData callBack) {
        IListServer iListServer = NetUtils.createRetrofit(IListServer.class);
        Observable<ResponseData<ListData>> observable = iListServer.getListData(type, version);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseData<ListData>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull ResponseData<ListData> listResponseData) {
                        Log.d(TAG, "getListData,onNext: " + listResponseData.data.errorCode);
                        if (listResponseData.data.errorCode.equals(ResponseData.TOKEN_OVERDUE_CODE)|| MyApplication.getInstance().get(MyApplication.CLIENT_TOKEN)==null) {
                            //token已过期
                            NetUtils.refreshClientToken(() -> getListData(type, version, callBack));
                        } else if (listResponseData.data.errorCode == 0) {
                            Log.d(TAG, "onNext: 请求成功，数据如下\n" + JSONUtil.toJsonStr(listResponseData));
                            callBack.success(listResponseData.data.list);

                            // 2022/8/13 更新数据库
                            updateDataBase(listResponseData.data.list, type, version);
                        } else {
                            Log.d(TAG, "onNext: 请求失败" + listResponseData.data.description);
                            //处理错误信息
                            callBack.fail(listResponseData.data.description);

                            // 2022/8/13 从数据库中加载
                            getDataFromDataBase(type, version, callBack);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d(TAG, "onError: 请求出错，错误信息：" + e.getMessage() + "造成原因：" + e.getCause());
                        callBack.fail("网络异常");

                        // TODO: 2022/8/13 从数据库中加载
                        getDataFromDataBase(type, version, callBack);
                    }

                    @Override
                    public void onComplete() {

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
         * @param listData 排行榜数据
         */
        void success(List<ListData> listData);

        /**
         * 处理错误
         *
         * @param message 错误信息
         */
        void fail(String message);
    }


    public void getDataFromDataBase(Integer type, Integer version, CallBack2DealData callBack) {
        IListDataDao dao = AppDatabase.getInstance().getListDataDao();

        Single<List<ListData>> single = dao.getListData(type, version);
        single.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<ListData>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull List<ListData> listData) {
                        if (listData == null || listData.isEmpty()) {
                            callBack.fail("并未缓存数据");
                            return;
                        }
                        callBack.success(listData);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        callBack.fail(e.getMessage());
                    }
                });
    }


    /**
     * 更新数据库
     *
     * @param list
     */
    private void updateDataBase(List<ListData> list, Integer type, Integer version) {
        IListDataDao dao = AppDatabase.getInstance().getListDataDao();
        for (ListData data :
                list) {
            data.version = version;
        }

        Single<List<ListData>> single = dao.getListData(type, version);
        single.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<ListData>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull List<ListData> listData) {
                        if (listData == null || listData.isEmpty()) {
                            //数据库中没有数据，直接插入数据库
                            dao.insertList(list).subscribeOn(Schedulers.io()).subscribe();
                            return;
                        }
                        dao.updateListData(list).subscribeOn(Schedulers.io()).subscribe();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d(TAG, "onError: 查询数据库失败！！！" + e.getMessage());
                    }
                });
    }


    /**
     * 获取榜单版本信息
     * @param type 榜单的类型 {@link ListData#type ListData.type}
     * @param callBackDealVersion 操作完成后的回调数据
     */
    public void getVersionData(int type, CallBackDealVersion callBackDealVersion) {
        final int pageSize = 20;
        IListServer retrofit = NetUtils.createRetrofit(IListServer.class);
        Single<ResponseData<Version>> single = retrofit.getVersion(type, 1, pageSize);

        SingleObserver<ResponseData<Version>> observer = new SingleObserver<ResponseData<Version>>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onSuccess(@NonNull ResponseData<Version> versionResponseData) {
                if (versionResponseData.data.errorCode == 0) {
                    callBackDealVersion.success(versionResponseData.data.list);
                    Log.d(TAG, "onSuccess: ");
                    if (versionResponseData.data.hasMore) {
                        Single<ResponseData<Version>> single1 = retrofit.getVersion(type, versionResponseData.data.cursor, pageSize);
                        single1.observeOn(AndroidSchedulers.mainThread())
                                .subscribeOn(Schedulers.io())
                                .subscribe(this);
                    }
                } else if (versionResponseData.data.errorCode.equals(ResponseData.TOKEN_OVERDUE_CODE)){
                    NetUtils.refreshClientToken(() -> getVersionData(type, callBackDealVersion));
                } else {
                    callBackDealVersion.fail(versionResponseData.data.description);
                }
            }

            @Override
            public void onError(@NonNull Throwable e) {
                callBackDealVersion.fail(e.getMessage());
            }
        };
        single.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public interface CallBackDealVersion {
        /**
         * 请求 版本数据成功后的回调函数
         * @param versions 请求成功获取的版本数据
         */
        void success(List<Version> versions);

        /**
         * 请求 版本数据失败后的回调函数
         * @param message 请求失败信息
         */
        void fail(String message);
    }
}
