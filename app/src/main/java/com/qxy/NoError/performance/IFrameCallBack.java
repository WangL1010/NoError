package com.qxy.NoError.performance;

import android.util.Log;
import android.view.Choreographer;

import java.util.concurrent.TimeUnit;


public class IFrameCallBack implements Choreographer.FrameCallback {
    public static final String TAG = "ScrollFPS";
    public static long lastFrameTimeNanos = 0;
    public static long currentFrameTimeNanos = 0;
    public static IFrameCallBack mFrameCallBack;
    public static float deviceRefreshRate = 60;

    public IFrameCallBack(float refreshRate) {
        deviceRefreshRate = refreshRate;
    }

    public IFrameCallBack() {
    }

    public void start() {
        Log.e(TAG, "start");
        Choreographer.getInstance().postFrameCallback(IFrameCallBack.getInstance());
    }

    public void stop() {
        Log.e(TAG, "stop");
        Choreographer.getInstance().removeFrameCallback(IFrameCallBack.getInstance());
    }

    public static IFrameCallBack getInstance() {
        if (mFrameCallBack == null) {
            mFrameCallBack = new IFrameCallBack();
        }
        return mFrameCallBack;
    }

    public static IFrameCallBack getInstance(float refreshRate) {
        if (mFrameCallBack == null) {
            mFrameCallBack = new IFrameCallBack(refreshRate);
        }
        deviceRefreshRate = refreshRate;
        return mFrameCallBack;
    }

    @Override
    public void doFrame(long frameTimeNanos) {
        if (lastFrameTimeNanos == 0) {
            lastFrameTimeNanos = frameTimeNanos;
            Choreographer.getInstance().postFrameCallback(this);
            return;
        }
        currentFrameTimeNanos = frameTimeNanos;

        long render = TimeUnit.MILLISECONDS.convert(currentFrameTimeNanos - lastFrameTimeNanos
                , TimeUnit.NANOSECONDS);

        int skipFrameNum = skipFrameNum(render, deviceRefreshRate);
        if (skipFrameNum != 0)
            Log.e(TAG, "render=" + render + "  lastFrameTimeNanos=" + lastFrameTimeNanos + "  currentFrameTimeNanos=" + currentFrameTimeNanos + "  skipFrameNum=" + skipFrameNum);
        lastFrameTimeNanos = currentFrameTimeNanos;
        Choreographer.getInstance().postFrameCallback(this);
    }


    private int skipFrameNum(long render, float devRefreshRate) {
        int num = 0;
        float timePerFrame = 1000.0f / devRefreshRate;
        if (render > timePerFrame) {
            float skipnum = render / timePerFrame;
            num = (int) skipnum;
        }
        return num;
    }

}
