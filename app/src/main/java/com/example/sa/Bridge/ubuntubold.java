package com.example.sa.Bridge;

import android.graphics.Typeface;
import android.widget.TextView;

public class ubuntubold implements ChangeFont{
    @Override
    public void method(TextView a, TextView b) {
        Typeface tf = Typeface.createFromAsset(a.getContext().getAssets(), "font/ubuntubold.ttf");
        a.setTypeface(tf);
        Typeface tf2 = Typeface.createFromAsset(b.getContext().getAssets(), "font/ubuntubold.ttf");
        b.setTypeface(tf2);
    }
}
