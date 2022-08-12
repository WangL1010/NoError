package com.qxy.NoError.list.adapter;

import androidx.recyclerview.widget.RecyclerView;

import com.qxy.NoError.list.bean.ListData;

import java.util.List;

/**
 * 榜单数据适配器的基本
 * @author 徐鑫
 */
public abstract class MyListAdapter<T extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<T> {

    protected List<ListData> listData;

    public List<ListData> getListData() {
        return listData;
    }

    public void setListData(List<ListData> listData) {
        this.listData = listData;
    }
}
