package com.qxy.NoError.list.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.util.StringUtil;

import com.bumptech.glide.Glide;
import com.qxy.NoError.R;
import com.qxy.NoError.list.bean.ListData;
import com.qxy.NoError.utils.StrUtil;

/**
 * 电影榜单的适配器
 *
 * @author 徐鑫
 */
public class VarietyAdapter extends MyListAdapter<VarietyAdapter.MovieViewHolder> {

    private static volatile VarietyAdapter instance;

    private VarietyAdapter() {}


    public static VarietyAdapter getInstance() {
        if (instance == null) {
            synchronized (VarietyAdapter.class) {
                if (instance == null) {
                    instance = new VarietyAdapter();
                }
            }
        }
        return instance;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MovieViewHolder holder;
        if (viewType == HEADER_VIEW_TYPE) {
            holder = new MovieViewHolder(
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
            View itemView = layoutInflater.inflate(R.layout.item_variety, parent, false);
            holder = new MovieViewHolder(itemView);
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        if (position == 0) {
            return;
        }

        ListData listData = getItem(position);
        holder.tvName.setText(listData.name);
        holder.tvHot.setText(StrUtil.formatFromInt(listData.hot, 4, "万"));
        Glide.with(holder.icon)
                .load(listData.poster)
                .placeholder(R.drawable.ic_list)
                .into(holder.icon);

        holder.tvEnName.setText(listData.nameEn);
        holder.tvRank.setText(StrUtil.formatRank(position, 3, "TOP "));

        holder.tvDirector.setText(StrUtil.formatStr(listData.directors, ",", 2));
    }

    static class MovieViewHolder extends RecyclerView.ViewHolder {

        ImageView icon;
        TextView tvName, tvHot, tvVersion, tvDirector, tvEnName, tvRank;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_variety_name);
            tvHot = itemView.findViewById(R.id.tv_variety_hot);
            icon = itemView.findViewById(R.id.variety_icon);
            tvDirector = itemView.findViewById(R.id.tv_variety_director);
            tvEnName = itemView.findViewById(R.id.tv_variety_en_name);
            tvVersion = itemView.findViewById(R.id.tvVersion);
            tvRank = itemView.findViewById(R.id.tv_variety_rank);
        }
    }
}
