package com.qxy.NoError.list.fragment;

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
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog == null) {
            return;
        }
        Window window = dialog.getWindow();
        if (window != null) {
            //设置 window 的背景色为透明色.
            //如果通过 window 设置宽高时，想要设置宽为屏宽，就必须调用下面这行代码。
            window.setBackgroundDrawableResource(R.color.transparent);
            WindowManager.LayoutParams attributes = window.getAttributes();
            //在这里我们可以设置 DialogFragment 弹窗的位置
            attributes.gravity = Gravity.START | Gravity.CENTER_VERTICAL;
            //我们可以在这里指定 window的宽高
            attributes.width = 200;
            attributes.height = 200;
            //设置 DialogFragment 的进出动画
//            attributes.windowAnimations = R.style.DialogAnimation;
//            window.setAttributes(attributes);
        }
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

        Log.d(TAG, "onViewCreated: ");

        ListViewModel mViewModel = new ViewModelProvider(requireActivity())
                .get(String.valueOf(type), ListViewModel.class);
        mViewModel.getVersionLiveData().observe(getViewLifecycleOwner(), versions -> adapter.submitList(versions));

        Log.d(TAG, "onViewCreated: " + mViewModel.hashCode());
        adapter = new VersionAdapter(mViewModel);

//        adapter.addLoadStateListener(new Function1<CombinedLoadStates, Unit>() {
//            @Override
//            public Unit invoke(CombinedLoadStates combinedLoadStates) {
//                if (combinedLoadStates.getRefresh() instanceof LoadState.Loading) {
//                    Log.d(TAG, "invoke: 正在加载");
//                } else if (combinedLoadStates.getRefresh() instanceof LoadState.Error) {
//                    Log.d(TAG, "invoke: 加载异常");
//                    Toast.makeText(requireContext(), "网络异常", Toast.LENGTH_SHORT).show();
//
//                } else if (combinedLoadStates.getRefresh() instanceof LoadState.NotLoading) {
//                    Log.d(TAG, "invoke: 加载完成");
//                }
//                return null;
//            }
//        });

        binding.rvVersion.setAdapter(adapter);
        binding.rvVersion.setLayoutManager(new LinearLayoutManager(requireActivity()));

        // TODO: 2022/8/13 需要根据不同的fragment来传递不同的type
//        mViewModel.getVersionData(type).observe(getViewLifecycleOwner(), pagingData -> {
//            adapter.submitData(getLifecycle(), pagingData);
//        });
    }
}