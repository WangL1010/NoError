package com.qxy.NoError.list.adapter;

import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.qxy.NoError.list.bean.Movie;

import java.util.HashMap;
import java.util.List;

/**
 * 电视剧榜单的适配器
 * @author 徐鑫
 */
public class TeleplayAdapter extends RecyclerView.Adapter<TeleplayAdapter.TeleplayViewHolder> {

    private List<Movie> movieList;

    private HashMap<String, Bitmap> bitmaps;



    @NonNull
    @Override
    public TeleplayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull TeleplayViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class TeleplayViewHolder extends RecyclerView.ViewHolder {

        public TeleplayViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    public TeleplayAdapter(List<Movie> movieList, HashMap<String, Bitmap> bitmaps) {
        this.movieList = movieList;
        this.bitmaps = bitmaps;
    }

    public TeleplayAdapter(List<Movie> movieList) {
        this.movieList = movieList;
    }

    public TeleplayAdapter() {
    }

    public List<Movie> getMovieList() {
        return movieList;
    }

    public void setMovieList(List<Movie> movieList) {
        this.movieList = movieList;
    }

    public HashMap<String, Bitmap> getBitmaps() {
        return bitmaps;
    }

    public void setBitmaps(HashMap<String, Bitmap> bitmaps) {
        this.bitmaps = bitmaps;
    }
}
