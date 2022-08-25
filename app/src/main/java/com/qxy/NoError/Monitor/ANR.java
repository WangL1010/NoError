package com.qxy.NoError.Monitor;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.util.Log;
import android.util.Printer;

public class ANR {

    private final String TAG_Monitor = "卡顿性能检测";
    private CheckTask mCheckTask = new CheckTask();

    long StartTime;

    public ANR(){
        Check();
    }

    private void Check(){
        Looper.getMainLooper().setMessageLogging(new Printer() {
            private final String START = ">>>>> Dispatching to";
            private final String END = "<<<<< Finished to";

            @Override
            public void println(String s) {
                if(s.startsWith(START)){
                    mCheckTask.start();
                    StartTime = System.currentTimeMillis();
                }else if(s.startsWith(END)){
                    mCheckTask.end();
                }
            }
        });
    }

    private class CheckTask {
        private HandlerThread mHandlerThread = new HandlerThread("卡顿检测");
        private Handler mHandler;

        private final int THREAD_HOLD = 1000;

        public CheckTask() {
            mHandlerThread.start();
            mHandler = new Handler(mHandlerThread.getLooper());
        }

        private Runnable mRunnable = new Runnable() {
            @Override
            public void run() {
                log();
            }
        };

        public void start() {
            //实现每THREAD_HOLD的定时操作，调用此mRunnable对象，调用run()方法
            mHandler.postDelayed(mRunnable, THREAD_HOLD);
        }

        public void end() {
            //关闭定时器
            mHandler.removeCallbacks(mRunnable);
        }
    }

    private void log() {
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = Looper.getMainLooper().getThread().getStackTrace();
        for (StackTraceElement s : stackTrace) {
            sb.append("\n" + s );
        }
        Log.w(TAG_Monitor, sb.toString());
        Log.i("卡顿时间", (System.currentTimeMillis() - StartTime) + "ms\n");
    }
}