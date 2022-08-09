package com.qxy.NoError.list.vm;

import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.qxy.NoError.list.bean.Movie;
import com.qxy.NoError.list.model.MovieModel;

import java.io.Closeable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 管理电影榜单的view model
 * @author 徐鑫
 */
public class MovieViewModel extends ViewModel {

    private MutableLiveData<List<Movie>> movieList;
    private MutableLiveData<LocalDate> date;

    public MovieViewModel() {
        super();
        init();
    }

    public MovieViewModel(@NonNull Closeable... closeables) {
        super(closeables);
        init();
    }

    private void init() {
        MovieModel movieModel = new MovieModel();
        movieModel.getMovieList((localDate, description, movieList) -> {
            getDate().setValue(localDate);
            getMovieList().setValue(movieList);
        });
    }

    public MutableLiveData<LocalDate> getDate() {
        if (date == null) {
            date = new MutableLiveData<>();
        }
        return date;
    }

    public MutableLiveData<List<Movie>> getMovieList() {
        if (movieList == null) {
            movieList = new MutableLiveData<>();
        }
        return movieList;
    }
}
