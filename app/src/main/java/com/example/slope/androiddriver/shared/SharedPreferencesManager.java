package com.example.slope.androiddriver.shared;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Slope on 2016/9/11.
 */
public class SharedPreferencesManager {

    public static void setLogin(Context context,boolean isOffLogin){
        SharedPreferences.Editor editor=context.getSharedPreferences("login",Context.MODE_PRIVATE).edit();
        editor.putBoolean("login",isOffLogin);
        editor.commit();

    }
    public static boolean checkIsLogin(Context context){
        SharedPreferences sharedPreferences=context.getSharedPreferences("login",Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean("login",false);
    }
    public static boolean fragment(Context context){
        SharedPreferences sharedPreferences=context.getSharedPreferences("title",Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean("title",false);
    }
    public static String getMyName(Context context){
        SharedPreferences sharedPreferences=context.getSharedPreferences("name",Context.MODE_PRIVATE);
        return sharedPreferences.getString("name","XXX");

    }
    public static String getOneScore(Context context){
        SharedPreferences sharedPreferences=context.getSharedPreferences("onescore",Context.MODE_PRIVATE);
        return sharedPreferences.getString("onescore","XXX");
    }
    public static String getFourScore(Context context){
        SharedPreferences sharedPreferences=context.getSharedPreferences("fourscore",Context.MODE_PRIVATE);
        return sharedPreferences.getString("fourscore","XXX");
    }
    public static String getOneError(Context context){
        SharedPreferences sharedPreferences=context.getSharedPreferences("oneerror",Context.MODE_PRIVATE);
        return sharedPreferences.getString("oneerror","XXX");
    }
    public static String getFourError(Context context){
        SharedPreferences sharedPreferences=context.getSharedPreferences("fourerror",Context.MODE_PRIVATE);
        return sharedPreferences.getString("fourerror","XXX");
    }
}
