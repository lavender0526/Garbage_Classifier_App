package com.example.sa.Visitor;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sa.ConnectCan;
import com.example.sa.Home;
import com.example.sa.Money;
import com.example.sa.NonRegistLocation;
import com.example.sa.NonRegistTrashcan;
import com.example.sa.RedgistMoney;
import com.example.sa.RegistLocation;
import com.example.sa.RegistTrashcan;
import com.example.sa.RegisterActivity;

public class LoginVisitor extends AppCompatActivity implements Visotor{
    @Override
    public void visit(RedgistMoney redgistMoney,Page p) {
        Intent intent = new Intent(p, RedgistMoney.class);
        startActivity(intent);
    }

    @Override
    public void visit(ConnectCan ConnectCan,Page p) {
        Intent intent = new Intent(p, ConnectCan.class);
        startActivity(intent);
    }

    @Override
    public void visit(NonRegistLocation nonRegistLocation,Page p) {
        Intent intent = new Intent(p, RegistLocation.class);
        startActivity(intent);
    }

    @Override
    public void visit(NonRegistTrashcan nonRegistTrashcan,Page p) {
        Intent intent = new Intent(p, RegistTrashcan.class);
        startActivity(intent);
    }

    @Override
    public void visit(RegistLocation registLocation,Page p) {
        Intent intent = new Intent(p, RegistLocation.class);
        startActivity(intent);
    }

    @Override
    public void visit(RegistTrashcan registTrashcan,Page p) {
        Intent intent = new Intent(p, RegistTrashcan.class);
        startActivity(intent);
    }
}
