package com.example.slope.androiddriver;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AboutCarActivity extends AppCompatActivity {

    @BindView(R.id.aboutcar_bar)
    ProgressBar aboutcarBar;
    @BindView(R.id.aboutcar_webview)
    WebView aboutcarWebview;
    String url;
    String title;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_car);
        ButterKnife.bind(this);
        url = getIntent().getStringExtra("url");
        title = getIntent().getStringExtra("title");
        initWebView();
        ActionBar actionBar=getSupportActionBar();
      actionBar.setTitle(title);

    }

    public void initWebView() {
        WebSettings webSettings = aboutcarWebview.getSettings();
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);

        aboutcarWebview.getSettings().setJavaScriptEnabled(true);//是否使用JavaScript脚本
        aboutcarWebview.getSettings().setSupportMultipleWindows(true);//是否支持手势缩放
        aboutcarWebview.getSettings().setBuiltInZoomControls(true);//是否支持缩放

        //WebView加载数据
        aboutcarWebview.loadUrl(url);
        // 设置WebView上所有超链接打开时使用当前Activity
        aboutcarWebview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                aboutcarWebview.loadUrl(url);
                return true;
            }
        });
        // 设置进度显示到ProgressBar控件
        aboutcarWebview.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress < 100) {
                    aboutcarBar.setProgress(newProgress);
                    aboutcarBar.setVisibility(View.VISIBLE);
                } else if (newProgress >= 100) {
                    aboutcarBar.setVisibility(View.GONE);
                }
            }
        });

    }

    @Override
    public void onBackPressed() {
        if (aboutcarWebview.canGoBack()) {
            aboutcarWebview.goBack();
        } else {
            finish();
        }

    }
}
