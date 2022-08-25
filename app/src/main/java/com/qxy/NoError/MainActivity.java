package com.qxy.NoError;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.util.Log;
import android.util.Printer;
import android.view.View;


import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;


import com.bytedance.sdk.open.aweme.authorize.model.Authorization;
import com.bytedance.sdk.open.douyin.DouYinOpenApiFactory;
import com.bytedance.sdk.open.douyin.api.DouYinOpenApi;
import com.qxy.NoError.Monitor.ANR;
import com.qxy.NoError.databinding.ActivityMainBinding;

import com.qxy.NoError.utils.Constants;
import com.qxy.NoError.utils.NetUtils;


import java.util.HashMap;
import java.util.Map;

/**
 * 主界面
 *
 * @author 徐鑫
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private final HashMap<Integer, MotionLayout> map = new HashMap<>();
    private DouYinOpenApi douYinOpenApi;
    private ANR anr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        douYinOpenApi = DouYinOpenApiFactory.create(this);
        anr = new ANR();


        /**
         * 从SharedPreferences文件中读取数据
         * 判断authCode如果为空去获取
         */
        SharedPreferences pref = getSharedPreferences("data",MODE_PRIVATE);
        String authCode = pref.getString("authCode","");
        if (authCode==null||authCode.length()==0){
            getAuthCode();
        }

        System.out.println("");

        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
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

    /**
     * 获取authCode用来获取之后的数据
     */
    private void getAuthCode() {
        Authorization.Request request = new Authorization.Request();
        request.scope = Constants.SCOPE;                          // 用户授权时必选权限
        request.optionalScope0 = "mobile";     // 用户授权时可选权限（默认选择）
//        request.optionalScope0 = mOptionalScope1;    // 用户授权时可选权限（默认不选）
        request.state = "ww";                                   // 用于保持请求和回调的状态，授权请求后原样带回给第三方。
        douYinOpenApi.authorize(request);               // 优先使用抖音app进行授权，如果抖音app因版本或者其他原因无法授权，则使用wap页授权
    }
}