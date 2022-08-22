package com.qxy.NoError.user.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.qxy.NoError.VideoDetailActivity;
import com.qxy.NoError.databinding.FragmentListDetailBinding;

import com.qxy.NoError.user.adapter.VideoListAdapter;
import com.qxy.NoError.user.bean.VideoListData;
import com.qxy.NoError.user.vm.UserViewModel;

public class VideoListFragment extends Fragment implements VideoListAdapter.OnListItemClickListener {
    private static final String TAG = "VideoListFragment";
    private FragmentListDetailBinding binding;
    private UserViewModel userViewModel;
    private VideoListAdapter videoListAdapter;

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
        userViewModel.getVideoListData(0,10);
        videoListAdapter = new VideoListAdapter();
        videoListAdapter.setOnListItemClickListener(this);
        binding.rvList.setAdapter(videoListAdapter);
        binding.rvList.setLayoutManager(new GridLayoutManager(requireContext(),3, LinearLayoutManager.VERTICAL,false));

        userViewModel.getVideoLiveData().observe(getViewLifecycleOwner(),(videoListData)->{
            videoListAdapter.setData(videoListData);
            binding.rvList.setAdapter(videoListAdapter);
        });
    }

    @Override
    public void onItemClick(VideoListData videoData) {
        //跳转到视频详情页面
        Log.d(TAG, "onItemClick: ");
        if (videoData!=null){

            String shareUrl = videoData.getShareUrl();
            Intent intent=new Intent(requireContext(), VideoDetailActivity.class);
            intent.putExtra("videoUrl",shareUrl);
            intent.putExtra("create_time",""+videoData.getCreateTime());
            intent.putExtra("play_count",""+videoData.getStatistics().getPlayCount());
            intent.putExtra("share_count",""+videoData.getStatistics().getShareCount());
            intent.putExtra("forward_count",""+videoData.getStatistics().getForwardCount());
            intent.putExtra("download_count",""+videoData.getStatistics().getDownloadCount());
            startActivity(intent);
        }

    }

}
