package com.example.slope.androiddriver.myserverhttp;

import android.util.Log;

import com.example.slope.androiddriver.json.ParseTalk;

import org.xutils.common.Callback;

/**
 * Created by Slope on 2016/9/16.
 */
public class MyTalkResultResponse implements Callback.CommonCallback<String> {
    MyDetaResult myDetaResult;

    public MyTalkResultResponse(MyDetaResult myDetaResult) {
        this.myDetaResult = myDetaResult;
    }

    @Override
    public void onSuccess(String result) {
        myDetaResult.myserverListForTalk(ParseTalk.parse(result));

    }



    @Override
    public void onError(Throwable ex, boolean isOnCallback) {
        Log.v("---------------",ex.toString());
    }

    @Override
    public void onCancelled(CancelledException cex) {

    }

    @Override
    public void onFinished() {

    }
}
