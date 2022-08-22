package com.qxy.NoError.user.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.qxy.NoError.databinding.FragmentListDetailBinding;
import com.qxy.NoError.user.adapter.FollowingListAdapter;
import com.qxy.NoError.user.vm.UserViewModel;

public class FollowListFragment extends Fragment {
    private static final String TAG = "FollowListFragment";
    private FragmentListDetailBinding binding;
    private UserViewModel userViewModel;
    private FollowingListAdapter followingListAdapter;
    int count = 10;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentListDetailBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        userViewModel=new ViewModelProvider(requireActivity()).get(UserViewModel.class);
        userViewModel.getFollowListData(0,10);
        followingListAdapter = new FollowingListAdapter();
        binding.rvList.setAdapter(followingListAdapter);
        binding.rvList.setLayoutManager(new LinearLayoutManager(requireContext()));

        userViewModel.getFollowingLiveData().observe(getViewLifecycleOwner(),(followList)->{
            followingListAdapter.setData(followList);
            binding.rvList.setAdapter(followingListAdapter);
        });

        //设置刷新触发事件
        binding.srlList.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        count += 5;
                        userViewModel.getFollowListData(0,count);
                        binding.srlList.setRefreshing(false);
                    }
                }, 1000);
            }
        });
    }
}
