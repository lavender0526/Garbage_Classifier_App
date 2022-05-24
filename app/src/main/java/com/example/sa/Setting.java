package com.example.sa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Setting extends AppCompatActivity {
    private String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        Intent intent2 = getIntent();
        username = intent2.getStringExtra("userLoginName");

    }

    public void btnsettingHome(View view) {
        Intent intent = new Intent(Setting.this,MainActivity.class);
        intent.putExtra("userLoginName",username);
        startActivity(intent);
    }
    public void btnsettingAccount(View view) {
        Intent intent = new Intent(Setting.this,registerReviseAccount.class);
        intent.putExtra("userLoginName",username);
        startActivity(intent);
    }
    public void btnsettingLocation(View view) {
        Intent intent = new Intent(Setting.this,RegistLocation.class);
        intent.putExtra("userLoginName",username);
        startActivity(intent);
    }
    public void btnsettingMoney(View view) {
        Intent intent = new Intent(Setting.this,Money.class);
        intent.putExtra("userLoginName",username);
        startActivity(intent);
    }
}