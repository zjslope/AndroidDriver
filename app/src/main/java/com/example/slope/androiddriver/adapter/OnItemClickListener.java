package com.example.slope.androiddriver.adapter;

import android.view.View;

/**
 * Created by Slope on 2016/9/16.
 */
public interface OnItemClickListener {
    /**
     * item点击回调
     *
     * @param view
     * @param position
     */
    void onItemClick(View view, int position);

    /**
     * 删除按钮回调
     *
     * @param position
     */
    void onDeleteClick(int position);
}
