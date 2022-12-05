package com.example.sa.Visitor;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sa.RedgistMoney;
import com.example.sa.registerReviseAccount;

public class RedgistMoneyMediary extends AppCompatActivity implements Page {
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
