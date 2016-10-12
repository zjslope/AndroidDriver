package com.example.slope.androiddriver;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.slope.androiddriver.basicclass.Car;
import com.example.slope.androiddriver.database.ServicesDataBase;
import com.example.slope.androiddriver.shared.SharedPreferencesManager;
import com.example.slope.androiddriver.utils.Couterdown;

import org.xutils.image.ImageOptions;
import org.xutils.x;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StartExamActivity extends AppCompatActivity {
    @BindView(R.id.show_time)
    TextView showTime;
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
    @BindView(R.id.down_up)
    TextView downUp;
    @BindView(R.id.down_submit)
    TextView downSubmit;
    @BindView(R.id.down_schedule)
    TextView downSchedule;
    @BindView(R.id.down_down)
    TextView downDown;
    int id = 1;
    int globalAnswer;
    @BindView(R.id.answer)
    TextView answer;
    int content = 0;
    int num = 0;
    int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_exam);
        ButterKnife.bind(this);
        //getScore();
        initShowTime();
        initOneRand();
        downSchedule.setText(id + "/100");
    }

    private void initOneRand() {
        question.setText(getOneRand(id).getQuestion());
        questionBtFirst.setText(getOneRand(id).getItem1());
        questionBtTwo.setText(getOneRand(id).getItem2());
        questionBtThree.setText(getOneRand(id).getItem3());
        questionBtFore.setText(getOneRand(id).getItem4());
        answer.setText(getOneRand(id).getAnswer());
        globalAnswer = Integer.valueOf(getOneRand(id).getAnswer());
        ImageOptions options = new ImageOptions.Builder()
                .setFadeIn(true)
                .build();
        x.image().bind(urlImage, getOneRand(id).getUrl(), options);
    }

    private void initShowTime() {
        new Couterdown(2700000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                showTime.setText("倒计时：" + toClock(millisUntilFinished));
            }

            @Override
            public String toClock(long millis) {
                return super.toClock(millis);
            }
            @Override
            public void onFinish() {
               initScore();
                finish();
             // Toast.makeText(StartExamActivity.this, "考试时间结束", Toast.LENGTH_SHORT).show();
            }
        }.start();

    }

    @OnClick({R.id.question_bt_first, R.id.question_bt_two, R.id.question_bt_three, R.id.question_bt_fore, R.id.down_up, R.id.down_submit, R.id.down_schedule, R.id.down_down})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.question_bt_first:
                num++;
                if (1 == globalAnswer) {
                    nextQuestion();
                    //Toast.makeText(this, "答对了", Toast.LENGTH_SHORT).show();
                } else {
                    content++;
                    nextQuestion();
                    answer.setVisibility(View.GONE);
                }
                break;
            case R.id.question_bt_two:
                num++;
                if (2 == globalAnswer) {
                    nextQuestion();
                  //  Toast.makeText(this, "答对了", Toast.LENGTH_SHORT).show();
                } else {
                    content++;
                    nextQuestion();
                    answer.setVisibility(View.GONE);
                }
                break;
            case R.id.question_bt_three:
                num++;
                if (3 == globalAnswer) {
                    nextQuestion();
                  //  Toast.makeText(this, "答对了", Toast.LENGTH_SHORT).show();
                } else {
                    content++;
                    nextQuestion();
                    answer.setVisibility(View.GONE);
                }
                break;
            case R.id.question_bt_fore:
                 num++;
                if (4 == globalAnswer) {
                    nextQuestion();
                   // Toast.makeText(this, "答对了", Toast.LENGTH_SHORT).show();
                } else {
                    content++;
                    nextQuestion();
                    answer.setVisibility(View.GONE);
                }
                break;
            case R.id.down_up:
                onQuestion();
                break;
            case R.id.down_submit:

                score=num-content;
//                SharedPreferences.Editor editor=getSharedPreferences("score",MODE_PRIVATE).edit();
//                editor.putString("score", String.valueOf(score));
//                editor.commit();
                receiveScore(score);
                break;
            case R.id.down_schedule:
                break;
            case R.id.down_down:
                nextQuestion();
                break;
        }
    }

    public Car getOneRand(int id) {
        Cursor cursor = new ServicesDataBase(this).selectCarOne(8, id);
        Car car = null;
        if (cursor.moveToNext()) {
            car = new Car(Integer.valueOf(cursor.getString(0)), cursor.getString(1), cursor.getString(2),
                    cursor.getString(3), cursor.getString(4), cursor.getString(5),
                    cursor.getString(6), cursor.getString(7), cursor.getString(8));

        } else {
            Toast.makeText(this, "没有更多。。。。", Toast.LENGTH_SHORT).show();
        }
        return car;
    }


    private void nextQuestion() {
        id++;
        if(id==100){
            score=num-content;
//            SharedPreferences.Editor editor=getSharedPreferences("score",MODE_PRIVATE).edit();
//            editor.putString("score", String.valueOf(score));
//            editor.commit();
            receiveScore(score);
            finish();
        }
        else {
            downSchedule.setText(id + "/100");
            questionRg.clearCheck();
            initOneRand();
            answer.setVisibility(View.GONE);
            score=num-content;
        }
    }

    private void onQuestion() {
        if (id == 1) {
            Toast.makeText(StartExamActivity.this, "已经是第一题了", Toast.LENGTH_SHORT).show();
        } else {
            id--;
            downSchedule.setText(id + "/100");
            initOneRand();
            answer.setVisibility(View.GONE);
        }
    }
    private void initScore(){

        score=num-content;
//        SharedPreferences.Editor editor=getSharedPreferences("score",MODE_PRIVATE).edit();
//        editor.putString("score", String.valueOf(score));
//        editor.commit();
        receiveScore(score);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();


    }

    @Override
    public void onBackPressed() {
       new AlertDialog.Builder(this)
               .setTitle("正在考试中...")
               .setMessage("是否退出当前考试")
               .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {
                       Toast.makeText(StartExamActivity.this, "请您继续作答", Toast.LENGTH_SHORT).show();
                   }
               })
               .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {
                       score=num-content;
//                       SharedPreferences.Editor editor=getSharedPreferences("score",MODE_PRIVATE).edit();
//                       editor.putString("score", String.valueOf(score));
//                       editor.commit();
                       receiveScore(score);
                       finish();
                   }
               })
               .show();

    }
    private void receiveScore(int score){
        if (getIntent().getStringExtra("one").equals("Osubject")){
            SharedPreferences.Editor editor1=getSharedPreferences("onescore",MODE_PRIVATE).edit();
            editor1.putString("onescore", String.valueOf(score));
            editor1.commit();
        }
        else if (getIntent().getStringExtra("one").equals("Fsubject")){
            SharedPreferences.Editor editor1=getSharedPreferences("fourscore",MODE_PRIVATE).edit();
            editor1.putString("fourscore", String.valueOf(score));
            editor1.commit();
        }
    }
}

