package com.qxy.NoError.list.vm;

import android.graphics.Bitmap;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.qxy.NoError.list.bean.ListData;
import com.qxy.NoError.list.model.ListModel;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

/**
 * 管理榜单的view model
 *
 * @author 徐鑫
 */
public class ListViewModel extends ViewModel {

    private MutableLiveData<List<ListData>> liveData;
    private MutableLiveData<LocalDate> date;
    private MutableLiveData<HashMap<String, Bitmap>> bitmapHash;
    ListModel listModel = new ListModel();

    public void requestData(Integer type) {
        requestData(type, 0);
    }

    public void requestData(Integer type, Integer version) {
        listModel.getListData(type, version, (localDate, description, movieList) -> {
            getDate().setValue(localDate);
            getLiveData().setValue(movieList);
        });
    }

    public MutableLiveData<LocalDate> getDate() {
        if (date == null) {
            date = new MutableLiveData<>();
        }
        return date;
    }

    public MutableLiveData<List<ListData>> getLiveData() {
        if (liveData == null) {
            liveData = new MutableLiveData<>();
        }
        return liveData;
    }
}
