package com.qxy.NoError.list.adapter;

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.qxy.NoError.R;
import com.qxy.NoError.list.bean.ListData;

import java.util.HashMap;
import java.util.List;

/**
 * 电视剧榜单的适配器
 * @author 徐鑫
 */
public class TeleplayAdapter extends RecyclerView.Adapter<TeleplayAdapter.TeleplayViewHolder> {

    private List<ListData> listData;

    @NonNull
    @Override
    public TeleplayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_teleplay, parent, false);

        return new TeleplayViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TeleplayViewHolder holder, int position) {
        ListData listData = this.listData.get(position);
        Glide.with(holder.ivIcon)
                .load(listData.poster)
                .placeholder(R.drawable.ic_list)
                .into(holder.ivIcon);
        holder.tvHot.setText(String.valueOf(listData.hot));
        holder.tvName.setText(listData.name);
        StringBuilder stringBuilder = new StringBuilder();
        if (listData.tags != null) {
            for (String str :
                    listData.tags) {
                stringBuilder.append(str).append(',');
            }
        }
        holder.tvType.setText(stringBuilder);
        holder.tvReleaseTime.setText(listData.releaseDate);
    }

    @Override
    public int getItemCount() {
        return listData == null ? 0 : listData.size();
    }

    public static class TeleplayViewHolder extends RecyclerView.ViewHolder {

        TextView tvName, tvHot, tvType, tvReleaseTime;
        ImageView ivIcon;

        public TeleplayViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_teleplay_name);
            tvHot = itemView.findViewById(R.id.tv_teleplay_hot);
            tvType = itemView.findViewById(R.id.tv_teleplay_type);
            tvReleaseTime = itemView.findViewById(R.id.tv_teleplay_release_time);
            ivIcon = itemView.findViewById(R.id.iv_teleplay_icon);
        }
    }

    public TeleplayAdapter(List<ListData> listData) {
        this.listData = listData;
    }

    public TeleplayAdapter() {
    }

    public void setListData(List<ListData> listData) {
        this.listData = listData;
    }
}
