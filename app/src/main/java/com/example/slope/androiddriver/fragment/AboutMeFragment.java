package com.example.slope.androiddriver.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.example.slope.androiddriver.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.slope.androiddriver.http.MyURL;

/**
 * Created by Slope on 2016/9/10.
 */
public class AboutMeFragment extends Fragment{

    @BindView(R.id.aboutme_bar)
    ProgressBar aboutmeBar;
    @BindView(R.id.aboutme_webview)
    WebView aboutmeWebview;
    private View rootView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_about_me, null);

        }
        ViewGroup group = (ViewGroup) rootView.getRootView();
        if (group != null) {
            group.removeView(rootView);
        }
        ButterKnife.bind(this, rootView);
        init();
        return rootView;
    }

    private void init() {
        WebSettings webSettings=aboutmeWebview.getSettings();
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        // WebView基本属性设置如下
        aboutmeWebview.getSettings().setJavaScriptEnabled(true);//是否使用JavaScript脚本
        aboutmeWebview.getSettings().setSupportMultipleWindows(true);//是否支持手势缩放
        aboutmeWebview.getSettings().setBuiltInZoomControls(true);//是否支持缩放

        //WebView加载数据
        aboutmeWebview.loadUrl(MyURL.ABOUT_ME);
        // 设置WebView上所有超链接打开时使用当前Activity
        aboutmeWebview.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                aboutmeWebview.loadUrl(url);
                return true;
            }
        });
        // 设置进度显示到ProgressBar控件
        aboutmeWebview.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress<100){
                    aboutmeBar.setProgress(newProgress);
                    aboutmeBar.setVisibility(View.VISIBLE);
                }else if (newProgress>=100){
                    aboutmeBar.setVisibility(View.GONE);
                }
            }
        });



    }
    public boolean gotoBack(){
        if (aboutmeWebview.canGoBack()){
            aboutmeWebview.goBack();
            return true;
        }
        else {
            return false;
        }
    }
    void initto(){
        aboutmeWebview.getSettings().setJavaScriptEnabled(true);

        aboutmeWebview.loadUrl("http://weibo.com/zjslope");
        aboutmeWebview.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                aboutmeWebview.loadUrl(url);
                return true;
            }
        });
        WebChromeClient chromeClient=new WebChromeClient(){
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
            }
        };
        aboutmeWebview.setWebChromeClient(chromeClient);
    }

}
