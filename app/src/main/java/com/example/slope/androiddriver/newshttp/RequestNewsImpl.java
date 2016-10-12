package com.example.slope.androiddriver.newshttp;



import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import com.example.slope.androiddriver.http.MyURL;

/**
 * Created by Slope on 2016/9/16.
 */
public class RequestNewsImpl implements IRequestNews {
    @Override
    public void getNews(Callback.CommonCallback<String> commonCallback) {
        RequestParams params=new RequestParams(MyURL.NEWSURL);
        x.http().get(params,commonCallback);
    }
}
