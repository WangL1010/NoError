package com.qxy.NoError.user.net;


import java.util.List;

/**
 * 从网络中请求到的响应数据类
 * @author 徐鑫
 */

public class ListResponseData<T> {


    public Data<T> data;
    public Extra extra;
    public String message;

    public static class Data<T> {

        public List<T> list;
        public Integer total;
        public Long cursor;
        public Integer error_code;
        public String description;
        public Boolean has_more;

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
