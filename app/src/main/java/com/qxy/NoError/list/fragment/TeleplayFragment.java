package com.qxy.NoError.list.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qxy.NoError.R;

/**
 * 电视剧榜单
 * @author 徐鑫
 */
public class TeleplayFragment extends Fragment {

    public TeleplayFragment() {
        // Required empty public constructor
    }

    public static TeleplayFragment newInstance() {
        TeleplayFragment fragment = new TeleplayFragment();
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
        return inflater.inflate(R.layout.fragment_teleplay, container, false);
    }
}