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
        if (movie.directors != null) {
            for (String str : movie.directors) {
                stringBuilder.append(str).append(',');
            }
        }
        holder.tvMovieDirtector.setText(stringBuilder);

        StringBuilder stringBuilder_2 = new StringBuilder();
        if (movie.actors != null) {
            for (String str : movie.actors) {
                stringBuilder.append(str).append(',');
            }
        }
        holder.tvMovieActor.setText(stringBuilder_2);

        holder.tvReleaseTime.setText(movie.releaseDate);
        holder.tvReleaseArea.setText(movie.releaseArea == null ? "中国大陆" : movie.releaseArea);

        if (bitmaps != null && bitmaps.size() > position) {
            holder.movieIcon.setImageBitmap(bitmaps.get(movie.poster));
        }
    }


    static class MovieViewHolder extends RecyclerView.ViewHolder {

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

    @Override
    public int getItemCount() {
        return movieList == null ? 0 : movieList.size();
    }

    public void setMovieList(List<Movie> movieList) {
        this.movieList = movieList;
    }

    public void setBitmaps(HashMap<String, Bitmap> bitmaps) {
        this.bitmaps = bitmaps;
    }
}
