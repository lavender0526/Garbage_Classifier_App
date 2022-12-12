package com.example.sa.Visitor;

import android.content.Context;

public interface Page {
    void gotoPage(Context context, Class<?> tClass,String text);
    void accept(AuthVisitor visotor);
}
