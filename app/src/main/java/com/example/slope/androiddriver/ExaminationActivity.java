package com.example.slope.androiddriver;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.slope.androiddriver.basicclass.Car;
import com.example.slope.androiddriver.database.CarDataBase;
import com.example.slope.androiddriver.database.ServicesDataBase;
import com.example.slope.androiddriver.http.DataResult;
import com.example.slope.androiddriver.http.HttpResultResponseRand;
import com.example.slope.androiddriver.http.MyURL;

import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.Utils;
import me.imid.swipebacklayout.lib.app.SwipeBackActivityBase;
import me.imid.swipebacklayout.lib.app.SwipeBackActivityHelper;

public class ExaminationActivity extends AppCompatActivity implements SwipeBackActivityBase, DataResult {

    @BindView(R.id.car_type)
    TextView carType;
    @BindView(R.id.start_exam)
    Button startExam;
    @BindView(R.id.username)
    TextView username;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    @BindView(R.id.show_download)
    LinearLayout showDownload;
    private SwipeBackActivityHelper swipeBackActivityHelper;
    String model;
    String type;
    int i = 0;
    String subject;
    //    String subject;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_examination);
        ButterKnife.bind(this);
        swipeBackActivityHelper = new SwipeBackActivityHelper(this);
        swipeBackActivityHelper.onActivityCreate();
        setUserName();//保存用户名到SharedPreferences
        setSelectCar();
    }

    public void setUserName() {
        SharedPreferences sharedPreferences = getSharedPreferences("name", MODE_PRIVATE);
        String name = sharedPreferences.getString("name", "XXX");
        username.setText(name);
    }

    public void setSelectCar() {
        SharedPreferences sharedPreferences = getSharedPreferences("car", MODE_PRIVATE);
        type = sharedPreferences.getString("car", "XXX");
        carType.setText(type);
    }

    @OnClick({R.id.car_type, R.id.start_exam})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.car_type:
                break;
            case R.id.start_exam:

                if ("小车".equals(type)) {
                    new ServicesDataBase(this).deleteOne(CarDataBase.TABLE_RAND);
                    model = MyURL.MODEAL_C1;

                } else if ("货车".equals(type)) {
                    new ServicesDataBase(this).deleteOne(CarDataBase.TABLE_RAND);

                    model = MyURL.MODEAL_A2;
                } else if ("客车".equals(type)) {

                    new ServicesDataBase(this).deleteOne(CarDataBase.TABLE_RAND);
                    model = MyURL.MODEAL_B1;
                }
                RequestParams params = new RequestParams(MyURL.URL);
                params.addQueryStringParameter("subject", MyURL.SUBJECT1);
                params.addQueryStringParameter("key", MyURL.MYKEY);
                params.addQueryStringParameter("model", model);
                params.addQueryStringParameter("testType", MyURL.testType_rand);
                x.http().get(params, new HttpResultResponseRand(this, getApplicationContext(), 8));
                //Snackbar.make(startExam, "请耐心等待，考题马上呈现...", Snackbar.LENGTH_SHORT).show();
                //加载进度条
                showDownload.setVisibility(View.VISIBLE);
                handler.post(updateThread);
        }
    }
    Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            progressBar.setProgress(msg.arg1);
            handler.post(updateThread);
        }
    };
    Runnable updateThread = new Runnable() {
        @Override
        public void run() {
            // TODO Auto-generated method stub
//            System.out.println("startProgressBar/n");
            Message msg = handler.obtainMessage();
            msg.arg1 = i;
            i = i + 50;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            handler.sendMessage(msg);
            if (i == 100) {

                Message message = new Message();
                message.what = 1;
                handlerStop.sendMessage(message);
                handler.removeCallbacks(updateThread);
                showDownload.setVisibility(View.GONE);
                Snackbar.make(showDownload,"下载失败！", Snackbar.LENGTH_SHORT).show();
            }
            handler.postDelayed(updateThread, 1000);
        }

    };
    final Handler handlerStop = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    i = 0;
                    handler.removeCallbacks(updateThread);
                    break;
            }
            super.handleMessage(msg);
        }
    };

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

    @Override
    public void resultObject(String objects) {

    }

    @Override
    public void resultList(final List<Car> list, final int type) {

        switch (type) {
            case 8:
                if (-1 != new ServicesDataBase(this).addSubjectRand(list, CarDataBase.TABLE_RAND)) {
                   // startActivity(new Intent(this, StartExamActivity.class));
                    if (getIntent().getStringExtra("one").equals("Oscore")){
                        subject="Osubject";
                        Intent intent=new Intent(this,StartExamActivity.class);
                        intent.putExtra("one",subject);
                        startActivity(intent);
                    }else if (getIntent().getStringExtra("one").equals("Fscore")){
                        subject="Fsubject";
                        Intent intent=new Intent(this,StartExamActivity.class);
                        intent.putExtra("one",subject);
                        startActivity(intent);
                    }

                }
                break;
        }


    }
}
