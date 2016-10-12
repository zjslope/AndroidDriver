package com.example.slope.androiddriver.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.slope.androiddriver.R;
import com.example.slope.androiddriver.basicclass.Car;
import com.example.slope.androiddriver.database.CarDataBase;
import com.example.slope.androiddriver.database.ServicesDataBase;
import com.example.slope.androiddriver.http.DataResult;
import com.example.slope.androiddriver.http.HttpResultResponse;
import com.example.slope.androiddriver.http.MyURL;

import org.xutils.http.RequestParams;
import org.xutils.x;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.slope.androiddriver.R.id.answer;

/**
 * Created by Slope on 2016/9/10.
 */
public class MyExamFragment extends Fragment implements DataResult {
    @BindView(R.id.update)
    TextView update;
    @BindView(R.id.left_count)
    TextView leftCount;
    @BindView(R.id.reght_count)
    TextView reghtCount;
    @BindView(R.id.sub_car)
    RadioButton subCar;
    @BindView(R.id.sub_truck)
    RadioButton subTruck;
    @BindView(R.id.sub_carriage)
    RadioButton subCarriage;
    @BindView(R.id.sub_btn)
    Button subBtn;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    @BindView(R.id.show_download)
    LinearLayout showDownload;

    private View rootView;
    String model;
    String select_car;
    int i = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_my_exam, null);

        }
        ViewGroup group = (ViewGroup) rootView.getRootView();
        if (group != null) {
            group.removeView(rootView);
        }
        ButterKnife.bind(this, rootView);
        init();
        subCar.setText("小车");
        subTruck.setText("货车");
        subCarriage.setText("客车");
        return rootView;
    }

    private void init() {
        update.setText("已更新至：" + new SimpleDateFormat("yyyy-MM-dd").format(new Date()));

    }

    public boolean gotoBack() {
        return false;
    }

    List<Car> list = new ArrayList<Car>();

    @OnClick({R.id.sub_car, R.id.sub_truck, R.id.sub_carriage, R.id.sub_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            //c1,c2
            case R.id.sub_car:
                new ServicesDataBase(getContext()).deleteOne(CarDataBase.TABLE_NAME_1);
                model = MyURL.MODEAL_C1;
                select_car = (String) subCar.getText();
                SharedPreferences.Editor editor = getActivity().getSharedPreferences("car", getContext().MODE_PRIVATE).edit();
                editor.putString("car", select_car);
                editor.commit();
                leftCount.setText("科一一共: 1299");
                reghtCount.setText("科四一共: 1096");
                break;
            //a2,b2
            case R.id.sub_truck:
                new ServicesDataBase(getContext()).deleteOne(CarDataBase.TABLE_NAME_1);
                //new ServicesDataBase(getContext()).deleteOne(CarDataBase.TABLE_NAME_4);
                select_car = (String) subTruck.getText();
                SharedPreferences.Editor editor1 = getActivity().getSharedPreferences("car", getContext().MODE_PRIVATE).edit();
                editor1.putString("car", select_car);
                editor1.commit();
                model = MyURL.MODEAL_A2;
                leftCount.setText("科一一共: 1299");
                reghtCount.setText("科四一共:1096 ");

                break;
            //a1,b1
            case R.id.sub_carriage:
                new ServicesDataBase(getContext()).deleteOne(CarDataBase.TABLE_NAME_1);
                select_car = (String) subCarriage.getText();
                SharedPreferences.Editor editor2 = getActivity().getSharedPreferences("car", getContext().MODE_PRIVATE).edit();
                editor2.putString("car", select_car);
                editor2.commit();
                model = MyURL.MODEAL_B1;
                leftCount.setText("科一一共: 1299");
                reghtCount.setText("科四一共: 1096");
                break;
            case R.id.sub_btn:

                RequestParams params1 = new RequestParams(MyURL.URL);
                params1.addQueryStringParameter("subject", MyURL.SUBJECT1);
                params1.addQueryStringParameter("key", MyURL.MYKEY);
                params1.addQueryStringParameter("model", model);
                params1.addQueryStringParameter("testType", MyURL.testType_order);
                x.http().get(params1, new HttpResultResponse(this, getContext(), 1));
                //加载进度条
                showDownload.setVisibility(View.VISIBLE);
                handler.post(updateThread);

                break;
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
            i = i + 2;
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
                Snackbar.make(showDownload,"下载成功！", Snackbar.LENGTH_SHORT).show();
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
    public void resultObject(String objects) {

    }

    @Override
    public void resultList(final List<Car> list, final int type) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                switch (type) {
                    case 1:
                        if (-1 != new ServicesDataBase(getContext()).addAllSubject(list, CarDataBase.TABLE_NAME_1))
                            if (!getActivity().getSharedPreferences("has4", Context.MODE_PRIVATE).getBoolean("has4", false)) {
                                RequestParams params4 = new RequestParams(MyURL.URL);
                                params4.addQueryStringParameter("subject", MyURL.SUBJECT4);
                                params4.addQueryStringParameter("key", MyURL.MYKEY);
                                params4.addQueryStringParameter("testType", MyURL.testType_order);
                                x.http().get(params4, new HttpResultResponse(MyExamFragment.this, getContext(), 4));
                                getActivity().getSharedPreferences("has4", Context.MODE_PRIVATE).edit().putBoolean("has4", true).commit();
                                Log.e("success+++++++++", "run:_________________________________________ ");

                            }
                        break;
                    case 4:
                        if (-1 != new ServicesDataBase(getContext()).addAllSubject(list, CarDataBase.TABLE_NAME_4))
                            Log.e("success-------------", "run:_________________________________________ ");
                        break;
                    case 8:
                        if (-1 != new ServicesDataBase(getContext()).addAllSubject(list, CarDataBase.TABLE_RAND))

                            break;
                }

            }
        }).start();


    }
}
