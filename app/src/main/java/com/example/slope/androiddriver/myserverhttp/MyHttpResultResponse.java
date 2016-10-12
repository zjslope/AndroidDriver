package com.example.slope.androiddriver.myserverhttp;

import android.content.Context;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;

/**
 * Created by Slope on 2016/9/11.
 */
public class MyHttpResultResponse implements Callback.CommonCallback<String> {
    MyDetaResult myDetaResult;
    Context context;

    public MyHttpResultResponse(MyDetaResult myDetaResult, Context context) {
        this.myDetaResult = myDetaResult;
        this.context = context;
    }

    @Override
    public void onSuccess(String result) {
        try {
            JSONObject rootObject=new JSONObject(result);
            if ("0"!=rootObject.getString("result")){
                Toast.makeText(context, "获取用户信息失败", Toast.LENGTH_SHORT).show();
            }

            myDetaResult.mysrvserObject(result);
        } catch (JSONException e) {
            e.printStackTrace();
        }

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
