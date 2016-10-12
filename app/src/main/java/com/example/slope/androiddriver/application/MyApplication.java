package com.example.slope.androiddriver.application;

import android.app.Application;

import org.xutils.x;

/**
 * Created by Slope on 2016/9/10.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(true);
    }
}
