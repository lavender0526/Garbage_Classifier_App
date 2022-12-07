package com.example.sa.Iterator;

public interface Aggregate {
    Iterator createIterator();
    void add(String item);
}
