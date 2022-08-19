package com.qxy.NoError.list.net;

import androidx.annotation.NonNull;
import androidx.room.Ignore;

import java.util.List;

/**
 * 从网络中请求到的响应数据类
 * @author 徐鑫
 */

public class ResponseData<T> {


    public static final Long TOKEN_OVERDUE_CODE = 2190008L;

    public Data<T> data;
    public Extra extra;

    public static class Data<T> {
        public String activeTime;
        public String description;
        public Long errorCode;
        public List<T> list;

        /**
         * 榜单历史版本用的数据
         */
        public Integer cursor;
        public Boolean hasMore;
    }
    public static class Extra {
        public String logid;
        public String description;
        public Long errorCode;
        public Long now;
        public String subDescription;
        public Long subErrorCode;
    }
}
