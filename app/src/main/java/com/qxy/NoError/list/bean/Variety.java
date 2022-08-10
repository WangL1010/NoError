package com.qxy.NoError.list.bean;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.TypeConverters;

import com.qxy.NoError.Database.StringConverter;

import java.util.List;

/**
 * 综艺数据类
 * @author 徐鑫
 */
@Entity(tableName = "variety_table",primaryKeys = {"id"})
@TypeConverters(StringConverter.class)
public class Variety {

    @NonNull
    public String id;

    public List<String> directors;
    public Long discussionHot;
    public Long hot;
    public Long influenceHot;
    public String maoYanId;

    public String name;
    public String nameEn;
    public String poster;
    public String releaseData;
    public String searchHot;
    public Long topicHot;
    public Long type;


}
