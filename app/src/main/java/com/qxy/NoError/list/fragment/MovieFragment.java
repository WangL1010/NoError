package com.qxy.NoError.list.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qxy.NoError.R;
import com.qxy.NoError.list.adapter.MovieAdapter;
import com.qxy.NoError.list.bean.Movie;
import com.qxy.NoError.list.vm.MovieViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 电影榜单
 * @author 徐鑫
 */
public class MovieFragment extends Fragment {

    public MovieFragment() {
        // Required empty public constructor
    }
    public static MovieFragment newInstance() {
        return new MovieFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        MovieViewModel movieViewModel = new ViewModelProvider(this).get(MovieViewModel.class);
        RecyclerView recyclerView = view.findViewById(R.id.rv_movie_list);
        MovieAdapter adapter = new MovieAdapter(movieViewModel.getMovieList().getValue());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        movieViewModel.getMovieList().observe(getViewLifecycleOwner(), movieList -> {
            adapter.setMovieList(movieList);
            recyclerView.setAdapter(adapter);
            //当加载完毕所有movie数据，再加载图片数据
            movieViewModel.getBitmapsFromPaths();
        });

        movieViewModel.getBitmapHash().observe(getViewLifecycleOwner(), stringBitmapHashMap -> {
            adapter.setBitmaps(stringBitmapHashMap);
            recyclerView.setAdapter(adapter);
        });
    }
}