package com.qxy.NoError.user.vm;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.qxy.NoError.user.bean.UserOpenInfo;

public class UserInfoViewModel extends ViewModel  {
    private MutableLiveData<UserOpenInfo> liveData;

    public static final String TAG="UserInfoViewModel";

    public UserInfoViewModel() {

    }




    public MutableLiveData<UserOpenInfo> getLiveData(){
        return liveData;
    }


}
