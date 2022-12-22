package com.example.sa.Bridge;

import android.graphics.Typeface;

public abstract class Font {
    protected ChangeFont changefont;

    protected Font(ChangeFont changefont){
        this.changefont = changefont;
    }

    abstract public Typeface change();
}
