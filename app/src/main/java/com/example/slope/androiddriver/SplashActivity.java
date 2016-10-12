package com.example.slope.androiddriver;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.slope.androiddriver.shared.SharedPreferencesManager;
import com.example.slope.androiddriver.utils.SharedUtils;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 判断如果是第一次运行本项目，加载GuideActivity
        if(SharedUtils.isFirst(this)) {
            startActivity(new Intent(SplashActivity.this , GuideActivity.class)) ;
            finish();			// 关闭一个Activity
            return ;
        }
        setContentView(R.layout.activity_splash);
        // 界面跳转
        new Handler().postDelayed(new Runnable() {
                                      @Override
                                      public void run() {
                                          startActivity(new Intent(SplashActivity.this , MainActivity.class)) ;
                                          finish();			// 关闭一个Activity
                                      }
                                  }, 	2000		// 2000毫秒后跳转
        ) ;
    }
}
