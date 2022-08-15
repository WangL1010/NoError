package com.qxy.NoError.list.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.qxy.NoError.R;
import com.qxy.NoError.list.bean.Version;
import com.qxy.NoError.list.vm.ListViewModel;

import cn.hutool.json.JSONUtil;

/**
 * version适配器，用于配置version数据UI
 *
 * @author 徐鑫
 */
public class VersionAdapter extends ListAdapter<Version, VersionAdapter.VersionViewHolder> {

    private static final String TAG = "BriefAdapter";
    private final ListViewModel mViewModel;

    public VersionAdapter(ListViewModel versionViewModel) {
        super(new VersionComparator());
        this.mViewModel = versionViewModel;
    }

    @NonNull
    @Override
    public VersionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_version, parent, false);
        return new VersionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VersionViewHolder holder, int position) {
        Version version = getItem(position);
        Log.d(TAG, "onBindViewHolder: " + JSONUtil.toJsonStr(version));
        if (version == null) {
            return;
        }
        holder.itemView.setOnClickListener(v -> {
            mViewModel.getSelectVersion().setValue(version);
            Navigation.findNavController(v).popBackStack();
        });
        Log.d(TAG, "onBindViewHolder: " + JSONUtil.toJsonStr(version));

        holder.tvVersion.setText(version.version);
        holder.tvStartTime.setText(version.startTime.substring(2, 10));
        holder.tvEndTime.setText(version.endTime.substring(2, 10));
    }

    public static class VersionViewHolder extends RecyclerView.ViewHolder {

        TextView tvVersion, tvStartTime, tvEndTime;

        public VersionViewHolder(@NonNull View itemView) {
            super(itemView);
            tvVersion = itemView.findViewById(R.id.tv_version);
            tvStartTime = itemView.findViewById(R.id.tv_start_time);
            tvEndTime = itemView.findViewById(R.id.tv_end_time);
        }
    }

    public static class VersionComparator extends DiffUtil.ItemCallback<Version> {
        @Override
        public boolean areItemsTheSame(@NonNull Version oldItem,
                                       @NonNull Version newItem) {
            return oldItem.version.equals(newItem.version);
        }

        @Override
        public boolean areContentsTheSame(@NonNull Version oldItem,
                                          @NonNull Version newItem) {
            return oldItem.version.equals(newItem.version);
        }
    }

}
