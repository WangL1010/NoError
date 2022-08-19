package com.qxy.NoError.user.vm;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.qxy.NoError.user.bean.FanListData;
import com.qxy.NoError.user.bean.FollowListData;
import com.qxy.NoError.user.bean.UserOpenInfo;
import com.qxy.NoError.user.bean.VideoListData;
import com.qxy.NoError.user.moder.UserModel;

import java.util.List;

public class UserViewModel extends ViewModel {

    // TODO: Implement the ViewModel
    //用户信息
    private MutableLiveData<UserOpenInfo> userOpenInfo;

    //粉丝、关注、视频列表
    private MutableLiveData<List<FanListData>> fansList;
    private MutableLiveData<List<FollowListData>> followingList;
    private MutableLiveData<List<VideoListData>> videoList;

    private MutableLiveData<Boolean> success;

    UserModel userModel=new UserModel();

    public void getFanListData(Integer cursor,Integer count){
        userModel.getFanListData(cursor, count, new UserModel.CallBack2DealData<FanListData>() {


            @Override
            public void success(List<FanListData> list) {
                getFansLiveData().setValue(list);
                getSuccess().setValue(true);
            }

            @Override
            public void success(FanListData fanListData) {

            }

            @Override
            public void fail(String message) {
                getSuccess().setValue(false);
            }
        });

    }
    public void getUserOpenInfo(){
        userModel.getUserOpenInfo(new UserModel.CallBack2DealData<UserOpenInfo>() {

            @Override
            public void success(List<UserOpenInfo> list) {

            }

            @Override
            public void success(UserOpenInfo userOpenInfo) {
                getUserOpenInfoLiveData().setValue(userOpenInfo);
                getSuccess().setValue(true);
            }

            @Override
            public void fail(String message) {
                getSuccess().setValue(false);
            }
        });
    }
    public void getFollowListData(Integer cursor,Integer count){
        userModel.getFollowListData(cursor, count, new UserModel.CallBack2DealData<FollowListData>() {

            @Override
            public void success(List<FollowListData> list) {
                getFollowingLiveData().setValue(list);
                getSuccess().setValue(true);
            }

            @Override
            public void success(FollowListData followListData) {

            }

            @Override
            public void fail(String message) {
                getSuccess().setValue(false);
            }
        });
    }

    public void getVideoListData(Integer cursor,Integer count){
        userModel.getVideoListData(cursor, count, new UserModel.CallBack2DealData<VideoListData>() {
            @Override
            public void success(List<VideoListData> list) {
                getVideoLiveData().setValue(list);
                getSuccess().setValue(true);
            }

            @Override
            public void success(VideoListData videoListData) {

            }

            @Override
            public void fail(String message) {
                getSuccess().setValue(false);
            }
        });
    }
    private MutableLiveData<Boolean> getSuccess() {
        if (this.success == null) {
            this.success = new MutableLiveData<>();
        }
        return success;
    }

    public MutableLiveData<List<FanListData>> getFansLiveData(){
        if (this.fansList==null){
            fansList=new MutableLiveData<>();
        }
        return fansList;
    }

    public MutableLiveData<List<FollowListData>> getFollowingLiveData(){
        if (this.followingList==null){
            followingList=new MutableLiveData<>();
        }
        return followingList;
    }
    public MutableLiveData<List<VideoListData>> getVideoLiveData(){
        if (this.videoList==null){
            videoList=new MutableLiveData<>();
        }
        return videoList;
    }
    public MutableLiveData<UserOpenInfo> getUserOpenInfoLiveData(){
        if (this.userOpenInfo==null){
            userOpenInfo=new MutableLiveData<>();
        }
        return userOpenInfo;
    }

}