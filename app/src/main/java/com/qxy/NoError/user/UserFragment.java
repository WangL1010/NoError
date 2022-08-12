package com.qxy.NoError.user;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qxy.NoError.R;
import com.qxy.NoError.databinding.FragmentUserBinding;
import com.qxy.NoError.user.vm.UserViewModel;

/**
 * 用户信息界面
 * @author 徐鑫
 */
public class UserFragment extends Fragment {

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
        mViewModel = new ViewModelProvider(this).get(UserViewModel.class);
    }
}