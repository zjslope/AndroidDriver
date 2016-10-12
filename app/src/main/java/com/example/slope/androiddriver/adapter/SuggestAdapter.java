package com.example.slope.androiddriver.adapter;

import android.content.Context;
import android.graphics.MaskFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.slope.androiddriver.R;
import com.example.slope.androiddriver.basicclass.Msg;
import com.example.slope.androiddriver.basicclass.Talk;

import java.util.List;

/**
 * Created by Slope on 2016/9/21.
 */
public class SuggestAdapter extends ArrayAdapter<Msg> {
    private int resourceId;


    public SuggestAdapter(Context context, int textViewResourceId, List<Msg> objects){
        super(context,textViewResourceId,objects);
        resourceId=textViewResourceId;
    }
    public View getView(int position, View convertView, ViewGroup parent){
        Msg msg=getItem(position);
        View view;
        ViewHolder viewHolder;
        if (convertView==null){
            view= LayoutInflater.from(getContext()).inflate(resourceId,null);
            viewHolder=new ViewHolder();
            viewHolder.leftLayout= (LinearLayout) view.findViewById(R.id.left_layout_suggest);
            viewHolder.rightLayout= (LinearLayout) view.findViewById(R.id.right_layout_suggest);
            viewHolder.leftMsg= (TextView) view.findViewById(R.id.left_suggest);
            viewHolder.rightMsg= (TextView) view.findViewById(R.id.right_suggest);

            view.setTag(viewHolder);
        }else {
            view=convertView;
            viewHolder= (ViewHolder) view.getTag();
        }
       if (msg.getType()==Msg.TYPE_RECEIVED){
           viewHolder.leftLayout.setVisibility(View.VISIBLE);
           viewHolder.rightLayout.setVisibility(View.GONE);
           viewHolder.leftMsg.setText(msg.getContent());
       }
        else if (msg.getType()==Msg.TYPE_SENT){
           viewHolder.rightLayout.setVisibility(View.VISIBLE);
           viewHolder.leftLayout.setVisibility(View.GONE);
           viewHolder.rightMsg.setText(msg.getContent());
       }
        return view;
    }
    class ViewHolder{
        LinearLayout leftLayout;
        LinearLayout rightLayout;
        TextView leftMsg;
        TextView rightMsg;

    }
}
