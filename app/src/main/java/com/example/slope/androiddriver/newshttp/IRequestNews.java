package com.example.slope.androiddriver.newshttp;


import org.xutils.common.Callback;

/**
 * Created by Slope on 2016/9/16.
 */
public interface IRequestNews {
    public void getNews(Callback.CommonCallback<String> commonCallback);
}
