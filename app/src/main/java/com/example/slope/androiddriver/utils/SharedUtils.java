package com.example.slope.androiddriver.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SharedUtils {
	/**
	 * user if first use.
	 */
	private static final String KEY_FIRST = "isfirst" ;
	private static final String FILENAME_FIRST = "first" ;
	public static void saveNotFirst(Context context) {
		SharedPreferences prefs = context.getSharedPreferences(FILENAME_FIRST, Context.MODE_PRIVATE) ;
		Editor edit = prefs.edit() ;
		edit.putBoolean(KEY_FIRST, false) ;
		edit.commit() ;
	}
	public static boolean isFirst(Context context) {
		SharedPreferences prefs = context.getSharedPreferences(FILENAME_FIRST, Context.MODE_PRIVATE) ;
		return prefs.getBoolean(KEY_FIRST, true) ;
	}
	/**
	 * user password
	 * @param context
	 * @param pwd
	 */
	public static void savePwd(Context context ,String pwd) {
		SharedPreferences prefs = context.getSharedPreferences("pwd", Context.MODE_PRIVATE) ;
		Editor edit = prefs.edit() ;
		edit.putString("pwd", pwd) ;
		edit.commit() ;
	}
	public static String getPwd(Context context) {
		SharedPreferences prefs = context.getSharedPreferences("pwd", Context.MODE_PRIVATE) ;
		return prefs.getString("pwd", null) ;
	}
	
	public static void saveIsSafe(Context context , boolean isSafe) {
		SharedPreferences prefs = context.getSharedPreferences("safe", Context.MODE_PRIVATE) ;
		Editor edit = prefs.edit() ;
		edit.putBoolean("safe", isSafe) ;
		edit.commit() ;
	}
	public static boolean getIsSafe(Context context) {
		SharedPreferences prefs = context.getSharedPreferences("safe", Context.MODE_PRIVATE) ;
		return prefs.getBoolean("safe", false) ;
	}
}
