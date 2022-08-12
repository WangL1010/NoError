package com.qxy.NoError.list.bean;


import androidx.annotation.NonNull;
import androidx.room.Entity;

import java.util.List;

/**
 * 统一的榜单数据
 *
 * @author 徐鑫
 */
@Entity(tableName = "listData_table", primaryKeys = {"id"})
public class ListData {

    public static final int MOVIE_TYPE = 1;
    public static final int TELEPLAY_TYPE = 2;
    public static final int VARIETY_TYPE = 3;

    @NonNull
    public String id = "";

    /**
     * 榜单期数，一周一期
     */
    public Integer version;
    /**
     * 演员
     */
    public List<String> actors;
    /**
     * 地域
     */
    public List<String> areas;
    /**
     * 导演
     */
    public List<String> directors;
    /**
     * 讨论热度
     */
    public Long discussionHot;
    /**
     * 总热度
     */
    public Long hot;
    /**
     * 影响力热度
     */
    public Long influenceHot;
    /**
     * 猫眼id
     */
    public String maoyanId;
    /**
     * 中文名
     */
    public String name;
    /**
     * 英文名
     */
    public String nameEn;
    /**
     * 海报地址
     */
    public String poster;
    /**
     * 上映时间
     */
    public String releaseDate;
    /**
     * 搜索热度
     */
    public Long searchHot;
    /**
     * 标签
     */
    public List<String> tags;
    /**
     * 话题热度
     */
    public Long topicHot;
    /**
     * 类型数据：1-电影，2-电视剧，3-综艺
     */
    public Long type;

    //部分影片有上映区域的数据，位于Actors和Director中间
    public String releaseArea;
}
