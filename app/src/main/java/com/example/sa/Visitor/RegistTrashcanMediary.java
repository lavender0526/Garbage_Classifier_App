package com.example.sa.Visitor;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

public class RegistTrashcanMediary extends AppCompatActivity implements Page{

    @Override
    public void gotoPage(Class<?> tClass) {
        Intent intent = new Intent(getApplication(), tClass);
        startActivity(intent);
    }
    @Override
    public void accept(Visitor visoitor) {
        visoitor.visit(this);
    }
}
