package com.qxy.NoError.list.bean;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.TypeConverters;

import com.qxy.NoError.Database.objectConverter;

import java.util.List;




/**
 * 统一的榜单数据
 * @author 徐鑫
 */
@Entity(tableName = "listData_table",primaryKeys = {"id"})
public class ListData {
    /**
     * 电影和电视剧用
     */
    @NonNull
    public String id;

    //榜单期数
    public Integer version;
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
