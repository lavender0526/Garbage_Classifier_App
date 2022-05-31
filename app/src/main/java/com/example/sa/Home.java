package com.example.sa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

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
        Intent intent = new Intent(Home.this,NonRegistLocation.class);
        startActivity(intent);
    }

    public void btnMainToTrashcan(View view) {
        Intent intent = new Intent(Home.this,NonRegistTrashcan.class);
        startActivity(intent);
    }
}