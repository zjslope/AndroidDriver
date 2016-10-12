package com.example.slope.androiddriver.drawer;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;

import com.example.slope.androiddriver.R;

/**
 * Created by Slope on 2016/9/10.
 */
public class CircleImageView extends de.hdodenhof.circleimageview.CircleImageView {
    public CircleImageView(Context context) {
        super(context);
    }

    public CircleImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CircleImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    /*
    根据选中的状态来给选中项设置颜色
     */
    @Override
    public void setPressed(boolean pressed) {
        super.setPressed(pressed);
        if (pressed) {
            setBorderColor(getResources().getColor(R.color.colorAccent));
        } else {
            setBorderColor(Color.WHITE);
        }
    }
}
