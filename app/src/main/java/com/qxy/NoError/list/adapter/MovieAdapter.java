package com.qxy.NoError.list.adapter;

import android.os.Bundle;
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
import com.qxy.NoError.list.fragment.VersionFragment;
import com.qxy.NoError.utils.StrUtil;

/**
 * 电影榜单的适配器
 *
 * @author 徐鑫
 */
public class MovieAdapter extends MyListAdapter<MovieAdapter.MovieViewHolder> {

    private volatile static MovieAdapter instance;

    private MovieAdapter() {}

    public static MovieAdapter getInstance() {
        if (instance == null) {
            synchronized (MovieAdapter.class) {
                if (instance == null) {
                    instance = new MovieAdapter();
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
                    //跳转到版本选择弹窗
                    Bundle bundle = new Bundle();
                    bundle.putInt(VersionFragment.VERSION_TYPE, 1);
                    Navigation.findNavController(v).navigate(R.id.versionFragment, bundle);
                }
            });
        } else {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View itemView = layoutInflater.inflate(R.layout.item_movie, parent, false);
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

        holder.tvRank.setText(StrUtil.formatRank(position, 3, "TOP "));
        holder.tvMovieName.setText(listData.name);
        holder.tvMovieHot.setText(StrUtil.formatFromInt(listData.hot, 4, "万"));
        holder.tvMovieDirtector.setText(StrUtil.formatStr(listData.directors, ",", 2));

        holder.tvMovieActor.setText(StrUtil.formatStr(listData.actors, ",", 2));
        holder.tvReleaseArea.setText(StrUtil.formatStr(listData.areas, ",", 2));
        holder.tvReleaseTime.setText(listData.releaseDate);

        Glide.with(holder.movieIcon)
                .load(listData.poster)
                .placeholder(R.drawable.ic_list)
                .into(holder.movieIcon);
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder {

        ImageView movieIcon;
        TextView tvMovieName, tvMovieDirtector, tvMovieActor,
                tvReleaseArea, tvReleaseTime, tvMovieHot, tvRank,
                tvVersion;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMovieName = itemView.findViewById(R.id.movie_name);
            tvMovieDirtector = itemView.findViewById(R.id.movie_director);
            tvMovieActor = itemView.findViewById(R.id.movie_actor);
            tvReleaseArea = itemView.findViewById(R.id.movie_release_area);
            tvReleaseTime = itemView.findViewById(R.id.movie_release_time);
            tvMovieHot = itemView.findViewById(R.id.tv_movie_hot);
            tvRank = itemView.findViewById(R.id.tvRank);
            movieIcon = itemView.findViewById(R.id.movie_icon);
            tvVersion = itemView.findViewById(R.id.tvVersion);
        }
    }
}
