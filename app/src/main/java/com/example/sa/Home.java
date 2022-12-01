package com.example.sa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.sa.Visitor.Page;
import com.example.sa.Visitor.Switch;

public class Home extends AppCompatActivity {

    private Page NonRegistLocation;
    private Page NonRegistTrashcan;

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
        System.out.printf("go NonRegistLocation");
        Switch s = new Switch(false,NonRegistLocation);
        s.activity();
    }

    public void btnMainToTrashcan(View view) {
        System.out.printf("go NonRegistTrashcan");
        Switch s = new Switch(false,NonRegistTrashcan);
        s.activity();
    }
}
