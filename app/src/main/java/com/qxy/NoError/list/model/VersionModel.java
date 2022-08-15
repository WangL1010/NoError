package com.qxy.NoError.list.model;

import android.util.Log;

import com.qxy.NoError.list.bean.Version;
import com.qxy.NoError.list.net.IListServer;
import com.qxy.NoError.list.net.ResponseData;
import com.qxy.NoError.utils.NetUtils;

import io.reactivex.rxjava3.core.Single;

/**
 * 获取版本数据的模型类
 *
 * @author 徐鑫
 */
public class VersionModel {
    private static final String TAG = "VersionModel";

    public Single<ResponseData<Version>> getVersionData(int type, int nextPageNum, int pageSize) {

        Log.d(TAG, "getVersionData: ");

        IListServer retrofit = NetUtils.createRetrofit(IListServer.class);
        return retrofit.getVersion(type, nextPageNum, pageSize);
    }

    public Single<ResponseData<Version>> getData(int type, int nextPageNum, int pageSize) {
        IListServer retrofit = NetUtils.createRetrofit(IListServer.class);
        return retrofit.getVersion(type, nextPageNum, pageSize);


//        List<Version> versions = new ArrayList<>();
//        IListServer retrofit = NetUtils.createRetrofit(IListServer.class);
//        Single<ResponseData<Version>> single = retrofit.getVersion(type, 0, 20);
//        SingleObserver<ResponseData<Version>> observer = new SingleObserver<ResponseData<Version>>() {
//            @Override
//            public void onSubscribe(@NonNull Disposable d) {
//
//            }
//
//            @Override
//            public void onSuccess(@NonNull ResponseData<Version> versionResponseData) {
//                versions.addAll(versionResponseData.data.list);
//                if (versionResponseData.data.hasMore) {
//                    Single<ResponseData<Version>> single1 = retrofit.getVersion(type, versionResponseData.data.cursor, 20);
//                    single1.subscribeOn(Schedulers.io()).observeOn(Schedulers.io()).subscribe(this);
//                }
//            }
//
//            @Override
//            public void onError(@NonNull Throwable e) {
//
//            }
//        };
//        single.subscribeOn(Schedulers.io())
//                .observeOn(Schedulers.io())
//                .subscribe(observer);
//        return Single.create(new SingleOnSubscribe<List<Version>>() {
//            @Override
//            public void subscribe(@NonNull SingleEmitter<List<Version>> emitter) throws Throwable {
//                emitter.onSuccess(versions);
//            }
//        });
    }
}
