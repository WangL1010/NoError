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

    //获取listDataDao对数据库进行操作
    private ListDataDao listDataDao=AppDatabase.getDatabase().getListDataDao();


    public void getListData(Integer type,CallBack2DealData callBack) {
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
                            callBack.dealData(LocalDate.parse(movieResponseData.data.activeTime)
                                    , movieResponseData.data.description
                                    , movieResponseData.data.list);

                            //把请求到的数据更新到数据库
                            listDataDao.deleteAll();
                            for (ListData listData : movieResponseData.data.list) {
                                listDataDao.insert(listData);
                            }
                        } else {
                            Log.d(TAG, "onNext: 请求失败" + movieResponseData.data.description);
                        }
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
    public interface CallBack2DealData {
        /**
         * 处理数据
         *
         * @param localDate   排行榜时间
         * @param movieList   排行榜数据
         * @param description 请求数据失败的描述信息，请求数据成功没有描述
         */
        void dealData(LocalDate localDate, String description, List<ListData> movieList);
    }

}
