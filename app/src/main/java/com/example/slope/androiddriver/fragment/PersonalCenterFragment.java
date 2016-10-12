package com.example.slope.androiddriver.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.slope.androiddriver.MyErrQuestionActivity;
import com.example.slope.androiddriver.R;
import com.example.slope.androiddriver.shared.SharedPreferencesManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Slope on 2016/9/10.
 */
public class PersonalCenterFragment extends Fragment {
    @BindView(R.id.profile_image)
    CircleImageView profileImage;
    @BindView(R.id.username)
    TextView username;
    @BindView(R.id.userheader)
    RelativeLayout userheader;
    @BindView(R.id.one_number)
    TextView oneNumber;
    @BindView(R.id.four_number)
    TextView fourNumber;
    @BindView(R.id.one_score)
    TextView oneScore;
    @BindView(R.id.four_score)
    TextView fourScore;
    @BindView(R.id.my_err_question)
    Button myErrQuestion;
    private View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_personal_center, null);

        }
        ViewGroup group = (ViewGroup) rootView.getRootView();
        if (group != null) {
            group.removeView(rootView);
        }
        ButterKnife.bind(this, rootView);
        setUserName();//保存用户名到SharedPreferences
        init();
        return rootView;
    }

    public void setUserName() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("name", getContext().MODE_PRIVATE);
        String name = sharedPreferences.getString("name", "XXX");
        username.setText(name);
    }

    private void init() {
        oneNumber.setText("您一共做错" + SharedPreferencesManager.getOneError(getContext()) + "题");
        fourNumber.setText("您一共做错" + SharedPreferencesManager.getFourError(getContext()) + "题");
        oneScore.setText("您的成绩为：" + SharedPreferencesManager.getOneScore(getContext()) + "分");
        fourScore.setText("您的成绩为：" + SharedPreferencesManager.getFourScore(getContext()) + "分");
    }

    @OnClick(R.id.my_err_question)
    public void onClick() {
        startActivity(new Intent(getActivity(), MyErrQuestionActivity.class));
    }
}