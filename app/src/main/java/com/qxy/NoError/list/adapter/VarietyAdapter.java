package com.qxy.NoError.list.adapter;

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

import java.util.List;

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
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.item_movie, parent, false);
        return new MovieViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        ListData listData = this.listData.get(position);
        holder.tvMovieName.setText(listData.name);
        holder.tvMovieHot.setText(String.valueOf(listData.hot));
        StringBuilder stringBuilder = new StringBuilder();
        Glide.with(holder.movieIcon)
                .load(listData.poster)
                .placeholder(R.drawable.ic_list)
                .into(holder.movieIcon);
        holder.tvMovieType.setText(stringBuilder);
        holder.tvReleaseTime.setText(listData.releaseDate);
    }

    @Override
    public int getItemCount() {
        return listData == null ? 0 : listData.size();
    }

    static class MovieViewHolder extends RecyclerView.ViewHolder {

        ImageView movieIcon;
        TextView tvMovieName, tvDoubanScore, tvMovieType, tvReleaseTime, tvMovieHot;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMovieName = itemView.findViewById(R.id.tv_movie_name);
            tvDoubanScore = itemView.findViewById(R.id.tv_douban_score);
            tvMovieType = itemView.findViewById(R.id.tv_movie_type);
            tvReleaseTime = itemView.findViewById(R.id.tv_movie_release_time);
            tvMovieHot = itemView.findViewById(R.id.tv_movie_hot);
            movieIcon = itemView.findViewById(R.id.iv_movie_icon);
        }
    }
}
