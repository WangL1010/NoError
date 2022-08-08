package com.qxy.NoError.model;

/**
 * 获取用户信息的api所需POST请求的body
 */
public class UserInfo2Body {
    private String access_token;
    private String open_id;

    public UserInfo2Body(String access_token, String open_id) {
        this.access_token = access_token;
        this.open_id = open_id;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getOpen_id() {
        return open_id;
    }

    public void setOpen_id(String open_id) {
        this.open_id = open_id;
    }
}
