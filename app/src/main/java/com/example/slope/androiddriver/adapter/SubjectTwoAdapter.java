package com.example.slope.androiddriver.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.slope.androiddriver.R;

/**
 * Created by Slope on 2016/9/13.
 */
public class SubjectTwoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private String[] title;
    private int[] image;
    private OnItemClickListener mItemClickListener;

    public SubjectTwoAdapter(Context context, String[] title, int[] image) {
        this.context = context;
        this.title = title;
        this.image = image;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.web_subject_item, parent, false);
        RecyclerView.ViewHolder holder = new MyViewHolder(view, mItemClickListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyViewHolder) {
            ((MyViewHolder) holder).tv.setText(title[position]);
            ((MyViewHolder) holder).iv.setImageResource(image[position]);
        }
    }

    @Override
    public int getItemCount() {
        return title.length;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tv;
        public ImageView iv;

        public MyViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.one_tv_dc);
            iv = (ImageView) itemView.findViewById(R.id.one_img_dc);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(v, getLayoutPosition());
                }
            });
        }
    }

    /***
     * 监听点击事件接口
     */
    public interface OnItemClickListener {
        public void onItemClick(View view, int postion);
    }

    /***
     * 设置item点击事件
     *
     * @param listener
     */
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mItemClickListener = listener;
    }
}
