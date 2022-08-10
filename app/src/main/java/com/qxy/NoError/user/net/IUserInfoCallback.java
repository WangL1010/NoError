package com.qxy.NoError.user.net;

import com.qxy.NoError.user.bean.UserInfo;

/**
 * 用户信息回调
 */
public interface IUserInfoCallback {

    void loadUserInfo(UserInfo userInfo);
}
