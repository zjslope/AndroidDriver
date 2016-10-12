package com.example.slope.androiddriver;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.example.slope.androiddriver.http.MyURL;

/**
 * Created by Slope on 2016/9/11.
 */
public class RegisteredActivity extends AppCompatActivity {
    @BindView(R.id.register_name)
    EditText registerName;
    @BindView(R.id.register_layout_name)
    TextInputLayout registerLayoutName;
    @BindView(R.id.register_phone)
    EditText registerPhone;
    @BindView(R.id.register_layout_phone)
    TextInputLayout registerLayoutPhone;
    @BindView(R.id.register_password)
    EditText registerPassword;
    @BindView(R.id.register_layout_password)
    TextInputLayout registerLayoutPassword;
    @BindView(R.id.btn_register)
    Button btnRegister;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registered_activity);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_register)
    public void onClick(final View view) {
        hideKeyboard();//隐藏键盘

        RequestParams params = new RequestParams(MyURL.MY_SERVWE_REGISTER);
        params.addQueryStringParameter("phone", registerPhone.getText().toString().trim());
        params.addQueryStringParameter("uname", registerName.getText().toString().trim());
        params.addQueryStringParameter("regpass", registerPassword.getText().toString().trim());
        if (registerPhone.length() == 0) {
            Snackbar.make(view, "错误", Snackbar.LENGTH_INDEFINITE)
                    .setAction("请填写手机号", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(RegisteredActivity.this, "请填写手机号", Toast.LENGTH_SHORT).show();
                        }
                    }).show();
            registerPhone.requestFocus();      // 控件获取焦点
            return;                     // 结束函数的执行
        }
        if (registerName.length() == 0) {
            Snackbar.make(view, "错误", Snackbar.LENGTH_INDEFINITE)
                    .setAction("请填写用户名", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(RegisteredActivity.this, "请填写用户名", Toast.LENGTH_SHORT).show();
                        }
                    }).show();
            registerName.requestFocus();      // 控件获取焦点
            return;                     // 结束函数的执行
        }
        if (registerPassword.length() == 0) {
            Snackbar.make(view, "错误", Snackbar.LENGTH_INDEFINITE)
                    .setAction("请填写密码", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(RegisteredActivity.this, "请填写密码", Toast.LENGTH_SHORT).show();
                        }
                    }).show();
            registerPassword.requestFocus();      // 控件获取焦点
            return;                     // 结束函数的执行
        }
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject rootObj = new JSONObject(result);
                    if ("0".equals(rootObj.getString("result"))) {
                        startActivity(new Intent(RegisteredActivity.this, LoginActivity.class));
                        finish();
                    } else if ("1".equals(rootObj.getString("result"))) {
                        Snackbar.make(view, "错误", Snackbar.LENGTH_INDEFINITE)
                                .setAction("该手机已近注册过", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Toast.makeText(RegisteredActivity.this, "该手机已近注册过", Toast.LENGTH_SHORT).show();
                                    }
                                }).show();
                        registerPhone.requestFocus();      // 控件获取焦点
                        return;                     // 结束函数的执行
                    } else if ("3".equals(rootObj.getString("result"))) {
                        Snackbar.make(view, "错误", Snackbar.LENGTH_INDEFINITE)
                                .setAction("该用户名已经存在", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Toast.makeText(RegisteredActivity.this, "该用户名已经存在", Toast.LENGTH_SHORT).show();
                                    }
                                }).show();
                        registerName.requestFocus();      // 控件获取焦点
                        return;                     // 结束函数的执行
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    /**
     * 隐藏键盘
     */
    private void hideKeyboard() {
        View view = getCurrentFocus();
        if (view != null) {
            ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).
                    hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

}
