package com.qxy.NoError.user;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabLayoutMediator;
import com.qxy.NoError.databinding.FragmentUserBinding;
import com.qxy.NoError.list.fragment.VersionFragment;
import com.qxy.NoError.user.adapter.ListFragmentAdapter;
import com.qxy.NoError.user.fragment.FansListFragment;
import com.qxy.NoError.user.fragment.FollowListFragment;
import com.qxy.NoError.user.fragment.VideoListFragment;
import com.qxy.NoError.user.vm.UserViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户信息界面
 * @author 徐鑫
 */
public class UserFragment extends Fragment {

    private static final String TAG = "UserFragment";
    private UserViewModel mViewModel;
    private FragmentUserBinding binding;


    public static UserFragment newInstance() {
        return new UserFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentUserBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(TAG, "onViewCreated: ");
        mViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        //请求用户数据
        mViewModel.getUserOpenInfo();

        List<Fragment> fragments=new ArrayList<>();
        fragments.add(0,new VideoListFragment());
        fragments.add(1,new FollowListFragment());
        fragments.add(2,new FansListFragment());
        ListFragmentAdapter listFragmentAdapter = new ListFragmentAdapter(this, fragments);
        binding.userVp2List.setAdapter(listFragmentAdapter);

        new TabLayoutMediator(binding.userTlList, binding.userVp2List,((tab,position)->{
            Log.d(TAG, "onViewCreated: "+position);
            switch (position) {
                case 0:
                    tab.setText("作品");
                    break;
                case 1:
                    tab.setText("关注");
                    break;
                default:
                    tab.setText("粉丝");
            }
        })).attach();

        mViewModel.getUserOpenInfoLiveData().observe(getViewLifecycleOwner(),userOpenInfo -> {
            binding.userNickname.setText(userOpenInfo.nickname);
            Glide.with(binding.CvUserAvatar).load(userOpenInfo.avatar).into(binding.userAvatar);
        });
    }
}

