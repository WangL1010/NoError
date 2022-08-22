package com.qxy.NoError.list.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.navigation.NavController;
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
    private final DialogFragment dialogFragment;
    private final static int NEW_VERSION = 0;
    private final static int OLD_VERSION = 1;


    public VersionAdapter(ListViewModel versionViewModel, DialogFragment dialogFragment) {
        super(new VersionComparator());
        this.mViewModel = versionViewModel;
        this.dialogFragment = dialogFragment;
    }

    @NonNull
    @Override
    public VersionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_version, parent, false);
        VersionViewHolder holder = new VersionViewHolder(view);
        if (viewType == NEW_VERSION) {
            holder.textView3.setVisibility(View.GONE);
            holder.textView5.setVisibility(View.GONE);
            holder.textView6.setVisibility(View.GONE);
            holder.tvEndTime.setVisibility(View.GONE);
            holder.tvStartTime.setVisibility(View.GONE);
        } else {
            holder.textView3.setVisibility(View.VISIBLE);
            holder.textView5.setVisibility(View.VISIBLE);
            holder.textView6.setVisibility(View.VISIBLE);
            holder.tvEndTime.setVisibility(View.VISIBLE);
            holder.tvStartTime.setVisibility(View.VISIBLE);
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull VersionViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: ");
        Version version;
        if (position == 0) {
            version = new Version();
            version.type = mViewModel.getSelectVersion().getValue().type;
            version.version = 0;
            holder.tvVersion.setText("最新榜单");
        } else {
            version = getItem(position - 1);
            if (version == null) {
                return;
            }
            holder.tvVersion.setText(String.valueOf(version.version));
            holder.tvStartTime.setText(version.startTime.substring(2, 10));
            holder.tvEndTime.setText(version.endTime.substring(2, 10));
        }
        holder.itemView.setOnClickListener(v -> {
            mViewModel.getSelectVersion().setValue(version);
            dialogFragment.dismiss();
        });
    }

    @Override
    public int getItemCount() {
        return super.getItemCount() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        return position == 0 ? NEW_VERSION : OLD_VERSION;
    }

    public static class VersionViewHolder extends RecyclerView.ViewHolder {

        TextView tvVersion, tvStartTime, tvEndTime;

        TextView textView3, textView6,textView5;

        public VersionViewHolder(@NonNull View itemView) {
            super(itemView);
            tvVersion = itemView.findViewById(R.id.tv_version);
            tvStartTime = itemView.findViewById(R.id.tv_start_time);
            tvEndTime = itemView.findViewById(R.id.tv_end_time);
            textView3 = itemView.findViewById(R.id.textView3);
            textView5 = itemView.findViewById(R.id.textView5);
            textView6 = itemView.findViewById(R.id.textView6);

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
