package com.qxy.NoError;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.qxy.NoError.databinding.ActivityTestBinding;

import java.util.HashMap;
import java.util.Map;

/**
 * 主界面
 *
 * @author 徐鑫
 */
public class TestActivity extends AppCompatActivity {

    private final HashMap<Integer, MotionLayout> map = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityTestBinding binding = ActivityTestBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // 主页面底部导航空间与id的映射
        // 如果需要添加底部导航控件，只需要在这里添加一个映射
        map.put(R.id.userFragment, binding.bnUser.mlUser);
        map.put(R.id.addFragment, binding.bnAdd.mlAdd);
        map.put(R.id.listFragment, binding.bnList.mlList);

        // TODO: 2022/8/12 不知道为什么在这里只能用这种方法获取 navController
        NavHostFragment navHostFragment =
                (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView);
        if (navHostFragment == null) {
            return;
        }
        NavController navController = navHostFragment.getNavController();

        for (Map.Entry<Integer, MotionLayout> entry :
                map.entrySet()) {
            entry.getValue().setOnClickListener(v -> {
                //弹出当前fragment
                navController.popBackStack();
                //压入点击的fragment
                navController.navigate(entry.getKey());
            });
        }

        navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
            int currDestinationId = destination.getId();

            for (Map.Entry<Integer, MotionLayout> entry :
                    map.entrySet()) {
                entry.getValue().setProgress(0f);
                //当前fragment的导航不能再次点击，其他的导航可以点击
                if (entry.getKey() == currDestinationId) {
                    entry.getValue().setClickable(false);
                    //点击的导航开始动画
                    entry.getValue().transitionToEnd();
                } else {
                    entry.getValue().setClickable(true);
                }
            }
        });
    }
}