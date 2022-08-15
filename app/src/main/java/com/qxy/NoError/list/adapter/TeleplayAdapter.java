package com.qxy.NoError.list.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.qxy.NoError.R;
import com.qxy.NoError.list.bean.ListData;
import com.qxy.NoError.utils.StrUtil;

/**
 * 电视剧榜单的适配器
 * @author 徐鑫
 */
public class TeleplayAdapter extends MyListAdapter<TeleplayAdapter.TeleplayViewHolder> {

    private static volatile TeleplayAdapter instance;

    private TeleplayAdapter() {}


    public static TeleplayAdapter getInstance() {
        if (instance == null) {
            synchronized (TeleplayAdapter.class) {
                if (instance == null) {
                    instance = new TeleplayAdapter();
                }
            }
        }
        return instance;
    }

    @NonNull
    @Override
    public TeleplayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        TeleplayViewHolder holder;
        if (viewType == HEADER_VIEW_TYPE) {
            holder = new TeleplayViewHolder(
                    LayoutInflater.from(parent.getContext()).inflate(
                            R.layout.layout_list_head,
                            parent,
                            false
                    )
            );
            holder.tvVersion.setText(getVersionMsg());
            holder.tvVersion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Navigation.findNavController(v).navigate(R.id.versionFragment);
                }
            });
        } else {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View itemView = layoutInflater.inflate(R.layout.item_teleplay, parent, false);
            holder = new TeleplayViewHolder(itemView);
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull TeleplayViewHolder holder, int position) {
        if (position == 0) {
            return;
        }
        ListData listData = getItem(position);
        Glide.with(holder.ivIcon)
                .load(listData.poster)
                .placeholder(R.drawable.ic_list)
                .into(holder.ivIcon);

        holder.tvRank.setText(StrUtil.formatRank(position, 3, "TOP "));
        holder.tvName.setText(listData.name);
        holder.tvHot.setText(StrUtil.formatFromInt(listData.hot, 4, "万"));
        holder.tvDirector.setText(StrUtil.formatStr(listData.directors, ",", 2));

        holder.tvActor.setText(StrUtil.formatStr(listData.actors, ",", 2));
        holder.tvArea.setText(StrUtil.formatStr(listData.areas, ",", 2));
        holder.tvReleaseTime.setText(listData.releaseDate);
        if (listData.tags == null || listData.tags.isEmpty()) {
            holder.tvTags.setVisibility(View.GONE);
        } else {
            holder.tvTags.setText(StrUtil.formatStr(listData.tags, ",", 2));
        }
    }

    public static class TeleplayViewHolder extends RecyclerView.ViewHolder {

        TextView tvName, tvHot, tvTags, tvReleaseTime,
                tvVersion, tvEnName, tvArea, tvRank,
                tvDirector, tvActor;
        ImageView ivIcon;

        public TeleplayViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_teleplay_name);
            tvHot = itemView.findViewById(R.id.tv_teleplay_hot);
            tvEnName = itemView.findViewById(R.id.tv_teleplay_en_name);
            tvTags = itemView.findViewById(R.id.tv_teleplay_tags);
            tvReleaseTime = itemView.findViewById(R.id.tv_teleplay_release_time);
            tvArea = itemView.findViewById(R.id.tv_teleplay_release_area);
            ivIcon = itemView.findViewById(R.id.teleplay_icon);
            tvVersion = itemView.findViewById(R.id.tvVersion);
            tvRank = itemView.findViewById(R.id.tv_teleplay_rank);
            tvDirector = itemView.findViewById(R.id.tv_teleplay_director);
            tvActor = itemView.findViewById(R.id.tv_teleplay_actor);
        }
    }
}
