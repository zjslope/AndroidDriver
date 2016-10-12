package com.example.slope.androiddriver.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.slope.androiddriver.NewsActivity;
import com.example.slope.androiddriver.R;
import com.example.slope.androiddriver.WebActivity;
import com.example.slope.androiddriver.adapter.HomeRecyclerViewAdapter;
import com.example.slope.androiddriver.basicclass.News;
import com.example.slope.androiddriver.json.ParseNews;
import com.example.slope.androiddriver.newshttp.INewsData;
import com.example.slope.androiddriver.newshttp.RequestNewsImpl;
import com.example.slope.androiddriver.newshttp.ResponseNews;
import com.example.slope.androiddriver.utils.RecyclerViewListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.relex.circleindicator.CircleIndicator;

/**
 * Created by Slope on 2016/9/10.
 */
public class NewsFragment extends Fragment implements INewsData {
    @BindView(R.id.news_recyclerView)
    RecyclerView newsRecyclerView;
     View rootView;
    ViewPager viewPager;
    CircleIndicator circleIndicator;
    List<News> list;
    HomeRecyclerViewAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_news,container, false);
            ButterKnife.bind(this, rootView);
            init();
        }
//        ViewGroup group = (ViewGroup) rootView.getRootView();
//        if (group != null) {
//            group.removeView(rootView);
//        }

        return rootView;
    }
    private  void init(){
      new RequestNewsImpl().getNews(new ResponseNews(this));
        list=new ArrayList<News>();
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        newsRecyclerView.setLayoutManager(linearLayoutManager);
        newsRecyclerView.setItemAnimator(new DefaultItemAnimator());
        View headerView=getActivity().getLayoutInflater().inflate(R.layout.adv,newsRecyclerView,false);
        adapter=new HomeRecyclerViewAdapter(getActivity(),list);
        newsRecyclerView.setAdapter(adapter);
        adapter.setHeaderView(headerView);
    }
    @Override
    public void NewsString(String result) {
        parseNews(result);
    }
    public void parseNews(String data){
        try {
            JSONObject object=new JSONObject(data);
            if (object.getString("reason").equals("成功的返回")){
                JSONObject result=object.getJSONObject("result");
                list.addAll(ParseNews.pareNews(result));
                adapter.notifyDataSetChanged();
                adapter.setRecyclerViewListener(new RecyclerViewListener() {
                    @Override
                    public void onClick(int positon) {
                        Intent intent=new Intent(getActivity(), NewsActivity.class);
                        intent.putExtra("url",list.get(positon).getArticle_url());
                        getActivity().startActivity(intent);
                        //getActivity().overridePendingTransition(R.anim.anim_in_right_left,R.anim.anim_out_right_left);
                    }
                });
            }
            else {
                Snackbar.make(viewPager,"新闻获取失败",Snackbar.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (rootView!=null){

            ((ViewGroup)rootView.getParent()).removeView(rootView);
        }
    }
}
