package com.qxy.NoError;

import android.util.Log;

import com.qxy.NoError.Database.AppDatabase;
import com.qxy.NoError.list.bean.ListData;
import com.qxy.NoError.list.dao.IListDataDao;

import org.junit.Test;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class TestDao {
    @Test
    public void test() {
        IListDataDao dao = AppDatabase.getInstance().getListDataDao();

        Single<List<ListData>> single = dao.getListData(1, 0);
        single.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<ListData>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull List<ListData> listData) {
                        if (listData == null || listData.isEmpty()) {

                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                });
    }
}
