package com.qxy.NoError.user.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.qxy.NoError.databinding.FragmentListDetailBinding;
import com.qxy.NoError.user.adapter.FansListAdapter;
import com.qxy.NoError.user.vm.UserViewModel;

public class FansListFragment extends Fragment {

    private static final String TAG = "FansListFragment";
    private FragmentListDetailBinding binding;
    private UserViewModel userViewModel;
    private FansListAdapter fansListAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentListDetailBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onViewCreated: ");
        super.onViewCreated(view, savedInstanceState);
        userViewModel=new ViewModelProvider(requireActivity()).get(UserViewModel.class);
        userViewModel.getFanListData(0,10);
        fansListAdapter = new FansListAdapter();
        binding.rvList.setAdapter(fansListAdapter);
        binding.rvList.setLayoutManager(new LinearLayoutManager(requireContext()));

        userViewModel.getFansLiveData().observe(getViewLifecycleOwner(),(fansList)->{
            fansListAdapter.setData(fansList);
            binding.rvList.setAdapter(fansListAdapter);
        });
    }
}
