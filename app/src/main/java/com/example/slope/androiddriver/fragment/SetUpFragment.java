package com.example.slope.androiddriver.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.slope.androiddriver.R;
import com.example.slope.androiddriver.SuggestActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Slope on 2016/9/10.
 */
public class SetUpFragment extends Fragment {
    @BindView(R.id.updates)
    LinearLayout updates;
    @BindView(R.id.suggest)
    LinearLayout suggest;
    @BindView(R.id.code)
    LinearLayout code;
    private View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_set_up, null);
            init();
        }
        ViewGroup group = (ViewGroup) rootView.getRootView();
        if (group != null) {
            group.removeView(rootView);
        }
        init();
        ButterKnife.bind(this, rootView);
        return rootView;

    }

    private void init() {


    }
    @OnClick({R.id.updates, R.id.suggest, R.id.code})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.updates:
                new AlertDialog.Builder(getContext())
                        .setTitle("检查更新")
                        .setMessage("以是最新版本")
                        .setPositiveButton("好", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getActivity(), "新版本正在开发中敬请期待！", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();
                break;
            case R.id.suggest:
                startActivity(new Intent(getActivity(), SuggestActivity.class));
                break;
            case R.id.code:
                LayoutInflater falter= LayoutInflater.from(getContext());
                View v=falter.inflate(R.layout.dialog,null);
                AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
                builder.setView(v);
                AlertDialog dialog=builder.create();
                dialog.show();
                break;
        }
    }
}

