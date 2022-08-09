package com.qxy.NoError.view;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.qxy.NoError.R;
import com.qxy.NoError.adapter.VideoViewPageAdapter;
import com.qxy.NoError.view.fragment.MovieFragment;
import com.qxy.NoError.view.fragment.TVPlayFragment;
import com.qxy.NoError.view.fragment.VarietyShowFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 呈现榜单数据
 */
public class VideoLeaderboardActivity extends FragmentActivity {
    @BindView(R.id.viewpage_video)
    public ViewPager2 mViewPager2;

    List<Fragment> mFragmentList;
    private Unbinder mBind;
    @BindView(R.id.video_navigation_bar)
    public BottomNavigationView mNavigationView;

    private MovieFragment mMovieFragment;
    private TVPlayFragment mTvPlayFragment;
    private VarietyShowFragment mVarietyShowFragment;
    private VideoViewPageAdapter mViewPageAdapter;
    private FragmentManager mFragmentManager;
    private String TAG="VideoLeaderboardActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_fragment);
        mBind = ButterKnife.bind(this);
        initFragment();
        initView();
        initEvent();

    }

    /**
     * 初始化Fragment
     */
    private void initFragment() {
        mFragmentList = new ArrayList<>();
        mMovieFragment = new MovieFragment();
        mTvPlayFragment = new TVPlayFragment();
        mVarietyShowFragment = new VarietyShowFragment();
        mFragmentList.add(mMovieFragment);
        mFragmentList.add(mTvPlayFragment);
        mFragmentList.add(mVarietyShowFragment);

        mFragmentManager = getSupportFragmentManager();

    }

    private void initView() {
        mViewPageAdapter = new VideoViewPageAdapter(this);
        mViewPageAdapter.setFragmentList(mFragmentList);
        mViewPager2.setAdapter(mViewPageAdapter);
    }

    private void initEvent() {
        mNavigationView.setOnNavigationItemSelectedListener(item -> {
            if (item.getItemId() == R.id.movie_list) {
                mViewPager2.setCurrentItem(0,false);
                Log.d(TAG, "initEvent: "+"切換到电影榜单");
            }
            if (item.getItemId() == R.id.tv_list) {
                mViewPager2.setCurrentItem(1,false);
                Log.d(TAG, "initEvent: "+"切換到电视剧榜单");
            }
            if (item.getItemId() == R.id.variety_list) {
                mViewPager2.setCurrentItem(2,false);
                Log.d(TAG, "initEvent: "+"切換到综艺节目榜单");
            }
            return true;
        });
        mViewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                mNavigationView.getMenu().getItem(position).setChecked(true);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mBind != null) {
            mBind.unbind();
        }
    }
}
