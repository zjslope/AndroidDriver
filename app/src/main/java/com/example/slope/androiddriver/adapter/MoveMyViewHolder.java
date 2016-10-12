package com.example.slope.androiddriver.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.slope.androiddriver.R;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;


/**
 * Created by Slope on 2016/9/16.
 */
public class MoveMyViewHolder extends RecyclerView.ViewHolder {
    public TextView title;
    public TextView delete;

    public LinearLayout layout;

    public MoveMyViewHolder(View itemView) {
        super(itemView);

        title = (TextView) itemView.findViewById(R.id.item_content);
        delete = (TextView) itemView.findViewById(R.id.item_delete);

        layout = (LinearLayout) itemView.findViewById(R.id.item_layout);

    }
}
