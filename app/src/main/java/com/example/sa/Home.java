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
        System.out.printf("go NonRegistLocation");
        Switch aswitch = new Switch("NonRegistLocation",this);
        aswitch.activity();
//        if(isAccept){
//            Intent intent = new Intent(Home.this, RegistLocation.class);
//            startActivity(intent);
//        }
//        else{
//            Intent intent = new Intent(Home.this, NonRegistLocation.class);
//            startActivity(intent);
//        }
    }

    public void btnMainToTrashcan(View view) {
        System.out.printf("go NonRegistTrashcan");
        Switch aswitch = new Switch("NonRegistTrashcan",this);
        aswitch.activity();
//        if(isAccept){
//            Intent intent = new Intent(Home.this, RegistTrashcan.class);
//            startActivity(intent);
//        }
//        else{
//            Intent intent = new Intent(Home.this, NonRegistTrashcan.class);
//            startActivity(intent);
//        }
    }
}
