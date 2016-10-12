package com.example.slope.androiddriver.adapter;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.slope.androiddriver.R;
import com.example.slope.androiddriver.basicclass.News;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;


/**
 * Created by Slope on 2016/9/16.
 */
public class MoveAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private LayoutInflater mInflater;

    private List<News>list;

    public MoveAdapter(Context mContext, List<News>list) {
        this.mContext = mContext;
        this.list=list;

        mInflater = LayoutInflater.from(mContext);

    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MoveMyViewHolder(mInflater.inflate(R.layout.news_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final MoveMyViewHolder viewHolder = (MoveMyViewHolder) holder;
        viewHolder.title.setText(list.get(position).getTitle());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void removeItem(int position) {
      list.remove(position);
     // notifyDataSetChanged();
    }

}
