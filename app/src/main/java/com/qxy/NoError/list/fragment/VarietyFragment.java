package com.qxy.NoError.list.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qxy.NoError.R;

/**
 * 综艺榜单
 * @author 徐鑫
 */
public class VarietyFragment extends Fragment {

    public VarietyFragment() {
        // Required empty public constructor
    }
    public static VarietyFragment newInstance() {
        VarietyFragment fragment = new VarietyFragment();
        return fragment;
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
}