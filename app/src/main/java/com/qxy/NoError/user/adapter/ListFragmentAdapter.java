package com.qxy.NoError.user.adapter;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.List;

public class ListFragmentAdapter extends FragmentStateAdapter {
    private static final String TAG = "ListFragmentAdapter";
    List<Fragment> fragments;
    public ListFragmentAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }
    public ListFragmentAdapter(@NonNull Fragment fragment,List<Fragment> fragments) {
        this(fragment);
        this.fragments=fragments;
        Log.d(TAG, "ListFragmentAdapter: "+fragments.size());
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Log.d(TAG, "createFragment: "+position);
        return fragments.get(position);
    }

    @Override
    public int getItemCount() {
        return fragments.size();
    }
}
