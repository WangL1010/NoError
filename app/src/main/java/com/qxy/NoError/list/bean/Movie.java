package com.qxy.NoError.list.bean;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Entity;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import com.qxy.NoError.Database.StringConverter;

import java.util.List;




@Entity(tableName = "movie_table",primaryKeys = {"id"})
@TypeConverters(StringConverter.class)
public class Movie {

    @NonNull
    public String id;

    public List<String> actors;
    public List<String> areas;
    public List<String> directors;
    public Long discussionHot;
    public Long hot;
    public Long influenceHot;
    public String maoyanId;
    public String name;
    public String nameEn;
    public String poster;
    public String releaseDate;
    public Long searchHot;
    public List<String> tags;
    public Long topicHot;
    public Long type;

}
