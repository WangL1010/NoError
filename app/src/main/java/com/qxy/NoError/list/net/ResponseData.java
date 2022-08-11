package com.qxy.NoError.list.net;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;

import java.lang.reflect.Field;
import java.util.List;

/**
 * 从网络中请求到的响应数据类
 * @author 徐鑫
 */

public class ResponseData<T> {

    @Ignore
    public static final Long TOKEN_OVERDUE_CODE = 2190008L;

    public Data<T> data;
    public Extra extra;

    public static class Data<T> {
        public String activeTime;
        public String description;
        public Long errorCode;
        public List<T> list;
    }
    public static class Extra {
        @NonNull
        public String logid;
        public String description;
        public Long errorCode;
        public Long now;
        public String subDescription;
        public Long subErrorCode;
    }
}
