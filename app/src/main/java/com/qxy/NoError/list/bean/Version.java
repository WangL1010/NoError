package com.qxy.NoError.list.bean;

import androidx.annotation.NonNull;
import androidx.room.Entity;

/**
 * 版本数据
 *
 * @author 徐鑫
 */
@Entity(tableName = "version", primaryKeys = {"type", "version"})
public class Version {
    /**
     * 榜单生成时间
     */
    public String activeTime;
    /**
     * 榜单结束时间
     */
    public String endTime;
    /**
     * 榜单起始时间
     */
    public String startTime;
    /**
     * 类型{@link ListData#type ListData.type}
     */
    @NonNull
    public Integer type = 1;
    /**
     * 榜单版本
     */
    @NonNull
    public Integer version = 0;

    public Version() {
    }

    public Version(String activeTime, String endTime, String startTime, @NonNull Integer type, @NonNull Integer version) {
        this.activeTime = activeTime;
        this.endTime = endTime;
        this.startTime = startTime;
        this.type = type;
        this.version = version;
    }
}
