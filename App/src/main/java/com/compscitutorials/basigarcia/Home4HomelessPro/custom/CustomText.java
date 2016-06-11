package com.compscitutorials.basigarcia.Home4HomelessPro.custom;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.TextView;

import com.compscitutorials.basigarcia.Home4HomelessPro.app.Contextor;

public class CustomText extends TextView {

    public CustomText(Context context) {
        super(context);
    }

    public CustomText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CustomText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void init() {
        if (!isInEditMode()) {
            Typeface tf = Typeface.createFromAsset(Contextor.getInstance().getContext().getAssets(), "fonts/rsu.ttf");
            setTypeface(tf);
        }
    }
}
