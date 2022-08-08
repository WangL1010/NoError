package com.qxy.NoError.viewModel;

import com.qxy.NoError.model.UserInfo;

/**
 * 用户信息回调
 */
public interface IUserInfoCallback {

    void loadUserInfo(UserInfo userInfo);
}
