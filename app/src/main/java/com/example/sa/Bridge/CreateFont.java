package com.example.sa.Bridge;


import android.content.Context;
import android.graphics.Typeface;
import android.widget.TextView;

public class CreateFont extends Font{
    Context context;
    public CreateFont(Context context,ChangeFont changeFont){
        super(changeFont);
        this.context = context;
    }

    @Override
    public Typeface change() {
        return changefont.method(context);
    }
}
