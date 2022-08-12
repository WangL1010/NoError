package com.qxy.NoError.list.model;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.qxy.NoError.Database.AppDatabase;
import com.qxy.NoError.list.bean.ListData;
import com.qxy.NoError.list.dao.ListDataDao;
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
 * 榜单服务层
 *
 * @author 徐鑫
 */
public class ListModel {
    private static final String TAG = "ListModel";

    /**
     * 获取listDataDao对数据库进行操作
     */
    private final ListDataDao listDataDao = AppDatabase.getDatabase().getListDataDao();

    public void getListData(Integer type, CallBack2DealData callBack) {
        getListData(type, 0, callBack);
    }

    public void getListData(Integer type, Integer version, CallBack2DealData callBack) {
        IListServer iListServer = NetUtils.createRetrofit(IListServer.class);
        Observable<ResponseData<ListData>> observable = iListServer.getListData(type, version);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseData<ListData>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void onNext(@NonNull ResponseData<ListData> movieResponseData) {
                        Log.d(TAG, "onNext: " + movieResponseData.data.errorCode);
                        if (movieResponseData.data.errorCode.equals(ResponseData.TOKEN_OVERDUE_CODE)) {
                            //token已过期
                            NetUtils.refreshClientToken(() -> getListData(type, version, callBack));
                        } else if (movieResponseData.data.errorCode == 0) {
                            Log.d(TAG, "onNext: 请求成功，数据如下\n" + JSONUtil.toJsonStr(movieResponseData));
                            callBack.success(LocalDate.parse(movieResponseData.data.activeTime)
                                    , movieResponseData.data.list);

                            // 向数据库中更新type字段对应的数据
                            listDataDao.deleteByTypeVersion(type, version);
                            for (ListData listData : movieResponseData.data.list) {
                                try {
                                    // 数据库中存在相同的数据会抛异常
                                    listDataDao.insert(listData);
                                } catch (Exception e) {
                                    //e.printStackTrace();
                                }
                            }
                        } else {
                            Log.d(TAG, "onNext: 请求失败" + movieResponseData.data.description);
                            // 请求失败返回数据库的数据
                            List<ListData> data = listDataDao.getDataByTypeVersion(type, version);
                            callBack.success(LocalDate.now(), data);

                            //处理错误信息
                            callBack.fail(movieResponseData.data.description);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d(TAG, "onError: 请求出错，错误信息：" + e.getMessage() + "造成原因：" + e.getCause());

                        //请求失败返回数据库的数据
                        List<ListData> data = listDataDao.getDataByTypeVersion(type, version);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            callBack.success(LocalDate.now(), data);
                        }
                        callBack.fail("网络异常");
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
         * @param localDate 排行榜时间
         * @param listData  排行榜数据
         */
        void success(LocalDate localDate, List<ListData> listData);

        /**
         * 处理错误
         *
         * @param message 错误信息
         */
        void fail(String message);
    }

}
