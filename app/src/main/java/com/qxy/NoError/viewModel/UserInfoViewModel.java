package com.qxy.NoError.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.qxy.NoError.api.RetrofitApi;
import com.qxy.NoError.api.RetrofitManager;
import com.qxy.NoError.model.UserInfo;
import com.qxy.NoError.model.UserInfo2Body;
import com.qxy.NoError.repository.UserInfoRepository;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Retrofit;

public class UserInfoViewModel extends ViewModel implements IUserInfoCallback {
    private UserInfoRepository repository;
    private MutableLiveData<UserInfo> liveData;

    public static final String TAG="UserInfoViewModel";

    public UserInfoViewModel() {
        repository=new UserInfoRepository();
        liveData=new MutableLiveData<>();
        repository.registerCallback(this);
        Retrofit mRetrofit = RetrofitManager.getInstance().getRetrofit();
        RetrofitApi api =mRetrofit.create(RetrofitApi.class);
        Map<String,String> map=new HashMap<>();
        map.put("Content-Type","application/json");
        map.put("access-token","act.12d9381d72ed01b1b3a13f6f57d044eftawpeAohWab6TTDeZrAn6TdGZy8d");
        UserInfo2Body body=new UserInfo2Body("act.12d9381d72ed01b1b3a13f6f57d044eftawpeAohWab6TTDeZrAn6TdGZy8d","_000c7mD_XtFTMXlg6BVheAccAxm0IUhUPU7");
        repository.getUserInfo(map,body);
    }




    public MutableLiveData<UserInfo> getLiveData(){
        return liveData;
    }
    /**
     *加载用户信息
     */
    @Override
    public void loadUserInfo(UserInfo userInfo) {
        liveData.postValue(userInfo);
    }
}
