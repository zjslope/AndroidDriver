package com.example.slope.androiddriver.http;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.slope.androiddriver.json.CarJson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;

/**
 * Created by Slope on 2016/9/19.
 */
public class HttpResultResponse implements Callback.CommonCallback<String> {
    DataResult dataResult;
    Context context;
    int  type;
    public HttpResultResponse(DataResult dataResult, Context applicationContext,int  type) {
        this.dataResult = dataResult;
        this.context=applicationContext;
        this.type=type;
    }

    @Override
    public void onSuccess(String result) {
        try {
            JSONObject rootObj=new JSONObject(result);
            if (0!=rootObj.getInt("error_code")){
                Toast.makeText(context, "获取题目信息失败", Toast.LENGTH_SHORT).show();
            }
            //获取考题
            JSONArray array=rootObj.optJSONArray("result");
            dataResult.resultList(CarJson.superJson(array),type);
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
