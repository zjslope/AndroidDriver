package com.example.slope.androiddriver;


import android.animation.ObjectAnimator;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.drawable.DrawerArrowDrawable;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.slope.androiddriver.drawer.SideBar;
import com.example.slope.androiddriver.drawer.SimpleFantasyListener;
import com.example.slope.androiddriver.drawer.Transformer;
import com.example.slope.androiddriver.fragment.NewsFragment;
import com.example.slope.androiddriver.shared.SharedPreferencesManager;

import org.xutils.DbManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.sidebar_uer_name)
    TextView sidebarUerName;
    private DrawerLayout drawerLayout;
    private NewsFragment newsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initFragment();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final DrawerArrowDrawable indicator = new DrawerArrowDrawable(this);
        //设置标题栏图标的颜色
        indicator.setColor(Color.WHITE);
        getSupportActionBar().setHomeAsUpIndicator(indicator);

        setTransformer();
        setListener();
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        drawerLayout.setScrimColor(Color.TRANSPARENT);
        drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                if (((ViewGroup) drawerView).getChildAt(1).getId() == R.id.leftSideBar) {
                    indicator.setProgress(slideOffset);
                }
            }
        });
        setUserName();//保存用户名到SharedPreferences
    }
    public void setUserName(){
        SharedPreferences sharedPreferences=getSharedPreferences("name",MODE_PRIVATE);
        String name=sharedPreferences.getString("name","XXX");
        sidebarUerName.setText(name);
    }
    private void initFragment() {
            newsFragment = new NewsFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.mian_news_fram, newsFragment)
                    .commit();
    }

    //右上角根据侧边栏的选择加载相应的选项
    private void setListener() {
        SideBar leftSideBar = (SideBar) findViewById(R.id.leftSideBar);
        leftSideBar.setFantasyListener(new SimpleFantasyListener() {
            @Override
            public boolean onHover(@Nullable View view) {
                return false;

            }

            @Override
            public boolean onSelect(View view) {

                return false;
            }

            @Override
            public void onCancel() {

            }
        });
    }

    private void setTransformer() {
        final float spacing = getResources().getDimensionPixelSize(R.dimen.spacing);
        SideBar rightSideBar = (SideBar) findViewById(R.id.rightSideBar);
        rightSideBar.setTransformer(new Transformer() {
            private View lastHoverView;

            @Override
            public void apply(ViewGroup sideBar, View itemView, float touchY, float slideOffset, boolean isLeft) {
                boolean hovered = itemView.isPressed();
                if (hovered && lastHoverView != itemView) {
                    animateIn(itemView);
                    animateOut(lastHoverView);
                    lastHoverView = itemView;
                }
            }

            private void animateOut(View view) {
                if (view == null) {
                    return;
                }
                ObjectAnimator translationX = ObjectAnimator.ofFloat(view, "translationX", -spacing, 0);
                translationX.setDuration(200);
                translationX.start();
            }

            private void animateIn(View view) {
                ObjectAnimator translationX = ObjectAnimator.ofFloat(view, "translationX", 0, -spacing);
                translationX.setDuration(200);
                translationX.start();
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.closeDrawer(GravityCompat.START);
            } else {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        }
        return true;
    }

    public void onClick(View view) {
        if (view instanceof TextView) {
            String title = ((TextView) view).getText().toString().trim();
            if (title.equals("注销")){
                new AlertDialog.Builder(MainActivity.this)
                        .setMessage("真的要注销?")
                        .setPositiveButton("是", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                SharedPreferencesManager.setLogin(MainActivity.this,false);
                                startActivity(new Intent(MainActivity.this,LoginActivity.class));
                                Toast.makeText(MainActivity.this, "注销成功", Toast.LENGTH_SHORT).show();

                                finish();
                            }
                        })
                        .setNegativeButton("否", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).show();
            }
            else if (title.startsWith("456")) {
                Toast.makeText(this, title, Toast.LENGTH_SHORT).show();
            } else {
                startActivity(UniversalActivity.newIntent(this, title));
            }
        } else if (view.getId() == R.id.userInfo) {
            startActivity(UniversalActivity.newIntent(this, "个人中心"));
        }
    }


    @OnClick(R.id.sidebar_uer_name)
    public void onClick() {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        getSupportFragmentManager().beginTransaction().remove(newsFragment).commit();
    }
}
