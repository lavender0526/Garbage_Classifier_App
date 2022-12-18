package com.example.sa.Bridge;

import android.graphics.Typeface;
import android.widget.TextView;

public class robotocondensedregular implements ChangeFont{
    @Override
    public void method(TextView a, TextView b) {
        Typeface tf = Typeface.createFromAsset(a.getContext().getAssets(), "font/robotocondensedregular.ttf");
        a.setTypeface(tf);
        Typeface tf2 = Typeface.createFromAsset(b.getContext().getAssets(), "font/robotocondensedregular.ttf");
        b.setTypeface(tf2);
    }
}
