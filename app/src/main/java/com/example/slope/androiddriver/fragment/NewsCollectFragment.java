package com.example.slope.androiddriver.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.slope.androiddriver.MainActivity;
import com.example.slope.androiddriver.NewsActivity;
import com.example.slope.androiddriver.QuestionActivity;
import com.example.slope.androiddriver.R;
import com.example.slope.androiddriver.UniversalActivity;
import com.example.slope.androiddriver.adapter.MoveAdapter;
import com.example.slope.androiddriver.adapter.OnItemClickListener;
import com.example.slope.androiddriver.basicclass.News;
import com.example.slope.androiddriver.database.ServicesDataBase;
import com.example.slope.androiddriver.json.ParseNews;
import com.example.slope.androiddriver.newshttp.INewsData;
import com.example.slope.androiddriver.newshttp.RequestNewsImpl;
import com.example.slope.androiddriver.newshttp.ResponseNews;
import com.example.slope.androiddriver.widget.ItemRemoveRecyclerView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.slope.androiddriver.R.id.framelayout;

/**
 * Created by Slope on 2016/9/10.
 */
public class NewsCollectFragment extends Fragment implements INewsData {
    @BindView(R.id.id_item_remove_recyclerview)
    ItemRemoveRecyclerView recyclerView;
    private List<News> list;
    private View rootView;
    News news;
    int listSize=0;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_news_collect, null);

        }
        ViewGroup group = (ViewGroup) rootView.getRootView();
        if (group != null) {
            group.removeView(rootView);
        }
        ButterKnife.bind(this, rootView);

            init();

            initDialog();

        return rootView;
    }

    private void initDialog() {
       Cursor cursor= new ServicesDataBase(getContext()).selectAllNew();
        if (cursor.moveToNext()){

        }
        else {


        new AlertDialog.Builder(getContext())
                .setTitle("未收藏")
                .setMessage("您还没有收藏新闻")
                .setPositiveButton("现在去收藏", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(getActivity(), MainActivity.class));

                    }
                })
                .setNegativeButton("我在逛逛", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(getActivity(), MainActivity.class));
                    }
                })
                .show();
        }
    }

    private void init() {
        new RequestNewsImpl().getNews(new ResponseNews(this));
        list = getNews();

        final MoveAdapter adapter = new MoveAdapter(getContext(), list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        recyclerView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
//                Toast.makeText(getActivity(), "** " + title[position] + " **", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getActivity(), NewsActivity.class);
                i.putExtra("url", list.get(position).getArticle_url());
                getActivity().startActivity(i);
            }

            @Override
            public void onDeleteClick(int position) {
                new ServicesDataBase(getContext()).delete(list.get(position));
                adapter.removeItem(position);
                adapter.notifyItemRemoved(position);

            }
        });
    }

    @Override
    public void NewsString(String result) {
        MoveAdapter adapter = null;
        try {
            JSONObject object = new JSONObject(result);
            if (object.getString("status").equals("000000")) {
//                list.addAll(ParseNews.pareNews(result));
//                adapter.notifyDataSetChanged();

            } else {
                Toast.makeText(getActivity(), "获取新闻失败", Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public List<News> getNews() {
        List<News> list = new ArrayList<News>();
        Cursor cursor = new ServicesDataBase(getContext()).selectAllNew();
        while (cursor.moveToNext()) {
            News news = new News(cursor.getString(cursor.getColumnIndex("title")),
                    cursor.getString(cursor.getColumnIndex("article_url")));
            list.add(news);
        }

        return list;

    }



}
