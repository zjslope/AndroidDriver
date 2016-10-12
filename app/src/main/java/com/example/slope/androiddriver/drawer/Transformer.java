package com.example.slope.androiddriver.drawer;

import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Slope on 2016/9/10.
 */
public interface Transformer {
    /**
     * 进行 transform 变化,对每个 SideBar 的 child view 进行样式变换
     *
     * @param sideBar     SideBar
     * @param itemView    child view
     * @param touchY      当前手指按下的 y 位置
     * @param slideOffset 当前 drawerLayout 的 slideOffset
     * @param isLeft      标识左右 SideBar
     */
    void apply(ViewGroup sideBar, View itemView, float touchY, float slideOffset, boolean isLeft);

}
