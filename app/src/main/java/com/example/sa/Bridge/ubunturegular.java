package com.example.sa.Bridge;

import android.graphics.Typeface;
import android.widget.TextView;

public class ubunturegular implements ChangeFont{
    @Override
    public void method(TextView a, TextView b) {
        Typeface tf = Typeface.createFromAsset(a.getContext().getAssets(), "font/ubunturegular.ttf");
        a.setTypeface(tf);
        Typeface tf2 = Typeface.createFromAsset(b.getContext().getAssets(), "font/ubunturegular.ttf");
        b.setTypeface(tf2);
    }
}
