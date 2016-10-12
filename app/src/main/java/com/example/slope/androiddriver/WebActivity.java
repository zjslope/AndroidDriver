package com.example.slope.androiddriver;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Slope on 2016/9/13.
 */
public class WebActivity extends AppCompatActivity {
    @BindView(R.id.main_bar)
    ProgressBar mainBar;
    @BindView(R.id.main_webview)
    WebView mainWebview;
    String title;
    String url;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_activity);
        ButterKnife.bind(this);
        title=getIntent().getStringExtra("title");
        url=getIntent().getStringExtra("url");
        initWebView();
    }
    public void initWebView(){
        WebSettings webSettings=mainWebview.getSettings();
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        // WebView基本属性设置如下
        mainWebview.getSettings().setJavaScriptEnabled(true);//是否使用JavaScript脚本
        mainWebview.getSettings().setSupportMultipleWindows(true);//是否支持手势缩放
        mainWebview.getSettings().setBuiltInZoomControls(true);//是否支持缩放

        //WebView加载数据
        mainWebview.loadUrl(url);
        // 设置WebView上所有超链接打开时使用当前Activity
        mainWebview.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                mainWebview.loadUrl(url);
                return true;
            }
        });
        // 设置进度显示到ProgressBar控件
        mainWebview.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress<100){
                    mainBar.setProgress(newProgress);
                    mainBar.setVisibility(View.VISIBLE);
                }else if (newProgress>=100){
                    mainBar.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (mainWebview.canGoBack()){
            mainWebview.goBack();
        }else {
//            Toast.makeText(WebActivity.this, "退出", Toast.LENGTH_SHORT).show();
            finish();
        }

    }
}
