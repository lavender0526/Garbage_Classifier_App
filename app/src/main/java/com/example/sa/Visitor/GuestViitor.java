package com.example.sa.Visitor;


import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sa.ConnectCan;
import com.example.sa.LoginActivity;
import com.example.sa.NonRegistLocation;
import com.example.sa.NonRegistTrashcan;
import com.example.sa.RedgistMoney;
import com.example.sa.RegistLocation;
import com.example.sa.RegistTrashcan;

public class GuestViitor extends AppCompatActivity implements Visotor {
    @Override
    public void visit(RedgistMoney redgistMoney,Page p) {
        Intent intent = new Intent(p, LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public void visit(ConnectCan ConnectCan,Page p) {
        Intent intent = new Intent(p, LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public void visit(NonRegistLocation nonRegistLocation,Page p) {
        Intent intent = new Intent(p, NonRegistLocation.class);
        startActivity(intent);
    }

    @Override
    public void visit(NonRegistTrashcan nonRegistTrashcan,Page p) {
        Intent intent = new Intent(p, NonRegistTrashcan.class);
        startActivity(intent);
    }
    @Override
    public void visit(RegistLocation registLocation,Page p) {
        Intent intent = new Intent(p, LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public void visit(RegistTrashcan registTrashcan,Page p) {
        Intent intent = new Intent(p, LoginActivity.class);
        startActivity(intent);
    }
}
