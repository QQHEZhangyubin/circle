package com.guoyi.circle;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.mabeijianxi.smallvideorecord2.DeviceUtils;
import com.mabeijianxi.smallvideorecord2.JianXiCamera;

import java.io.File;


/**
 * Created by Credit on 2017/3/3.
 */

public class MyApplication extends Application {
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        MultiDex.install(this);
        initSmallVideo(this);
    }

    public static void initSmallVideo(Context context) {
        // 设置拍摄视频缓存路径
        File dcim = context.getExternalFilesDir("small-video");

        if (!dcim.exists()) {
            dcim.mkdirs();
        }

        if (DeviceUtils.isZte()) {
            if (dcim.exists()) {
                JianXiCamera.setVideoCachePath(dcim.toString());
            } else {
                JianXiCamera.setVideoCachePath(dcim.getPath().replace("/sdcard/",
                        "/sdcard-ext/")
                        + "/circle/");
            }
        } else {
            JianXiCamera.setVideoCachePath(dcim.toString());
        }
        JianXiCamera.initialize(false,null);
    }

    public static Context getContext() {
        return mContext;
    }
}