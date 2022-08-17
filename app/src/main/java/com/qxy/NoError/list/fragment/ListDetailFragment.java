package com.qxy.NoError.list.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.qxy.NoError.databinding.FragmentListDetailBinding;
import com.qxy.NoError.list.adapter.MyListAdapter;
import com.qxy.NoError.list.bean.Version;
import com.qxy.NoError.list.vm.ListViewModel;
import com.qxy.NoError.performance.IFrameCallBack;
import com.qxy.NoError.utils.ToastUtils;

/**
 * @author 徐鑫
 */
public class ListDetailFragment<T extends RecyclerView.ViewHolder> extends Fragment {

    private static final String TAG = "ListDetailFragment";

    private FragmentListDetailBinding binding;
    private MyListAdapter<T> adapter;
    private int dataType;
    private int type;

    public ListDetailFragment() {
    }

    public ListDetailFragment(int type, @NonNull MyListAdapter<T> adapter, int dataType) {
        this.type = type;
        this.adapter = adapter;
        this.dataType = dataType;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentListDetailBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ListViewModel listViewModel = new ViewModelProvider(requireActivity())
                .get(String.valueOf(type), ListViewModel.class);

        //开始请求数据，页头开始刷新
        binding.srlList.setRefreshing(true);
        listViewModel.requestDataFromNet(this.dataType);

        //绑定适配器
        binding.rvList.setAdapter(adapter);
        binding.rvList.setLayoutManager(new LinearLayoutManager(requireContext()));

        //绑定监视器
        float refreshRate;
        try {
            refreshRate = getActivity().getWindowManager().getDefaultDisplay().getRefreshRate();
        }catch (NullPointerException e){
            e.printStackTrace();
            refreshRate = 60;
        }


        IFrameCallBack.deviceRefreshRate = refreshRate;
        binding.rvList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                switch (newState) {
                    //在开始滑动时启动
                    case RecyclerView.SCROLL_STATE_DRAGGING:
                        IFrameCallBack.getInstance().start();
                    //在滑动停止是停止
                    case RecyclerView.SCROLL_STATE_IDLE:
                        IFrameCallBack.getInstance().stop();
                }
            }
        });
        //获取选择版本的viewModel，作用域为activity，以便共享
//        VersionViewModel versionViewModel = new ViewModelProvider(requireActivity())
//                .get(String.valueOf(this.dataType), VersionViewModel.class);

//        versionViewModel.getData(this.dataType);

//        Log.d(TAG, "onViewCreated: " + versionViewModel.hashCode());
//        versionViewModel.getSelectVersion().observe(requireActivity(), version -> {
//            //当前控制版本选择的viewModel中选择的版本发生变化时，启动下拉刷新，并且获取版本数据
//            // TODO: 2022/8/14 根据设计，获取以往历史榜单的流程为，先从数据库中获取，再从网络中获取
//            binding.srlList.setRefreshing(true);
//            listViewModel.requestDataFromNet(Integer.valueOf(version.type),
//                    Integer.valueOf(version.version));
//            mVersion = version;
//        });


        binding.srlList.setOnRefreshListener(() -> {
            //如果在历史榜单上下拉刷新，由于历史榜单不会发生变化，所以没有必要再次获取数据
//            if (listViewModel.getSelectVersion().getValue().version != 0) {
//                ToastUtils.show("历史榜单不会改变，无需刷新");
//                return;
//            }
            //请求数据
            listViewModel.requestDataFromNet(this.dataType);
        });

        listViewModel.getListData().observe(getViewLifecycleOwner(), dataList -> {
            adapter.submitList(dataList);
            binding.rvList.setAdapter(adapter);
        });

        listViewModel.getSuccess().observe(getViewLifecycleOwner(), aBoolean -> {
            if (binding.srlList.isRefreshing()) {
                binding.srlList.setRefreshing(false);
            }
        });

        listViewModel.getSelectVersion().observe(getViewLifecycleOwner(), version -> {
            binding.srlList.setRefreshing(true);
            listViewModel.requestDataFromNet(version.type, version.version);
            adapter.setVersion(version);
            binding.rvList.setAdapter(adapter);
        });
    }
}