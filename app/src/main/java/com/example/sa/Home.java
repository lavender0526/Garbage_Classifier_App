package com.example.sa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.sa.Visitor.Page;
import com.example.sa.Visitor.Switch;

public class Home extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void btnMainToRegist(View view) {
        Intent intent = new Intent(Home.this,RegisterActivity.class);
        startActivity(intent);
    }

    public void btnMainToLogin(View view) {
        Intent intent = new Intent(Home.this,LoginActivity.class);
        startActivity(intent);
    }

    public void btnMainToLocation(View view) {
        Switch aswitch = new Switch("NonRegistLocation",this);
        aswitch.activity();
    }

    public void btnMainToTrashcan(View view) {
        Switch aswitch = new Switch("NonRegistTrashcan",this);
        aswitch.activity();
    }
}
