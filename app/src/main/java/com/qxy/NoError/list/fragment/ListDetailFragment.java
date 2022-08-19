package com.qxy.NoError.list.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.qxy.NoError.databinding.FragmentListDetailBinding;
import com.qxy.NoError.list.adapter.MyListAdapter;
import com.qxy.NoError.list.vm.ListViewModel;

/**
 * @author 徐鑫
 */
public class ListDetailFragment<T extends RecyclerView.ViewHolder> extends Fragment {

    private static final String TAG = "ListDetailFragment";

    private FragmentListDetailBinding binding;
    private MyListAdapter<T> adapter;
    private int type;

    public ListDetailFragment() {
    }

    public ListDetailFragment(int type, @NonNull MyListAdapter<T> adapter) {
        this.type = type;
        this.adapter = adapter;
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
        listViewModel.requireVersionData(this.type);

        //绑定适配器
        binding.rvList.setAdapter(adapter);
        binding.rvList.setLayoutManager(new LinearLayoutManager(requireContext()));

        binding.srlList.setOnRefreshListener(() -> {
            //请求数据
            listViewModel.requestDataFromNet(this.type,
                    listViewModel.getSelectVersion(this.type).getValue().version);
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

        listViewModel.getSelectVersion(this.type).observe(getViewLifecycleOwner(), version -> {
            binding.srlList.setRefreshing(true);
            listViewModel.requestDataFromNet(version.type, version.version);
            adapter.setVersion(version);
            binding.rvList.setAdapter(adapter);
        });
    }
}