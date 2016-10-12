package com.example.slope.androiddriver.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.slope.androiddriver.R;
import com.example.slope.androiddriver.adapter.MsgAdapter;
import com.example.slope.androiddriver.basicclass.Talk;
import com.example.slope.androiddriver.basicclass.User;
import com.example.slope.androiddriver.myserverhttp.MyDetaResult;
import com.example.slope.androiddriver.myserverhttp.MyTalkResultResponse;
import com.example.slope.androiddriver.shared.SharedPreferencesManager;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.example.slope.androiddriver.http.MyURL;

/**
 * Created by Slope on 2016/9/10.
 */
public class CarRingFragment extends Fragment implements MyDetaResult{
    @BindView(R.id.msg_list_view)
    ListView msgListView;
    @BindView(R.id.input_text)
    EditText inputText;
    @BindView(R.id.send)
    Button send;
    private View rootView;
    private MsgAdapter adapter;
    private List<Talk>magList;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_car_ring, null);

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
        RequestParams params=new RequestParams(MyURL.MY_SERVWE_SHOWTALK);
        x.http().get(params,new MyTalkResultResponse(this));

    }

    @OnClick(R.id.send)
    public void onClick(View view) {

        String content=inputText.getText().toString().trim();
        if (!"".equals(content)){
            Talk talk=new Talk(new SimpleDateFormat("yyyy-MM-dd hh:mm").format(new Date()),content, SharedPreferencesManager.getMyName(getContext()));
            String url=MyURL.MY_SERVWE_TALK;
            RequestParams params=new RequestParams(url);
            params.addQueryStringParameter("time",talk.getTime());
            params.addQueryStringParameter("usertalk",talk.getTalk());
            params.addQueryStringParameter("username",talk.getUsername());
           x.http().get(params, new Callback.CommonCallback<String>() {
               @Override
               public void onSuccess(String result) {

               }

               @Override
               public void onError(Throwable ex, boolean isOnCallback) {
                    Log.v("-----------------====",ex.toString());
               }

               @Override
               public void onCancelled(CancelledException cex) {

               }

               @Override
               public void onFinished() {

               }
           });
            magList.add(talk);
            adapter.notifyDataSetChanged();//当有新消息时，刷新listView中的显示
            msgListView.setSelection(magList.size());//将listView拉到最后一行
            inputText.setText("");//清空输入框的内容
        }
    }

    @Override
    public void mysrvserObject(String objects) {
    }

    @Override
    public void myserverList(List<User> list) {

    }

    @Override
    public void myserverListForTalk(List<Talk> parse) {
        magList=parse;
        adapter=new MsgAdapter(getContext(),R.layout.msg_item,magList);
        msgListView.setAdapter(adapter);
    }

    @Override
    public void onPause() {
        super.onPause();
        magList.clear();
    }

    @Override
    public void onResume() {
        super.onResume();
        init();
    }

}
