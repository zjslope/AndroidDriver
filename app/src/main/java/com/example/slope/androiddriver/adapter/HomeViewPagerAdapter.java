package com.example.slope.androiddriver.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.slope.androiddriver.R;

/**
 * Created by Xuebin He on 2016/6/25.
 */
public class HomeViewPagerAdapter extends PagerAdapter {
    int[] ints;
    Context context;

    public HomeViewPagerAdapter(Context context,int[] ints) {
        this.context = context;
        this.ints=ints;
    }

    @Override
    public int getCount() {
        return ints.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView=new ImageView(context);
        imageView.setImageResource(ints[position]);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
