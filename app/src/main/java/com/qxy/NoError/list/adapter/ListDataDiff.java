package com.qxy.NoError.list.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.qxy.NoError.list.bean.ListData;

/**
 * 判断两个list data是否相同
 * @author 徐鑫
 */
public class ListDataDiff extends DiffUtil.ItemCallback<ListData> {

    public volatile static ListDataDiff instance;

    private ListDataDiff(){}

    public static ListDataDiff getInstance() {
        if (instance == null) {
            synchronized (ListDataDiff.class) {
                if(instance == null) {
                    instance = new ListDataDiff();
                }
            }
        }
        return instance;
    }

    @Override
    public boolean areItemsTheSame(@NonNull ListData oldItem, @NonNull ListData newItem) {
        return oldItem.id.equals(newItem.id) && oldItem.version.equals(newItem.version);
    }

    @Override
    public boolean areContentsTheSame(@NonNull ListData oldItem, @NonNull ListData newItem) {
        return oldItem.id.equals(newItem.id) && oldItem.version.equals(newItem.version);
    }
}
