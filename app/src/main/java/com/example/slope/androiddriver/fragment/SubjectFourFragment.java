package com.example.slope.androiddriver.fragment;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.slope.androiddriver.AboutCarActivity;
import com.example.slope.androiddriver.ExaminationActivity;
import com.example.slope.androiddriver.MyErrQuestionActivity;
import com.example.slope.androiddriver.QuestionActivity;
import com.example.slope.androiddriver.R;
import com.example.slope.androiddriver.StartExamActivity;
import com.example.slope.androiddriver.adapter.HomeViewPagerAdapter;
import com.example.slope.androiddriver.database.ServicesDataBase;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.relex.circleindicator.CircleIndicator;

/**
 * Created by Slope on 2016/9/10.
 */
public class SubjectFourFragment extends Fragment {

    ViewPager viewPager;
    CircleIndicator circleIndicator;
    @BindView(R.id.traffic)
    Button traffic;
    @BindView(R.id.legislation)
    Button legislation;
    @BindView(R.id.skill)
    Button skill;
    @BindView(R.id.subject1_woring)
    Button subject1Woring;
    @BindView(R.id.subject1_save)
    Button subject1Save;
    @BindView(R.id.subject1_order)
    Button subject1Order;
    @BindView(R.id.subject1_random)
    Button subject1Random;
    @BindView(R.id.examination)
    TextView examination;
    private View rootView;
    int[] ints = new int[]{R.mipmap.car1,
            R.mipmap.car2,
            R.mipmap.car3,
            R.mipmap.car4,
            R.mipmap.car5};
    private String type;
    FragmentTransaction ft;
    private FragmentManager manager;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        manager = getFragmentManager();
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_subject_four, null);

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
        viewPager = (ViewPager) rootView.findViewById(R.id.viewpager_adv);
        viewPager.setAdapter(new HomeViewPagerAdapter(getContext(), ints));
        circleIndicator = (CircleIndicator) rootView.findViewById(R.id.header_home_indictor);
        circleIndicator.setViewPager(viewPager);
    }


    @OnClick({R.id.traffic, R.id.legislation, R.id.skill, R.id.subject1_woring, R.id.subject1_save, R.id.subject1_order, R.id.subject1_random, R.id.examination})
    public void onClick(View view) {
        switch (view.getId()) {
            //交通标志
            case R.id.traffic:
                String url_traffic="https://www.youtube.com/watch?v=_QZB213Hp_0";
                Intent i = new Intent(getActivity(), AboutCarActivity.class);
                i.putExtra("title",traffic.getText());
                i.putExtra("url",url_traffic);
                startActivity(i);
                break;
            //法规
            case R.id.legislation:
                String url_legislation="https://www.youtube.com/watch?v=7ORK11sq7_k";
                Intent i1 = new Intent(getActivity(), AboutCarActivity.class);
                i1.putExtra("title",legislation.getText());
                i1.putExtra("url",url_legislation);
                startActivity(i1);
                break;
            //技巧
            case R.id.skill:
                String url_skill="https://www.youtube.com/watch?v=_V5S7V5ZQLM";
                Intent i2 = new Intent(getActivity(), AboutCarActivity.class);
                i2.putExtra("title",skill.getText());
                i2.putExtra("url",url_skill);
                startActivity(i2);
                break;
            case R.id.subject1_woring:
                startActivity(new Intent(getActivity(), MyErrQuestionActivity.class));
                break;
            case R.id.subject1_save:
                //跳转到习题收藏
                ProblemCollectFragment fragment = new ProblemCollectFragment();
                ft = manager.beginTransaction();
                //当前的fragment会被myJDEditFragment替换
                ft.replace(R.id.framelayout, fragment);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft.addToBackStack(null);
                ft.commit();
              // startActivity(new Intent(getActivity(),StartExamActivity.class));
                break;
            case R.id.subject1_order:
                type="four";
                Intent four=new Intent(getActivity(),QuestionActivity.class);
                four.putExtra("one",type);
                getActivity().startActivity(four);
                break;
            case R.id.subject1_random:
                type="Frandom";
                Intent four1=new Intent(getActivity(),QuestionActivity.class);
                four1.putExtra("one",type);
                getActivity().startActivity(four1);
                break;
            case R.id.examination:
                type = "Fscore";
                Intent intent = new Intent(getActivity(), ExaminationActivity.class);
                intent.putExtra("one", type);
                getActivity().startActivity(intent);
            // startActivity(new Intent(getActivity(), ExaminationActivity.class));
                break;
        }
    }
}
