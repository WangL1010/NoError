package com.qxy.NoError.list.adapter;

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.qxy.NoError.R;
import com.qxy.NoError.list.bean.Movie;

import java.util.HashMap;
import java.util.List;

import cn.hutool.core.util.StrUtil;

/**
 * 电影榜单的适配器
 *
 * @author 徐鑫
 */
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private List<Movie> movieList;

    private HashMap<String, Bitmap> bitmaps;

    public MovieAdapter() {
    }

    public MovieAdapter(List<Movie> movieList) {
        this.movieList = movieList;
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
        Movie movie = movieList.get(position);
        holder.tvMovieName.setText(movie.name);
        holder.tvMovieHot.setText(String.valueOf(movie.hot));
        StringBuilder stringBuilder = new StringBuilder();
        if (movie.tags != null) {
            for (String str :
                    movie.tags) {
                stringBuilder.append(str).append(',');
            }
        }
        holder.tvMovieType.setText(stringBuilder);
        holder.tvReleaseTime.setText(movie.releaseDate);
        if (bitmaps != null && bitmaps.size() > position) {
            holder.movieIcon.setImageBitmap(bitmaps.get(movie.poster));
        }
    }

    @Override
    public int getItemCount() {
        return movieList == null ? 0 : movieList.size();
    }

    static class MovieViewHolder extends RecyclerView.ViewHolder {

        ImageView movieIcon;
        TextView tvMovieName, tvDoubanScore, tvMovieType, tvReleaseTime, tvMovieHot;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMovieName = itemView.findViewById(R.id.tv_movie_name);
            tvDoubanScore = itemView.findViewById(R.id.tv_douban_score);
            tvMovieType = itemView.findViewById(R.id.tv_movie_type);
            tvReleaseTime = itemView.findViewById(R.id.tv_release_time);
            tvMovieHot = itemView.findViewById(R.id.tv_movie_hot);
            movieIcon = itemView.findViewById(R.id.iv_movie_icon);
        }
    }

    public void setMovieList(List<Movie> movieList) {
        this.movieList = movieList;
    }

    public void setBitmaps(HashMap<String, Bitmap> bitmaps) {
        this.bitmaps = bitmaps;
    }
}
