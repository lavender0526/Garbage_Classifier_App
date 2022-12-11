package com.example.sa.Bridge;


public interface ChangeFont {
    Typeface typeface = getResources().getFont(R.font.myfont);
    textView.setTypeface(typeface);
abstract public void method();
}
