package com.qxy.NoError;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.just.agentweb.AgentWeb;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * 视屏播放详情页面
 */
public class VideoDetailActivity extends AppCompatActivity {
    private static final String TAG = "VideoDetailActivity";
    String videoUrl;
    LinearLayout layout;

    private AgentWeb mAgentWeb;

    TextView timeVideoView,playCountView,forwardCountView,shareCountView,downloadCountView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videolist);
        initView();


        //通过video传来的url播放视频
        if (videoUrl!=null){

            mAgentWeb = AgentWeb.with(this)
                    .setAgentWebParent(layout, new LinearLayout.LayoutParams(-1, -1))
                    .useDefaultIndicator()
                    .createAgentWeb()
                    .ready()
                    .go(videoUrl);
        }
    }

    private void initView() {
        layout=this.findViewById(R.id.layout);
        timeVideoView=this.findViewById(R.id.time_video);
        playCountView=this.findViewById(R.id.play_count);
        forwardCountView=this.findViewById(R.id.forward_count);
        shareCountView=this.findViewById(R.id.share_count);
        downloadCountView=this.findViewById(R.id.download_count);
        //获取shareUrl值
        Intent intent = getIntent();
        videoUrl=intent.getStringExtra("videoUrl");
        String create_time = intent.getStringExtra("create_time");
        String play_count = intent.getStringExtra("play_count");
        String share_count = intent.getStringExtra("share_count");
        String forward_count = intent.getStringExtra("forward_count");
        String download_count = intent.getStringExtra("download_count");
//        Log.d(TAG, "initView: "+create_time);
//        Log.d(TAG, "initView: "+stampToDate(create_time));
        timeVideoView.setText(stampToDate(create_time));
        playCountView.setText(play_count);
        forwardCountView.setText(forward_count);
        shareCountView.setText(share_count);
        downloadCountView.setText(download_count);
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

    /**
     * 转换时间戳
     * @param s
     * @return
     */
    public static String stampToDate(String s) {
        long timeStamp = Long.parseLong(s);
        if (timeStamp == 0) {
            return "";
        }
        timeStamp = timeStamp * 1000;
        String result = "";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        result = format.format(new Date(timeStamp));
        return result;
    }

}
