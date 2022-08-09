package com.qxy.NoError.list.net;

import java.util.List;

/**
 * 从网络中请求到的响应数据类
 * @author 徐鑫
 */
public class ResponseData<T> {

    public Data<T> data;
    public Extra extra;

    public static class Data<T> {
        public String activeTime;
        public String description;
        public String errorCode;
        public List<T> list;
    }
    public static class Extra {
        public String logid;
        public long now;
    }
}
