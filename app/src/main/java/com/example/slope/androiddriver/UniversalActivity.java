package com.example.slope.androiddriver;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.example.slope.androiddriver.fragment.AboutMeFragment;
import com.example.slope.androiddriver.fragment.CarRingFragment;
import com.example.slope.androiddriver.fragment.ChangePwdFragment;
import com.example.slope.androiddriver.fragment.LogoutFragment;
import com.example.slope.androiddriver.fragment.MyExamFragment;
import com.example.slope.androiddriver.fragment.NewsCollectFragment;
import com.example.slope.androiddriver.fragment.NewsFragment;
import com.example.slope.androiddriver.fragment.PersonalCenterFragment;
import com.example.slope.androiddriver.fragment.ProblemCollectFragment;
import com.example.slope.androiddriver.fragment.SetUpFragment;
import com.example.slope.androiddriver.fragment.SubjectFourFragment;
import com.example.slope.androiddriver.fragment.SubjectOneFragment;
import com.example.slope.androiddriver.fragment.SubjectThreeFragment;
import com.example.slope.androiddriver.fragment.SubjectTwoFragment;

import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivityBase;
import me.imid.swipebacklayout.lib.app.SwipeBackActivityHelper;

/**
 * Created by Slope on 2016/9/10.
 */
public class UniversalActivity extends AppCompatActivity implements SwipeBackActivityBase {
    private SwipeBackActivityHelper swipeBackActivityHelper;
    private NewsFragment newsFragment;
    private SubjectOneFragment subjectOneFragment;
    private SubjectTwoFragment subjectTwoFragment;
    private SubjectThreeFragment subjectThreeFragment;
    private SubjectFourFragment subjectFourFragment;
    private ProblemCollectFragment problemCollectFragment;
    private NewsCollectFragment newsCollectFragment;
    private CarRingFragment carRingFragment;
    private PersonalCenterFragment personalCenterFragment;
    private MyExamFragment myExamFragment;
    private ChangePwdFragment changePwdFragment;
    private LogoutFragment logoutFragment;
    private AboutMeFragment aboutMeFragment;
    private SetUpFragment setUpFragment;
    public static Intent newIntent(Context context, String title) {
        Intent intent = new Intent(context, UniversalActivity.class);
        intent.putExtra("title", title);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_universal);

        swipeBackActivityHelper = new SwipeBackActivityHelper(this);
        swipeBackActivityHelper.onActivityCreate();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        init();
        setTitle(getIntent().getStringExtra("title"));

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    private void init() {
        subjectFourFragment = new SubjectFourFragment();
        problemCollectFragment = new ProblemCollectFragment();
        newsCollectFragment = new NewsCollectFragment();
        carRingFragment = new CarRingFragment();
        newsFragment = new NewsFragment();
        personalCenterFragment = new PersonalCenterFragment();
        myExamFragment = new MyExamFragment();
        changePwdFragment = new ChangePwdFragment();
        logoutFragment = new LogoutFragment();
        aboutMeFragment = new AboutMeFragment();
        setUpFragment = new SetUpFragment();
        subjectOneFragment = new SubjectOneFragment();
        subjectTwoFragment = new SubjectTwoFragment();
        subjectThreeFragment = new SubjectThreeFragment();


        if (setTitle(getIntent().getStringExtra("title").equalsIgnoreCase("我的题库"))) {

            getSupportFragmentManager().beginTransaction()

                    .replace(R.id.framelayout, myExamFragment)
                    .show(myExamFragment)
                    .commit();
        } else if (setTitle(getIntent().getStringExtra("title").equalsIgnoreCase("修改密码"))) {
            getSupportFragmentManager().beginTransaction()

                    .replace(R.id.framelayout, changePwdFragment)

                    .show(changePwdFragment)
                    .commit();
        } else if (setTitle(getIntent().getStringExtra("title").equalsIgnoreCase("个人中心"))) {
            getSupportFragmentManager().beginTransaction()

                    .replace(R.id.framelayout, personalCenterFragment)

                    .show(personalCenterFragment)
                    .commit();
        } else if (setTitle(getIntent().getStringExtra("title").equalsIgnoreCase("注册"))) {
            getSupportFragmentManager().beginTransaction()

                    .replace(R.id.framelayout, logoutFragment)

                    .show(logoutFragment)
                    .commit();

        } else if (setTitle(getIntent().getStringExtra("title").equalsIgnoreCase("关于我"))) {
            getSupportFragmentManager().beginTransaction()

                    .replace(R.id.framelayout, aboutMeFragment)

                    .show(aboutMeFragment)
                    .commit();

        } else if (setTitle(getIntent().getStringExtra("title").equalsIgnoreCase("设置"))) {
            getSupportFragmentManager().beginTransaction()

                    .replace(R.id.framelayout, setUpFragment)

                    .show(setUpFragment)
                    .commit();

        } else if (setTitle(getIntent().getStringExtra("title").equalsIgnoreCase("科目一"))) {
            getSupportFragmentManager().beginTransaction()

                    .replace(R.id.framelayout, subjectOneFragment)

                    .show(subjectOneFragment)
                    .commit();

        } else if (setTitle(getIntent().getStringExtra("title").equalsIgnoreCase("科目二"))) {
            getSupportFragmentManager().beginTransaction()

                    .replace(R.id.framelayout, subjectTwoFragment)

                    .show(subjectTwoFragment)
                    .commit();

        } else if (setTitle(getIntent().getStringExtra("title").equalsIgnoreCase("科目三"))) {
            getSupportFragmentManager().beginTransaction()

                    .replace(R.id.framelayout, subjectThreeFragment)

                    .show(subjectThreeFragment)
                    .commit();

        } else if (setTitle(getIntent().getStringExtra("title").equalsIgnoreCase("科目四"))) {
            getSupportFragmentManager().beginTransaction()

                    .replace(R.id.framelayout, subjectFourFragment)

                    .show(subjectFourFragment)
                    .commit();

        } else if (setTitle(getIntent().getStringExtra("title").equalsIgnoreCase("习题收藏"))) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.framelayout, problemCollectFragment)
                    .show(problemCollectFragment)
                    .commit();

        } else if (setTitle(getIntent().getStringExtra("title").equalsIgnoreCase("新闻收藏"))) {
            getSupportFragmentManager().beginTransaction()

                    .replace(R.id.framelayout, newsCollectFragment)

                    .show(newsCollectFragment)
                    .commit();

        } else if (setTitle(getIntent().getStringExtra("title").equalsIgnoreCase("车友圈"))) {
            getSupportFragmentManager().beginTransaction()

                    .replace(R.id.framelayout, carRingFragment)

                    .show(carRingFragment)
                    .commit();

        }


    }

    private boolean setTitle(boolean equals) {
        return equals;
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

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        if (getSupportFragmentManager().getFragments().get(0) instanceof AboutMeFragment) {
            if (!((AboutMeFragment) getSupportFragmentManager().getFragments().get(0)).gotoBack()) {
                super.onBackPressed();
            }
        }
//       else if (getSupportFragmentManager().getFragments().get(0) instanceof SubjectTwoFragment){
//            if(!((SubjectTwoFragment) getSupportFragmentManager().getFragments().get(0)).gotoBack()){
//                super.onBackPressed();
//            }
//        }
        else {
            super.onBackPressed();
        }
    }

}

