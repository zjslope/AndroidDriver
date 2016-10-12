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
import com.example.slope.androiddriver.adapter.SubjectTwoAdapter;
import com.example.slope.androiddriver.widget.AdvanceDecoration;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.slope.androiddriver.http.MyURL;

/**
 * Created by Slope on 2016/9/10.
 */
public class SubjectTwoFragment extends Fragment {

    @BindView(R.id.subject_two_rv)
    RecyclerView subjectTwoRv;
    private SubjectTwoAdapter adapter;
    private String[] title;
    private int[] image;
    private RecyclerView.LayoutManager layoutManager;
    private View rootView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_subject_two, null);

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
        subjectTwoRv.setLayoutManager(layoutManager);
        subjectTwoRv.addItemDecoration(new AdvanceDecoration(getContext(), OrientationHelper.VERTICAL));
        subjectTwoRv.setHasFixedSize(true); //适配器内容改变，不会改变RecyclerView的大小
        url = new String[]{
                MyURL.SUBJECT_TWO_Back_into_the_garage,
                MyURL.SUBJECT_TWO_Ramps_and_designated_parking_started,
                MyURL.SUBJECT_TWO_Parallel_parking,
                MyURL.SUBJECT_TWO_Curve_with,
                MyURL.SUBJECT_TWO_Right_angle_turn,
                MyURL.SUBJECT_TWO_Narrow_road_U_turn,
                MyURL.SUBJECT_TWO_Simulation_of_Emergency_Management,
                MyURL.SUBJECT_TWO_Simulation_with_tunnel,
                MyURL.SUBJECT_TWO_Analog_take_highway_parking_card
        };

        title = new String[]{
                "倒车入库", "坡道定点停车和起步", "侧方停车",
                "曲线行驶", "直角转弯", "窄路掉头",
                "模拟紧急情况处理", "模拟隧道行驶", "模拟高速公路停车取卡"
        };
        image = new int[]{
                R.mipmap.car_d, R.mipmap.car_p,R.mipmap.car_c,
                R.mipmap.car_q, R.mipmap.car_z, R.mipmap.car_zl,
                R.mipmap.car_j, R.mipmap.car_s, R.mipmap.car_ck,
        };
        adapter = new SubjectTwoAdapter(getContext(), title, image);
        adapter.setOnItemClickListener(new SubjectTwoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent i = new Intent(getActivity(), WebActivity.class);
                i.putExtra("title", title[position]);
                i.putExtra("url", url[position]);
                startActivity(i);
//
            }
        });
        subjectTwoRv.setAdapter(adapter);
    }


}
