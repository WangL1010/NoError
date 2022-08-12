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
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.item_movie, parent, false);
        return new MovieViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        ListData listData = this.getListData().get(position);
        holder.tvMovieName.setText(listData.name);
        holder.tvMovieHot.setText(String.valueOf(listData.hot));
        StringBuilder stringBuilder = new StringBuilder();
        //暂时先完成电影榜单
//        if (listData.tags != null) {
//            for (String str :
//                    listData.tags) {
//                stringBuilder.append(str).append(',');
//            }
//        }
//        holder.tvMovieType.setText(stringBuilder);

        if (listData.directors != null) {
            for (String str :
                    listData.directors) {
                stringBuilder.append(str).append(',');
            }
        }
        holder.tvMovieDirtector.setText(stringBuilder);

        StringBuilder stringBuilder_actors = new StringBuilder();
        if (listData.actors != null) {
            for (String str :
                    listData.actors) {
                stringBuilder_actors.append(str).append(',');
            }
        }
        holder.tvMovieActor.setText(stringBuilder);
        holder.tvReleaseArea.setText(listData.releaseArea);
        holder.tvReleaseTime.setText(listData.releaseDate);

        Glide.with(holder.movieIcon)
                .load(listData.poster)
                .placeholder(R.drawable.ic_list)
                .into(holder.movieIcon);
    }

    @Override
    public int getItemCount() {
        return getListData() == null ? 0 : getListData().size();
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder {

        ImageView movieIcon;
        TextView tvMovieName, tvMovieDirtector, tvMovieActor, tvReleaseArea,tvReleaseTime, tvMovieHot;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMovieName = itemView.findViewById(R.id.movie_name);
            tvMovieDirtector = itemView.findViewById(R.id.movie_director);
            tvMovieActor = itemView.findViewById(R.id.movie_actor);
            tvReleaseArea = itemView.findViewById(R.id.movie_release_area);
            tvReleaseTime = itemView.findViewById(R.id.movie_release_time);
            tvMovieHot = itemView.findViewById(R.id.tv_movie_hot);

            movieIcon = itemView.findViewById(R.id.movie_icon);
        }
    }
}
