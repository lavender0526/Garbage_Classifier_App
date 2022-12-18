package com.example.sa.Bridge;


import android.widget.TextView;

public class CreateFont extends Font{
    TextView a,b;

    public CreateFont(ChangeFont changeFont, TextView a,TextView b){

        super(changeFont);
        this.a = a;
        this.b = b;
    }

    @Override
    public void change() {
        changefont.method(a,b);
    }
}
