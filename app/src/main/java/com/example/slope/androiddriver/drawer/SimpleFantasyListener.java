package com.example.slope.androiddriver.drawer;

import android.support.annotation.Nullable;
import android.view.View;

/**
 * Created by Slope on 2016/9/10.
 */
public class SimpleFantasyListener implements FantasyListener {
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
}
