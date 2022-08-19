package com.qxy.NoError.user.net;

/**
 * 请求参数，Body请求
 */
public class ResBody {
    private String access_token;
    private String open_id;

    public ResBody(String access_token, String open_id) {
        this.access_token = access_token;
        this.open_id = open_id;
    }

    @Override
    public String toString() {
        return "ResBody{" +
                "access_token='" + access_token + '\'' +
                ", open_id='" + open_id + '\'' +
                '}';
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
