package com.qxy.NoError.list.vm;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.Pager;
import androidx.paging.PagingConfig;
import androidx.paging.PagingData;
import androidx.paging.PagingLiveData;
import androidx.paging.PagingSource;

import com.qxy.NoError.list.bean.Version;
import com.qxy.NoError.list.model.VersionModel;
import com.qxy.NoError.list.net.ResponseData;
import com.qxy.NoError.list.version.VersionPagingSource;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import kotlin.jvm.functions.Function0;

/**
 * 管理版本数据
 *
 * @author 徐鑫
 */
public class VersionViewModel extends ViewModel {

    private final VersionModel versionModel = new VersionModel();
    private MutableLiveData<Version> selectVersion;
    private MutableLiveData<List<Version>> versionLiveData;

    public LiveData<PagingData<Version>> getVersionData(int type) {
        Pager<Integer, Version> pager = new Pager<>(new PagingConfig(20),
                new Function0<PagingSource<Integer, Version>>() {
                    @Override
                    public PagingSource<Integer, Version> invoke() {
                        return new VersionPagingSource(versionModel, type);
                    }
                });
        return PagingLiveData.cachedIn(PagingLiveData.getLiveData(pager), this);
    }

    public void getData(int type) {
        final int pageSize = 20;
        Single<ResponseData<Version>> single = versionModel.getData(type, 1, pageSize);

        SingleObserver<ResponseData<Version>> observer = new SingleObserver<ResponseData<Version>>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onSuccess(@NonNull ResponseData<Version> versionResponseData) {
                List<Version> value = getVersionLiveData().getValue();
                value.addAll(versionResponseData.data.list);
                getVersionLiveData().setValue(value);
                if (versionResponseData.data.hasMore) {
                    Single<ResponseData<Version>> single1 = versionModel.getVersionData(type, versionResponseData.data.cursor, pageSize);
                    single1.subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(this);
                }
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }
        };
        single.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public MutableLiveData<List<Version>> getVersionLiveData() {
        if (versionLiveData == null) {
            versionLiveData = new MutableLiveData<>(new ArrayList<>());
        }
        return versionLiveData;
    }

    public MutableLiveData<Version> getSelectVersion() {
        if (selectVersion == null) {
            selectVersion = new MutableLiveData<>();
        }
        return selectVersion;
    }
}
