package com.example.slope.androiddriver;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.slope.androiddriver.basicclass.News;
import com.example.slope.androiddriver.database.ServicesDataBase;
import com.example.slope.androiddriver.newshttp.INewsData;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsActivity extends AppCompatActivity implements INewsData {

    @BindView(R.id.news_bar)
    ProgressBar newsBar;
    @BindView(R.id.news_webview)
    WebView newsWebview;
    String url;
    String title;
    News news=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        ButterKnife.bind(this);
        url = getIntent().getStringExtra("url");
        initWebView();
        initRequest();
    }
    private void initRequest(){

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //右边的三个小点
        getMenuInflater().inflate(R.menu.discuss, menu);

        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();


        if (id == R.id.action_settings) {
            if (news!=null) {
                Cursor cursor=new ServicesDataBase(this).selectOneNew(news);
                if (cursor.moveToNext()){
                    Toast.makeText(NewsActivity.this, "你已经收藏过该新闻了！", Toast.LENGTH_SHORT).show();
                }
                 else if (-1!=new ServicesDataBase(this).addOne(news)){

                    Toast.makeText(this, "收藏成功", Toast.LENGTH_LONG).show();

                }
            }
            else {
                Toast.makeText(this, "loading...", Toast.LENGTH_LONG).show();
            }

            return true;
        }


        return super.onOptionsItemSelected(item);
    }
    public void initWebView() {
        WebSettings webSettings = newsWebview.getSettings();
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);

        newsWebview.getSettings().setJavaScriptEnabled(true);//是否使用JavaScript脚本
        newsWebview.getSettings().setSupportMultipleWindows(true);//是否支持手势缩放
        newsWebview.getSettings().setBuiltInZoomControls(true);//是否支持缩放

        //WebView加载数据
        newsWebview.loadUrl(url);
        // 设置WebView上所有超链接打开时使用当前Activity
        newsWebview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                newsWebview.loadUrl(url);
                return true;
            }
        });
        // 设置进度显示到ProgressBar控件
        newsWebview.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress < 100) {
                    newsBar.setProgress(newProgress);
                    newsBar.setVisibility(View.VISIBLE);
                } else if (newProgress >= 100) {
                    newsBar.setVisibility(View.GONE);
                }
            }
        });
        WebChromeClient chromeClient=new WebChromeClient(){
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                NewsActivity.this.title=title;
                news=new News(title,url);
            }
        };
        newsWebview.setWebChromeClient(chromeClient);
    }

    @Override
    public void onBackPressed() {
        if (newsWebview.canGoBack()) {
            newsWebview.goBack();
        } else {
            finish();
        }

    }

    @Override
    public void NewsString(String result) {
        try {
            JSONObject object=new JSONObject(result);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
