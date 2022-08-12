package com.qxy.NoError.list.vm;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.qxy.NoError.list.bean.ListData;
import com.qxy.NoError.list.model.ListModel;
import com.qxy.NoError.utils.ToastUtils;

import java.time.LocalDate;
import java.util.List;

/**
 * 管理榜单的view model
 *
 * @author 徐鑫
 */
public class ListViewModel extends ViewModel {

    /**
     * 榜单数据
     */
    private MutableLiveData<List<ListData>> listData;
    /**
     * 上映时间
     */
    private LocalDate date;

    /**
     * 对外可观察的对象，用于确定是否从网络中请求数据成功
     */
    private MutableLiveData<Boolean> success;
    ListModel listModel = new ListModel();

    public void requestDataFromNet(Integer type) {
        requestDataFromNet(type, 0);
    }

    /**
     * 从网络中请求数据
     * @param type 数据类型{@link ListData#type ListData.type}
     * @param version 数据版本{@link ListData#version ListData.version}
     */
    public void requestDataFromNet(Integer type, Integer version) {
        listModel.getListData(type, version, new ListModel.CallBack2DealData() {
            @Override
            public void success(LocalDate localDate, List<ListData> listData) {
                ListViewModel.this.date = localDate;
                getListData().setValue(listData);
                getSuccess().setValue(true);
            }

            @Override
            public void fail(String message) {
                ToastUtils.show(message);
                getSuccess().setValue(false);
            }
        });

    }

    /**
     * 从数据库中获取数据
     * @param type 数据类型{@link ListData#type ListData.type}
     * @param version 数据版本{@link ListData#version ListData.type}
     */
    public void getDataFromDataBase(Integer type, Integer version) {

    }

    public LocalDate getDate() {
        return date;
    }

    public MutableLiveData<Boolean> getSuccess() {
        if (this.success == null) {
            this.success = new MutableLiveData<>();
        }
        return success;
    }

    public MutableLiveData<List<ListData>> getListData() {
        if (this.listData == null) {
            this.listData = new MutableLiveData<>();
        }
        return listData;
    }
}
