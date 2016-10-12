package com.example.slope.androiddriver;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

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
import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivityBase;
import me.imid.swipebacklayout.lib.app.SwipeBackActivityHelper;

public class MyErrQuestionActivity extends AppCompatActivity implements SwipeBackActivityBase {
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
    int globalAnswer;
    Car car;
    int id = 0;
    int listSize = 0;
    List<Car> list = new ArrayList<Car>();
    @BindView(R.id.root_error_rl)
    RelativeLayout rootErrorRl;
    private SwipeBackActivityHelper swipeBackActivityHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_err_question);
        ButterKnife.bind(this);
        initError();
        swipeBackActivityHelper = new SwipeBackActivityHelper(this);
        swipeBackActivityHelper.onActivityCreate();
    }

    public void showError(Car car) {
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

    private void initError() {
        Cursor c = new ServicesDataBase(this).selectAll(CarDataBase.TABLE_MYERROR);
        while (c.moveToNext()) {
            car = new Car(Integer.valueOf(c.getString(0)), c.getString(1), c.getString(2), c.getString(3),
                    c.getString(4), c.getString(5), c.getString(6), c.getString(7),
                    c.getString(8));
            list.add(car);
        }
        if (0 != (listSize = list.size())) {
            showError(list.get(id));
            rootErrorRl.setVisibility(View.VISIBLE);
        } else {
            // Toast.makeText(MyErrQuestionActivity.this, "您没有错题", Toast.LENGTH_SHORT).show();
            new AlertDialog.Builder(this)
                    .setTitle("没有错题")
                    .setMessage("您还没有做题呢，请先做题")
                    .setPositiveButton("现在去做题", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //让该activity跳转到fragment
                            String err = "one";
                            Intent i = new Intent(MyErrQuestionActivity.this, QuestionActivity.class);
                            i.putExtra("one", err);
                            startActivity(i);
                            finish();
                        }
                    })
                    .setNegativeButton("我在逛逛", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            startActivity(new Intent(MyErrQuestionActivity.this, MainActivity.class));
                        }
                    })
                    .show();
        }

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
                // upQuestion();
                break;
            case R.id.down_down:
                nextQuestion();
                break;
        }
    }

    private void getdelete() {
        new ServicesDataBase(this).deleteErrorQuestion(list.get(id));
    }

    private void nextQuestion() {
        if (id + 1 >= listSize) {
            Toast.makeText(MyErrQuestionActivity.this, "我也是有底限的！", Toast.LENGTH_SHORT).show();

        } else {
            showError(list.get(++id));
        }
    }

    private void upQuestion() {
        if (id - 1 < 0) {
            Toast.makeText(MyErrQuestionActivity.this, "已经是第一题了！", Toast.LENGTH_SHORT).show();
        } else {
            showError(list.get(--id));
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return true;
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        swipeBackActivityHelper.onPostCreate();
    }

    @Override
    public View findViewById(int id) {
        View v = super.findViewById(id);
        if (v == null && swipeBackActivityHelper != null)
            return swipeBackActivityHelper.findViewById(id);
        return v;
    }

    @Override
    public SwipeBackLayout getSwipeBackLayout() {
        return swipeBackActivityHelper.getSwipeBackLayout();
    }

    @Override
    public void setSwipeBackEnable(boolean enable) {
        getSwipeBackLayout().setEnableGesture(enable);
    }

    @Override
    public void scrollToFinishActivity() {
//        Utils.convertActivityToTranslucent(this);
        me.imid.swipebacklayout.lib.Utils.convertActivityToTranslucent(this);
        getSwipeBackLayout().scrollToFinishActivity();
    }
}
