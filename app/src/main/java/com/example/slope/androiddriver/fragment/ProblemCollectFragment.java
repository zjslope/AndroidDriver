package com.example.slope.androiddriver.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.slope.androiddriver.MainActivity;
import com.example.slope.androiddriver.R;
import com.example.slope.androiddriver.basicclass.Car;
import com.example.slope.androiddriver.database.CarDataBase;
import com.example.slope.androiddriver.database.ServicesDataBase;

import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Slope on 2016/9/10.
 */
public class ProblemCollectFragment extends Fragment {

    @BindView(R.id.question)
    TextView question;
    @BindView(R.id.url_image)
    ImageView urlImage;
    @BindView(R.id.question_bt_first)
    RadioButton questionBtFirst;
    @BindView(R.id.question_bt_two)
    RadioButton questionBtTwo;
    @BindView(R.id.question_bt_three)
    RadioButton questionBtThree;
    @BindView(R.id.question_bt_fore)
    RadioButton questionBtFore;
    @BindView(R.id.question_rg)
    RadioGroup questionRg;
    @BindView(R.id.explains)
    TextView explains;
    @BindView(R.id.answer)
    TextView answer;
    @BindView(R.id.show_answer)
    LinearLayout showAnswer;
    @BindView(R.id.down_up)
    TextView downUp;
    @BindView(R.id.down_delete_collect)
    TextView downDeleteCollect;
    @BindView(R.id.down_down)
    TextView downDown;
    @BindView(R.id.root_error_rl)
    RelativeLayout rootErrorRl;
    private View rootView;
    int globalAnswer;
    Car car;
    int id = 0;
    int listSize = 0;
    List<Car> list = new ArrayList<Car>();
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
            rootView = inflater.inflate(R.layout.activity_my_err_question, null);

        }
        ViewGroup group = (ViewGroup) rootView.getRootView();
        if (group != null) {
            group.removeView(rootView);
        }
        ButterKnife.bind(this, rootView);
        initCollect();
        return rootView;
    }


    private void initCollect() {
        Cursor c = new ServicesDataBase(getContext()).selectAll(CarDataBase.TABLE_COLLECT_QUESTION);
        while (c.moveToNext()) {
            car = new Car(Integer.valueOf(c.getString(0)), c.getString(1), c.getString(2), c.getString(3),
                    c.getString(4), c.getString(5), c.getString(6), c.getString(7),
                    c.getString(8));
            list.add(car);
        }
        if (0 != (listSize = list.size())) {
            showCollect(list.get(id));
            rootErrorRl.setVisibility(View.VISIBLE);
        } else {
            new AlertDialog.Builder(getContext())
                    .setTitle("未收藏")
                    .setMessage("您还没有收藏习题")
                    .setPositiveButton("现在去收藏", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            SubjectOneFragment fragment = new SubjectOneFragment();
                            ft = manager.beginTransaction();
                            //当前的fragment会被myJDEditFragment替换
                            ft.replace(R.id.framelayout, fragment);
                            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                            ft.addToBackStack(null);
                            ft.commit();
                        }
                    })
                    .setNegativeButton("我在逛逛", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            startActivity(new Intent(getActivity(), MainActivity.class));
                        }
                    })
                    .show();

        }

    }

    public void showCollect(Car car) {
        question.setText(car.getQuestion());
        questionBtFirst.setText(car.getItem1());
        questionBtTwo.setText(car.getItem2());
        questionBtThree.setText(car.getItem3());
        questionBtFore.setText(car.getItem4());
        explains.setText(car.getExplains());
        answer.setText(car.getAnswer());
        globalAnswer = Integer.valueOf(car.getAnswer());
        ImageOptions options = new ImageOptions.Builder()
                .setFadeIn(true)
                .build();
        x.image().bind(urlImage, car.getUrl(), options);
    }

    @OnClick({R.id.question_bt_first, R.id.question_bt_two, R.id.question_bt_three, R.id.question_bt_fore, R.id.down_up, R.id.down_delete_collect, R.id.down_down})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.question_bt_first:
                if (1 == globalAnswer) {
                    nextQuestion();
                } else {
                    showAnswer.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.question_bt_two:
                if (2 == globalAnswer) {
                    nextQuestion();
                } else {
                    showAnswer.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.question_bt_three:
                if (3 == globalAnswer) {
                    nextQuestion();
                } else {
                    showAnswer.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.question_bt_fore:
                if (4 == globalAnswer) {
                    nextQuestion();
                } else {
                    showAnswer.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.down_up:
                upQuestion();
                break;
            case R.id.down_delete_collect:
                getdelete();
                nextQuestion();
                break;
            case R.id.down_down:
                nextQuestion();
                break;
        }
    }

    private void getdelete() {
        new ServicesDataBase(getContext()).deleteCollectQuestion(list.get(id));
    }

    private void nextQuestion() {
        if (id + 1 >= listSize) {
            Toast.makeText(getActivity(), "我也是有底限的！", Toast.LENGTH_SHORT).show();

        } else {
            showCollect(list.get(++id));
        }
    }

    private void upQuestion() {
        if (id - 1 < 0) {
            Toast.makeText(getActivity(), "已经是第一题了！", Toast.LENGTH_SHORT).show();
        } else {
            showCollect(list.get(--id));
        }
    }
}