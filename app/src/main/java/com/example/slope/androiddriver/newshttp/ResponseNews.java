package com.example.slope.androiddriver.newshttp;

import org.xutils.common.Callback;

/**
 * Created by Slope on 2016/9/16.
 */
public class ResponseNews implements Callback.CommonCallback<String>{
    private static final String TAG = "ResponseNews";
    INewsData iNewsData;
    public ResponseNews(INewsData iNewsData) {
        this.iNewsData = iNewsData;
    }
    @Override
    public void onSuccess(String result) {
        iNewsData.NewsString(result);

    }

    @Override
    public void onError(Throwable ex, boolean isOnCallback) {

    }

    @Override
    public void onCancelled(CancelledException cex) {

    }

    @Override
    public void onFinished() {

    }
}
