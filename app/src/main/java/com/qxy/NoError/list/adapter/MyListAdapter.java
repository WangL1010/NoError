package com.qxy.NoError.list.adapter;

import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.qxy.NoError.list.bean.ListData;
import com.qxy.NoError.list.bean.Version;

/**
 * 榜单数据适配器的基本
 *
 * @author 徐鑫
 */
public abstract class MyListAdapter<T extends RecyclerView.ViewHolder> extends ListAdapter<ListData, T> {

    protected static final int NORMAL_VIEW_TYPE = 0;
    protected static final int HEADER_VIEW_TYPE = 1;

    protected Version mVersion = new Version();

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

    public Version getVersion() {
        return mVersion;
    }

    public void setVersion(Version mVersion) {
        this.mVersion = mVersion;
    }

    protected String getVersionMsg() {
        return "第" + mVersion.version+ "期 " + mVersion.startTime + "--" + mVersion.endTime;
    }
}
