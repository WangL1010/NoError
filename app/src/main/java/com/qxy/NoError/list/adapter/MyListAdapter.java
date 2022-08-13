package com.qxy.NoError.list.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.qxy.NoError.list.bean.ListData;

/**
 * 榜单数据适配器的基本
 *
 * @author 徐鑫
 */
public abstract class MyListAdapter<T extends RecyclerView.ViewHolder> extends ListAdapter<ListData, T> {

    protected static final int NORMAL_VIEW_TYPE = 0;
    protected static final int HEADER_VIEW_TYPE = 1;

    protected MyListAdapter() {
        super(ListDataDiff.getInstance());
    }

    @Override
    public int getItemViewType(int position) {
        return position == 0 ? HEADER_VIEW_TYPE : NORMAL_VIEW_TYPE;
    }

    @Override
    public int getItemCount() {
        return super.getItemCount() + 1;
    }

    @Override
    protected ListData getItem(int position) {
        return super.getItem(position - 1);
    }
}
