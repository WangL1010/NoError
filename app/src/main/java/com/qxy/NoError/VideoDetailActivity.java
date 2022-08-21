package com.qxy.NoError;


import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.LinearLayout;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.just.agentweb.AgentWeb;

/**
 * 视屏播放详情页面
 */
public class VideoDetailActivity extends AppCompatActivity {
    private static final String TAG = "VideoDetailActivity";
    String testUrl;
    LinearLayout layout;
    private AgentWeb mAgentWeb;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videolist);
        layout=this.findViewById(R.id.layout);

        //获取shareUrl值
        Intent intent = getIntent();
        testUrl=intent.getStringExtra("videoUrl");

        //通过video传来的url播放视频
        if (testUrl!=null){

            mAgentWeb = AgentWeb.with(this)
                    .setAgentWebParent(layout, new LinearLayout.LayoutParams(-1, -1))
                    .useDefaultIndicator()
                    .createAgentWeb()
                    .ready()
                    .go(testUrl);
        }
    }

    @Override
    protected void onPause() {
        mAgentWeb.getWebLifeCycle().onPause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        mAgentWeb.getWebLifeCycle().onResume();
        super.onResume();
    }
}
