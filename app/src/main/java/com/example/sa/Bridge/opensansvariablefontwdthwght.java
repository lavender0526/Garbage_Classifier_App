package com.example.sa.Bridge;

import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.widget.TextView;


public class opensansvariablefontwdthwght implements ChangeFont{

    @Override
    public void method(TextView a,TextView b) {
        Typeface tf = Typeface.createFromAsset(a.getContext().getAssets(), "font/opensansvariablefontwdthwght.ttf");
        a.setTypeface(tf);
        Typeface tf2 = Typeface.createFromAsset(b.getContext().getAssets(), "font/opensansvariablefontwdthwght.ttf");
        b.setTypeface(tf2);

    }
}

