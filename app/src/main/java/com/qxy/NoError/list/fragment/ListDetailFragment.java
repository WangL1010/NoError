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

    private FragmentListDetailBinding binding;
    private MyListAdapter<T> adapter;
    private int dataType;
    private String viewModelName;

    public ListDetailFragment() {
    }

    public ListDetailFragment(String viewModelName, MyListAdapter<T> adapter, int dataType) {
        this.viewModelName = viewModelName;
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
        //绑定适配器
        binding.rvList.setAdapter(adapter);
        binding.rvList.setLayoutManager(new LinearLayoutManager(getContext()));

        ListViewModel listViewModel = new ViewModelProvider(requireActivity())
                .get(this.viewModelName, ListViewModel.class);

        binding.srlList.setOnRefreshListener(() -> {
            //请求数据
            listViewModel.requestDataFromNet(this.dataType);
        });

        //开始请求数据，页头开始刷新
        binding.srlList.setRefreshing(true);
        listViewModel.requestDataFromNet(this.dataType);

        listViewModel.getListData().observe(getViewLifecycleOwner(), dataList -> {
            adapter.setListData(dataList);
            binding.rvList.setAdapter(adapter);
        });

        listViewModel.getSuccess().observe(getViewLifecycleOwner(), aBoolean -> {
            if (binding.srlList.isRefreshing()) {
                binding.srlList.setRefreshing(false);
            }
        });

    }
}