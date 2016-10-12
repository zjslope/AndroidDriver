package com.example.slope.androiddriver.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.slope.androiddriver.R;

/**
 * Created by Slope on 2016/9/10.
 */
public class LogoutFragment extends Fragment {
    private View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(rootView==null){
            rootView=inflater.inflate(R.layout.fragment_logout,null);
            init();
        }
        ViewGroup group= (ViewGroup) rootView.getRootView();
        if(group!=null){
            group.removeView(rootView);
        }
        return  rootView;
    }
    private void init(){

    }
    public boolean gotoBack(){
        return false;
    }
}
