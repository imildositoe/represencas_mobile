package com.example.represencas_mobile.all.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

public class ViewPagerSwipe extends ViewPager {

    private boolean isEnabled;

    public ViewPagerSwipe(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (this.isEnabled) {
            return super.onTouchEvent(ev);
        }
        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (this.isEnabled) {
            return super.onInterceptTouchEvent(ev);
        }
        return false;
    }

    public void setSwippingEnabled(boolean enabled) {
        this.isEnabled = enabled;
    }
}
