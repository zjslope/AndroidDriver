package com.example.slope.androiddriver.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.slope.androiddriver.LoginActivity;
import com.example.slope.androiddriver.R;

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
 * Created by Slope on 2016/9/10.
 */
public class ChangePwdFragment extends Fragment {


    @BindView(R.id.old_pwd)
    EditText oldPwd;
    @BindView(R.id.login_layout_old_pwd)
    TextInputLayout loginLayoutOldPwd;
    @BindView(R.id.login_new_password)
    EditText loginNewPassword;
    @BindView(R.id.login_layout_new_password)
    TextInputLayout loginLayoutNewPassword;
    @BindView(R.id.login_again_pwd)
    EditText loginAgainPwd;
    @BindView(R.id.login_layout_again_pwd)
    TextInputLayout loginLayoutAgainPwd;
    @BindView(R.id.btn_ok)
    Button btnOk;
    private View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_chang_pwd, null);
            init();
        }
        ViewGroup group = (ViewGroup) rootView.getRootView();
        if (group != null) {
            group.removeView(rootView);
        }
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    private void init() {

    }



    @OnClick(R.id.btn_ok)
    public void onClick(final View view) {
        hideKeyboard();

        if(oldPwd.getText().toString().length()==0||
                loginNewPassword.getText().toString().length() ==0||
                loginAgainPwd.getText().toString().length()==0){
            Snackbar.make(view,"错误",Snackbar.LENGTH_INDEFINITE)
                    .setAction("请输入密码", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(getActivity(), "请输入密码", Toast.LENGTH_SHORT).show();
                        }
                    }).show();
            return;                     // 结束函数的执行
       }
        if (loginNewPassword.getText().toString().equals(loginAgainPwd.getText().toString())) {
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("name", getContext().MODE_PRIVATE);
            String name = sharedPreferences.getString("name", "XXX");
            RequestParams params = new RequestParams(MyURL.MY_SERVWE_RESETPASS);
            params.addQueryStringParameter("uname", name);
            params.addQueryStringParameter("upass", oldPwd.getText().toString().trim());
            params.addQueryStringParameter("newpwd", loginNewPassword.getText().toString().trim());
            x.http().get(params, new Callback.CommonCallback<String>() {
                @Override
                public void onSuccess(String result) {
                    try {
                        JSONObject rootobj = new JSONObject(result);
                        if ("0".equals(rootobj.getString("update"))) {
                            Snackbar.make(view, "成功", Snackbar.LENGTH_INDEFINITE)
                                    .setAction("修改密码成功，请重新登陆", new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            Toast.makeText(getActivity(), "修改密码成功，请重新登陆", Toast.LENGTH_SHORT).show();
                                        }
                                    }).show();
                            startActivity(new Intent(getActivity(), LoginActivity.class));
                            getActivity().finish();
                        }  if ("1".equals(rootobj.getString("update"))) {
                            Snackbar.make(view, "错误", Snackbar.LENGTH_INDEFINITE)
                                    .setAction("原始密码错误，请重新输入", new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            Toast.makeText(getActivity(), "原始密码错误，请重新输入", Toast.LENGTH_SHORT).show();
                                        }
                                    }).show();
                            oldPwd.requestFocus();
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
        else {
//        }if (loginNewPassword.getText().toString().trim()!=(loginAgainPwd.getText().toString().trim())){
            Snackbar.make(view,"错误",Snackbar.LENGTH_INDEFINITE)
                    .setAction("两次密码不一致,请重新输入", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(getActivity(), "两次密码不一致,请重新输入", Toast.LENGTH_SHORT).show();
                        }
                    }).show();
            loginNewPassword.requestFocus();
            return;                     // 结束函数的执行
        }


    }
    /**
     * 隐藏键盘
     */
    private void hideKeyboard() {
        View view = getActivity().getCurrentFocus();
        if (view != null) {
            ((InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE)).
                    hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}
