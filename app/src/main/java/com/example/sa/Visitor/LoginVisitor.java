//package com.example.sa.Visitor;
//
//import android.app.Activity;
//import android.content.Context;
//import android.content.Intent;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.example.sa.ConnectCan;
//import com.example.sa.Home;
//import com.example.sa.Money;
//import com.example.sa.NonRegistLocation;
//import com.example.sa.NonRegistTrashcan;
//import com.example.sa.RedgistMoney;
//import com.example.sa.RegistLocation;
//import com.example.sa.RegistTrashcan;
//import com.example.sa.RegisterActivity;
//
//public class LoginVisitor extends AppCompatActivity implements Visotor{
//    @Override
//    public boolean visit(RedgistMoney redgistMoney) {
//        Intent intent = new Intent(LoginVisitor.this, RedgistMoney.class);
//        startActivity(intent);
//    }
//
//    @Override
//    public boolean visit(ConnectCan ConnectCan) {
//        Intent intent = new Intent(LoginVisitor.this, ConnectCan.class);
//        startActivity(intent);
//    }
//
//    @Override
//    public boolean visit(NonRegistLocation nonRegistLocation) {
//        Intent intent = new Intent(LoginVisitor.this, RegistLocation.class);
//        startActivity(intent);
//    }
//
//    @Override
//    public boolean visit(NonRegistTrashcan nonRegistTrashcan) {
//        Intent intent = new Intent(LoginVisitor.this, RegistTrashcan.class);
//        startActivity(intent);
//    }
//
//    @Override
//    public boolean visit(RegistLocation registLocation) {
//        Intent intent = new Intent(LoginVisitor.this, RegistLocation.class);
//        startActivity(intent);
//    }
//
//    @Override
//    public boolean visit(RegistTrashcan registTrashcan) {
//        Intent intent = new Intent(LoginVisitor.this, RegistTrashcan.class);
//        startActivity(intent);
//    }
//}
