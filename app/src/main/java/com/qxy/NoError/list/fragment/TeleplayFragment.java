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
import com.qxy.NoError.list.adapter.TeleplayAdapter;
import com.qxy.NoError.list.vm.ListViewModel;

/**
 * 电视剧榜单
 * @author 徐鑫
 */
public class TeleplayFragment extends Fragment {

    public TeleplayFragment() {
        // Required empty public constructor
    }

    public static TeleplayFragment newInstance() {
        return new TeleplayFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_teleplay, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        ListViewModel listViewModel = new ViewModelProvider(this)
                .get("teleplayViewModel", ListViewModel.class);

        listViewModel.requestData(2);

        RecyclerView recyclerView = view.findViewById(R.id.rv_teleplay_list);
        TeleplayAdapter adapter = new TeleplayAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        listViewModel.getLiveData().observe(getViewLifecycleOwner(), teleplays -> {
            adapter.setListData(teleplays);
            recyclerView.setAdapter(adapter);
        });
    }
}