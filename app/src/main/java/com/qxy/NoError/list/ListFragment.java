package com.qxy.NoError.list;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.qxy.NoError.R;
import com.qxy.NoError.list.fragment.MovieFragment;
import com.qxy.NoError.list.fragment.TeleplayFragment;
import com.qxy.NoError.list.fragment.VarietyFragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListFragment extends Fragment {

    TabLayout tabLayout;
    ViewPager2 viewPager2;

    public ListFragment() {
        // Required empty public constructor
    }
    public static ListFragment newInstance() {
        ListFragment fragment = new ListFragment();
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
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        viewPager2 = view.findViewById(R.id.vp2List);

        viewPager2.setAdapter(new FragmentStateAdapter(this) {
            @NonNull
            @Override
            public Fragment createFragment(int position) {
                switch (position) {
                    case 0:
                        return MovieFragment.newInstance();
                    case 1:
                        return TeleplayFragment.newInstance();
                    default:
                        return VarietyFragment.newInstance();
                }
            }

            @Override
            public int getItemCount() {
                return 3;
            }
        });

        tabLayout = view.findViewById(R.id.tlList);

        new TabLayoutMediator(tabLayout, viewPager2, ((tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText("电影榜单");
                    break;
                case 1:
                    tab.setText("电视剧榜单");
                    break;
                default:
                    tab.setText("综艺榜单");
            }
        })).attach();

    }
}