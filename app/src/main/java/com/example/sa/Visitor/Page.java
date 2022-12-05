package com.example.sa.Visitor;

public interface Page {
    void gotoPage(Class<?> tClass);
    void accept(Visitor visotor);
}
