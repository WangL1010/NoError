package com.qxy.NoError.list.vm;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.qxy.NoError.list.bean.ListData;
import com.qxy.NoError.list.bean.Version;
import com.qxy.NoError.list.model.ListModel;
import com.qxy.NoError.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 管理榜单的view model
 *
 * @author 徐鑫
 */
public class ListViewModel extends ViewModel {

    private static final String TAG = "ListViewModel";

    /**
     * 榜单数据
     */
    private MutableLiveData<List<ListData>> listData;

    /**
     * 对外可观察的对象，用于确定是否从网络中请求数据成功
     */
    private MutableLiveData<Boolean> success;
    private final List<Version> versionList = new ArrayList<>();
    private MutableLiveData<Version> selectVersion;

    ListModel listModel = new ListModel();

    /**
     * 从网络中请求数据
     * @param type 数据类型{@link ListData#type ListData.type}
     * @param version 数据版本{@link ListData#version ListData.version}
     */
    public void requestDataFromNet(Integer type, Integer version) {
        listModel.getListData(type, version, new ListModel.CallBack2DealData() {
            @Override
            public void success(List<ListData> listData) {
                if (listData == null || listData.isEmpty()) {
                    return;
                }
                for (ListData data :
                        listData) {
                    data.version = version;
                }
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

    public void requireVersionData(int type) {
        listModel.getVersionData(type, new ListModel.CallBackDealVersion() {
            @Override
            public void success(List<Version> versions) {
                if (versions != null) {
                    Log.d(TAG, "success: ");
                    versionList.addAll(versions);
                }
            }

            @Override
            public void fail(String message) {
                ToastUtils.show(message);
            }
        });
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

    public List<Version> getVersionList() {
        return versionList;
    }

    public MutableLiveData<Version> getSelectVersion() {
        if (selectVersion == null) {
            Version version = new Version();
            selectVersion = new MutableLiveData<>(new Version());
        }
        return selectVersion;
    }
    public MutableLiveData<Version> getSelectVersion(int type) {
        if (selectVersion == null) {
            Version version = new Version();
            version.type = type;
            selectVersion = new MutableLiveData<>(version);
        }
        return selectVersion;
    }
}
