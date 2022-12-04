package com.example.sa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Setting extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
    }

    public void gotoHome(View view) {
        Intent intent = new Intent(Setting.this,RegistTrashcan.class);
        startActivity(intent);
    }
    public void gotoAccount(View view) {
        Intent intent = new Intent(Setting.this,registerReviseAccount.class);
        startActivity(intent);
    }
    public void gotoLocation(View view) {
        Intent intent = new Intent(Setting.this,RegistLocation.class);
        startActivity(intent);
    }
    public void gotoMoney(View view) {
        Intent intent = new Intent(Setting.this,RedgistMoney.class);
        startActivity(intent);
    }
    public void btnConnectCan(View view) {
        Intent intent = new Intent(Setting.this,ConnectCan.class);
        startActivity(intent);
    }
    public void btnContact(View view){
        Intent intent = new Intent(Setting.this,Contact.class);
        startActivity(intent);
    }
}