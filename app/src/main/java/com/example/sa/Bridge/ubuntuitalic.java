package com.example.sa.Bridge;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.TextView;

import androidx.core.content.res.ResourcesCompat;

import com.example.sa.R;

public class ubuntuitalic implements ChangeFont{
    @Override
    public Typeface method(Context context) {
        Typeface typeface;
        typeface = ResourcesCompat.getFont(context, R.font.ubuntuitalic);
        return typeface;

    }
}

