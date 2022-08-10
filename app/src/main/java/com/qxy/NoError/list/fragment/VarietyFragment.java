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

import com.qxy.NoError.R;
import com.qxy.NoError.list.adapter.VarietyAdapter;
import com.qxy.NoError.list.vm.ListViewModel;

/**
 * 综艺榜单
 * @author 徐鑫
 */
public class VarietyFragment extends Fragment {

    public VarietyFragment() {
        // Required empty public constructor
    }
    public static VarietyFragment newInstance() {
        return new VarietyFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_variety, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        ListViewModel listViewModel = new ViewModelProvider(this)
                .get("varietyViewModel", ListViewModel.class);
        //请求数据
        listViewModel.requestData(3);
        RecyclerView recyclerView = view.findViewById(R.id.rv_variety_list);
        VarietyAdapter adapter = new VarietyAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        listViewModel.getLiveData().observe(getViewLifecycleOwner(), listData -> {
            adapter.setMovieList(listData);
            recyclerView.setAdapter(adapter);
        });
    }
}