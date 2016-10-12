package com.example.slope.androiddriver.json;

import android.util.Log;

import com.example.slope.androiddriver.basicclass.Talk;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Slope on 2016/9/16.
 */
public class ParseTalk {
    public static List<Talk> parse(String result) {
        List<Talk> list = new ArrayList<Talk>();
        try {
            JSONArray array = new JSONArray(result);
            for (int i = 0; i <= array.length();i++){
                JSONObject talkObj=array.getJSONObject(i);
                Talk talk=new Talk(talkObj.getString("time"),talkObj.getString("talk")
                        ,talkObj.getString("username"));
                list.add(talk);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

}
