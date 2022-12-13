package com.example.sa.Visitor;

import android.content.Context;

public interface PageMiddleware {
    void gotoPage(Context context, Class<?> tClass,String text);
    void accept(AuthVisitor visotor);
}
