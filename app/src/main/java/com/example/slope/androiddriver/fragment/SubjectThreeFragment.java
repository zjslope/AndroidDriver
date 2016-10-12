package com.example.slope.androiddriver.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.slope.androiddriver.R;
import com.example.slope.androiddriver.WebActivity;
import com.example.slope.androiddriver.adapter.SubjectThreeAdapter;
import com.example.slope.androiddriver.widget.AdvanceDecoration;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.slope.androiddriver.http.MyURL;

/**
 * Created by Slope on 2016/9/10.
 */
public class SubjectThreeFragment extends Fragment {
    @BindView(R.id.subject_three_rv)
    RecyclerView subjectThreeRv;
    private SubjectThreeAdapter adapter;
    private String[] title;
    private int[] image;
    private RecyclerView.LayoutManager layoutManager;
    private View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_subject_three, null);

        }
        ViewGroup group = (ViewGroup) rootView.getRootView();
        if (group != null) {
            group.removeView(rootView);
        }
        ButterKnife.bind(this, rootView);
        init();
        return rootView;
    }

    String[] url;

    private void init() {
        layoutManager = new LinearLayoutManager(getContext());
        ((LinearLayoutManager) layoutManager).setOrientation(OrientationHelper.VERTICAL);
        subjectThreeRv.setLayoutManager(layoutManager);
        subjectThreeRv.addItemDecoration(new AdvanceDecoration(getContext(), OrientationHelper.VERTICAL));
        subjectThreeRv.setHasFixedSize(true); //适配器内容改变，不会改变RecyclerView的大小
        url = new String[]{
                MyURL.SUBJECT_THREE_COMPLETE,
                MyURL.SUBJECT_THREE_Nighttime_scene_lighting_use,
                MyURL.SUBJECT_THREE_READY,
                MyURL.SUBJECT_THREE_STARTED,
                MyURL.SUBJECT_THREE_Straight_running,
                MyURL.SUBJECT_THREE_Modified_file_operations,
                MyURL.SUBJECT_THREE_Change_lanes,
                MyURL.SUBJECT_THREE_Through_the_intersection,
                MyURL.SUBJECT_THREE_pull_over,
                MyURL.SUBJECT_THREE_Left_turn,
                MyURL.SUBJECT_THREE_Intersection_turn_right,
                MyURL.SUBJECT_THREE_By_crosswalk,
                MyURL.SUBJECT_THREE_By_school_districts,
                MyURL.SUBJECT_THREE_By_bus_stop,
                MyURL.SUBJECT_THREE_The_car_will,
                MyURL.SUBJECT_THREE_overtake,
                MyURL.SUBJECT_THREE_U_turn,
                MyURL.SUBJECT_THREE_Night_driving
        };

        title = new String[]{
                "科三完成版", "模拟夜间场景灯光使用", "上车准备",
                "起步", "直线行驶", "加减档操作",
                "变更车道", "通过路口", "靠边停车",
                "路口左转弯", "路口右转弯", "通过人行横道",
                "通过学校区域", "通过公共汽车站", "会车",
                "超车", "掉头", "夜间行驶"
        };
        image = new int[]{
                R.mipmap.car_three, R.mipmap.car_y, R.mipmap.car_sc,
                R.mipmap.car_qb, R.mipmap.car_zx, R.mipmap.car_jj,
                R.mipmap.car_bg, R.mipmap.car_tg, R.mipmap.car_kb,
                R.mipmap.car_lk, R.mipmap.car_lky, R.mipmap.car_rxh,
                R.mipmap.car_xxq, R.mipmap.car_ggq, R.mipmap.car_hc,
                R.mipmap.car_cc, R.mipmap.car_dt, R.mipmap.car_yj
        };
        adapter = new SubjectThreeAdapter(getContext(), title, image);
        adapter.setOnItemClickListener(new SubjectThreeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent i = new Intent(getActivity(), WebActivity.class);
                i.putExtra("title", title[position]);
                i.putExtra("url", url[position]);
                startActivity(i);
//
            }
        });
        subjectThreeRv.setAdapter(adapter);
    }


}
