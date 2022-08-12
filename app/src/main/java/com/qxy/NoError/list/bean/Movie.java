package com.qxy.NoError.list.bean;

import java.time.LocalDate;

public class Movie {

    public String[] actors;
    public String[] areas;
    public String[] directors;
    public Long discussionHot;
    public Long hot;
    public String id;
    public Long influenceHot;
    public String maoyanId;
    public String name;
    public String nameEn;
    public String poster;
    public String releaseDate;
    public Long searchHot;
    public String[] tags;
    public Long topicHot;
    public Long type;
    //部分数据有上映区域，部分没有（位于actor和directoe数据中间）
    public String releaseArea;

}
