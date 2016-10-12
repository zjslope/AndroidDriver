package com.example.slope.androiddriver;

import android.animation.Animator;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.slope.androiddriver.basicclass.Car;
import com.example.slope.androiddriver.database.CarDataBase;
import com.example.slope.androiddriver.database.ServicesDataBase;

import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.Utils;
import me.imid.swipebacklayout.lib.app.SwipeBackActivityBase;
import me.imid.swipebacklayout.lib.app.SwipeBackActivityHelper;

import static android.R.attr.y;
import static com.example.slope.androiddriver.R.id.down_up;

/**
 * Created by Slope on 2016/9/19.
 */
public class QuestionActivity extends AppCompatActivity implements SwipeBackActivityBase {
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
    @BindView(R.id.question_layout)
    LinearLayout questionLayout;
    @BindView(R.id.explains)
    TextView explains;
    @BindView(R.id.answer)
    TextView answer;
    @BindView(down_up)
    TextView downUp;
    @BindView(R.id.down_collect)
    TextView downCollect;
    @BindView(R.id.down_answer)
    TextView downAnswer;
    @BindView(R.id.down_down)
    TextView downDown;
    @BindView(R.id.show_answer)
    LinearLayout showAnswer;
    private int id = 1;
    private SwipeBackActivityHelper swipeBackActivityHelper;
    int globalAnswer;
    int error=0;
    int idRand;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question);
        ButterKnife.bind(this);
        getSelectSubject();

        swipeBackActivityHelper = new SwipeBackActivityHelper(this);
        swipeBackActivityHelper.onActivityCreate();
    }

    private void initOneOrder() {
        question.setText(getOneCar(id).getQuestion());
        questionBtFirst.setText(getOneCar(id).getItem1());
        questionBtTwo.setText(getOneCar(id).getItem2());
        questionBtThree.setText(getOneCar(id).getItem3());
        questionBtFore.setText(getOneCar(id).getItem4());
        explains.setText(getOneCar(id).getExplains());
        answer.setText(getOneCar(id).getAnswer());
        globalAnswer = Integer.valueOf(getOneCar(id).getAnswer());
        ImageOptions options = new ImageOptions.Builder()
                .setFadeIn(true)
                .build();
        x.image().bind(urlImage, getOneCar(id).getUrl(), options);
    }

    private void initOneRandom() {
        idRand = new Random().nextInt(1000);
        question.setText(getOneCar(idRand).getQuestion());
        questionBtFirst.setText(getOneCar(idRand).getItem1());
        questionBtTwo.setText(getOneCar(idRand).getItem2());
        questionBtThree.setText(getOneCar(idRand).getItem3());
        questionBtFore.setText(getOneCar(idRand).getItem4());
        explains.setText(getOneCar(idRand).getExplains());
        answer.setText(getOneCar(idRand).getAnswer());
        globalAnswer = Integer.valueOf(getOneCar(idRand).getAnswer());
        ImageOptions options = new ImageOptions.Builder()
                .setFadeIn(true)
                .build();
        x.image().bind(urlImage, getOneCar(idRand).getUrl(), options);
    }

    private void initFour() {
        question.setText(getFourCar(id).getQuestion());
        questionBtFirst.setText(getFourCar(id).getItem1());
        questionBtTwo.setText(getFourCar(id).getItem2());
        questionBtThree.setText(getFourCar(id).getItem3());
        questionBtFore.setText(getFourCar(id).getItem4());
        explains.setText(getFourCar(id).getExplains());
        answer.setText(getFourCar(id).getAnswer());
        globalAnswer=Integer.valueOf(getFourCar(id).getAnswer());
        ImageOptions options = new ImageOptions.Builder()
                .setFadeIn(true)
                .build();
        x.image().bind(urlImage, getFourCar(id).getUrl(), options);
    }

    private void initFourRandom() {
        idRand = new Random().nextInt(1000);
        question.setText(getFourCar(idRand).getQuestion());
        questionBtFirst.setText(getFourCar(idRand).getItem1());
        questionBtTwo.setText(getFourCar(idRand).getItem2());
        questionBtThree.setText(getFourCar(idRand).getItem3());
        questionBtFore.setText(getFourCar(idRand).getItem4());
        explains.setText(getFourCar(idRand).getExplains());
        answer.setText(getFourCar(idRand).getAnswer());
        globalAnswer=Integer.valueOf(getFourCar(idRand).getAnswer());
        ImageOptions options = new ImageOptions.Builder()
                .setFadeIn(true)
                .build();
        x.image().bind(urlImage, getFourCar(idRand).getUrl(), options);
    }

    @OnClick({R.id.question_bt_first, R.id.question_bt_two, R.id.question_bt_three, R.id.question_bt_fore, R.id.down_up, R.id.down_collect, R.id.down_answer, R.id.down_down})
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.question_bt_first:
                if (1 == globalAnswer) {
                  //  Snackbar.make(answer,"success1",Snackbar.LENGTH_SHORT).show();
                    nextQuestion();
                } else {
                    getError();
                    error++;
                    showAnswer.setVisibility(View.VISIBLE);
                }

                break;
            case R.id.question_bt_two:
                if (2 == globalAnswer) {
                    // Snackbar.make(answer,"success2",Snackbar.LENGTH_SHORT).show();
                    //下一题
                    nextQuestion();
                } else {
                    getError();
                    error++;
                    showAnswer.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.question_bt_three:
                if (3 == globalAnswer) {
                    nextQuestion();
                } else {
                    getError();
                    error++;
                    showAnswer.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.question_bt_fore:
                if (4 == globalAnswer) {
                    nextQuestion();
                } else {
                    getError();
                    error++;
                    showAnswer.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.down_up:
                onQuestion();
                break;
            case R.id.down_collect:
               getCollect();
                break;
            case R.id.down_answer:
                showAnswer.setVisibility(View.VISIBLE);
                break;
            case R.id.down_down:
                nextQuestion();
                break;
        }
    }

    public Car  getOneCar(int id) {
        Cursor cursor = new ServicesDataBase(this).selectCarOne(1, id);
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

    public Car getFourCar(int id) {
        Cursor cursor = new ServicesDataBase(this).selectCarOne(4, id);
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
        Utils.convertActivityToTranslucent(this);
        getSwipeBackLayout().scrollToFinishActivity();
    }

    public void getSelectSubject() {
        if (getIntent().getStringExtra("one").equals("one")) {
            initOneOrder();
        } else if (getIntent().getStringExtra("one").equals("Orandom")) {
            initOneRandom();
        } else if (getIntent().getStringExtra("one").equals("four")) {
            initFour();
        } else if (getIntent().getStringExtra("one").equals("Frandom")) {
            initFourRandom();
        }
        else if (getIntent().getStringExtra("one").endsWith("error")){
            initOneOrder();
        }
    }

    public void nextQuestion() {

        if (getIntent().getStringExtra("one").equals("one")) {
            id++;
            initOneOrder();
            showAnswer.setVisibility(View.GONE);
            questionRg.clearCheck();
        } else if (getIntent().getStringExtra("one").equals("Orandom")) {
            id++;
            initOneRandom();
            showAnswer.setVisibility(View.GONE);
            questionRg.clearCheck();
        } else if (getIntent().getStringExtra("one").equals("four")) {
            id++;
            initFour();
            showAnswer.setVisibility(View.GONE);
            questionRg.clearCheck();
        } else if (getIntent().getStringExtra("one").equals("Frandom")) {
            id++;
            initFourRandom();
            showAnswer.setVisibility(View.GONE);
            questionRg.clearCheck();
        }else if (getIntent().getStringExtra("one").equals("error")){
            id++;
            initOneOrder();
            showAnswer.setVisibility(View.GONE);
            questionRg.clearCheck();
        }
    }
    public void onQuestion(){
        if (id == 1) {
            Toast.makeText(QuestionActivity.this, "已经是第一题了", Toast.LENGTH_SHORT).show();
        } else {
            if (getIntent().getStringExtra("one").equals("one")) {
                id--;
                showAnswer.setVisibility(View.GONE);
                questionRg.clearCheck();
                initOneOrder();
            } else if (getIntent().getStringExtra("one").equals("Orandom")) {
                id--;
                showAnswer.setVisibility(View.GONE);
                questionRg.clearCheck();
                initOneRandom();
            } else if (getIntent().getStringExtra("one").equals("four")) {
                id--;
                showAnswer.setVisibility(View.GONE);
                questionRg.clearCheck();
                initFour();
            } else if (getIntent().getStringExtra("one").equals("Frandom")) {
                id--;
                showAnswer.setVisibility(View.GONE);
                questionRg.clearCheck();
                initFourRandom();
            }
            else if (getIntent().getStringExtra("one").equals("error")){
                id--;
                showAnswer.setVisibility(View.GONE);
                questionRg.clearCheck();
                initOneOrder();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
       if (getIntent().getStringExtra("one").equals("one")){

            SharedPreferences.Editor editor1=getSharedPreferences("oneerror",MODE_PRIVATE).edit();
            editor1.putString("oneerror", String.valueOf(error));
            editor1.commit();
       }
        else if (getIntent().getStringExtra("one").equals("four")){

            SharedPreferences.Editor editor4=getSharedPreferences("fourerror",MODE_PRIVATE).edit();
            editor4.putString("fourerror", String.valueOf(error));
            editor4.commit();
       }



    }
    public void getError(){
        if (getIntent().getStringExtra("one").equals("one")){
            List<Car> list=new ArrayList<Car>();
            list.add(getOneCar(id));
            new ServicesDataBase(this).addAllSubject(list, CarDataBase.TABLE_MYERROR);
        } else if (getIntent().getStringExtra("one").equals("Orandom")) {
            List<Car> list=new ArrayList<Car>();
            list.add(getOneCar(idRand));
            new ServicesDataBase(this).addAllSubject(list, CarDataBase.TABLE_MYERROR);
        }else if (getIntent().getStringExtra("one").equals("four")) {
            List<Car> list=new ArrayList<Car>();
            list.add(getFourCar(id));
            new ServicesDataBase(this).addAllSubject(list, CarDataBase.TABLE_MYERROR);
        } else if (getIntent().getStringExtra("one").equals("Frandom")) {
            List<Car> list=new ArrayList<Car>();
            list.add(getFourCar(idRand));
            new ServicesDataBase(this).addAllSubject(list, CarDataBase.TABLE_MYERROR);
        }
        else {
            Toast.makeText(QuestionActivity.this, "没有错题", Toast.LENGTH_SHORT).show();
        }
    }
    public void getCollect(){
        if (getIntent().getStringExtra("one").equals("one")){
            List<Car> list=new ArrayList<Car>();
            list.add(getOneCar(id));
            new ServicesDataBase(this).addAllSubject(list, CarDataBase.TABLE_COLLECT_QUESTION);
        } else if (getIntent().getStringExtra("one").equals("Orandom")) {
            List<Car> list=new ArrayList<Car>();
            list.add(getOneCar(idRand));
            new ServicesDataBase(this).addAllSubject(list, CarDataBase.TABLE_COLLECT_QUESTION);
        }else if (getIntent().getStringExtra("one").equals("four")) {
            List<Car> list=new ArrayList<Car>();
            list.add(getFourCar(id));
            new ServicesDataBase(this).addAllSubject(list, CarDataBase.TABLE_COLLECT_QUESTION);
        } else if (getIntent().getStringExtra("one").equals("Frandom")) {
            List<Car> list=new ArrayList<Car>();
            list.add(getFourCar(idRand));
            new ServicesDataBase(this).addAllSubject(list, CarDataBase.TABLE_COLLECT_QUESTION);
        }
    }

}
