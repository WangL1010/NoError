package com.qxy.NoError;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * 接收抖音发布的广播
 * @author 王龙
 */
public class StayInDyReceiver extends BroadcastReceiver {

    private static final String TAG = "StayInDyReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG, "收到了留在抖音广播${intent}");
        Toast.makeText(context, "第三方接收到广播：action = ${intent?.action}", Toast.LENGTH_LONG).show();
    }
}
