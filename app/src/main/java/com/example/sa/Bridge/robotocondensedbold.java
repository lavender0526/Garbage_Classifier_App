package com.example.sa.Bridge;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.widget.TextView;

import androidx.core.content.res.ResourcesCompat;

import com.example.sa.R;

public class robotocondensedbold implements ChangeFont{
    @Override
    public Typeface method(Context context) {
        Typeface typeface;
        typeface = ResourcesCompat.getFont(context, R.font.robotocondensedbold);
        return typeface;

    }
}

