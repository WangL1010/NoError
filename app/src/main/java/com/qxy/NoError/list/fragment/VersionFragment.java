package com.qxy.NoError.list.fragment;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.qxy.NoError.R;
import com.qxy.NoError.databinding.FragmentVersionBinding;
import com.qxy.NoError.list.adapter.VersionAdapter;
import com.qxy.NoError.list.vm.ListViewModel;

/**
 * @author 徐鑫
 */
public class VersionFragment extends DialogFragment {

    private static final String TAG = "VersionFragment";

    public static final String VERSION_TYPE = "data_type";

    /**
     * 绑定的榜单类型{@link com.qxy.NoError.list.bean.ListData#type ListData.type}
     */
    private int type;

    private FragmentVersionBinding binding;
    private VersionAdapter adapter;

    public VersionFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            type = getArguments().getInt(VERSION_TYPE, 1);
        } else {
            type = 1;
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentVersionBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ListViewModel mViewModel = new ViewModelProvider(requireActivity())
                .get(String.valueOf(type), ListViewModel.class);

        adapter = new VersionAdapter(mViewModel, this);
        adapter.submitList(mViewModel.getVersionList());

        binding.rvVersion.setAdapter(adapter);
        binding.rvVersion.setLayoutManager(new LinearLayoutManager(requireActivity()));

    }
}