package com.example.slope.androiddriver.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.slope.androiddriver.R;
import com.example.slope.androiddriver.basicclass.Msg;
import com.example.slope.androiddriver.basicclass.Talk;
import com.example.slope.androiddriver.shared.SharedPreferencesManager;

import java.util.List;

/**
 * Created by Slope on 2016/9/13.
 */
public class MsgAdapter extends ArrayAdapter<Talk> {
private int resourceId;
private List<Talk> list;
private String myName;

    public MsgAdapter(Context context, int textViewResourceId, List<Talk> objects) {
        super(context, textViewResourceId,objects);
        resourceId=textViewResourceId;
        list=objects;
        myName= SharedPreferencesManager.getMyName(context);
    }
    public View getView(int position, View convertView, ViewGroup parent){
        Talk talk=list.get(position);
        View view;
        ViewHolder viewHolder;
        if (convertView==null){
            view= LayoutInflater.from(getContext()).inflate(resourceId,null);
            viewHolder=new ViewHolder();
            viewHolder.leftLayout= (LinearLayout) view.findViewById(R.id.left_layout);
            viewHolder.rightLayout= (LinearLayout) view.findViewById(R.id.right_layout);
            viewHolder.leftMsg= (TextView) view.findViewById(R.id.left_msg);
            viewHolder.rightMsg= (TextView) view.findViewById(R.id.right_msg);
            viewHolder.leftName= (TextView) view.findViewById(R.id.left_name);
            viewHolder.rightName= (TextView) view.findViewById(R.id.right_name);
            viewHolder.left_deta= (TextView) view.findViewById(R.id.left_deta);
            viewHolder.right_deta= (TextView) view.findViewById(R.id.right_deta);
            view.setTag(viewHolder);
        }else {
            view=convertView;
            viewHolder= (ViewHolder) view.getTag();
        }
        if (!myName.equals(talk.getUsername())){
            //如果是收到的消息，则显示左边的消息布局，将右边的消息布局隐藏
            viewHolder.leftLayout.setVisibility(View.VISIBLE);
            viewHolder.rightLayout.setVisibility(View.GONE);
            viewHolder.left_deta.setVisibility(View.VISIBLE);
            viewHolder.right_deta.setVisibility(View.GONE);
            viewHolder.leftMsg.setText(talk.getTalk());
            viewHolder.leftName.setText(talk.getUsername()+":");
            viewHolder.left_deta.setText(talk.getTime());

        }else{
            //如果是发出的消息，则显示右边的消息布局，将左边的消息布局隐藏
            viewHolder.rightLayout.setVisibility(View.VISIBLE);
            viewHolder.leftLayout.setVisibility(View.GONE);
            viewHolder.left_deta.setVisibility(View.GONE);
            viewHolder.right_deta.setVisibility(View.VISIBLE);
            viewHolder.rightMsg.setText(talk.getTalk());
            viewHolder.rightName.setText(":"+talk.getUsername());
            viewHolder.right_deta.setText(talk.getTime());
        }
        return view;
    }
    class ViewHolder{
        LinearLayout leftLayout;
        LinearLayout rightLayout;
        TextView leftMsg;
        TextView rightMsg;
        TextView leftName;
        TextView rightName;
        TextView left_deta;
        TextView right_deta;
    }
}
