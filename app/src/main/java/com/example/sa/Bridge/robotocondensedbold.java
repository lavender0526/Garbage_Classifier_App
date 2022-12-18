package com.example.sa.Bridge;

import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.widget.TextView;

public class robotocondensedbold implements ChangeFont{

    @Override
    public void method(TextView a,TextView b) {
        Typeface tf = Typeface.createFromAsset(a.getContext().getAssets(), "font/robotocondensedbold.ttf");
        a.setTypeface(tf);
        Typeface tf2 = Typeface.createFromAsset(b.getContext().getAssets(), "font/robotocondensedbold.ttf");
        b.setTypeface(tf2);
    }
}
