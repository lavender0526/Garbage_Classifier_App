package com.example.sa.Visitor;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sa.ConnectCan;
import com.example.sa.LoginActivity;
import com.example.sa.MainActivity;
import com.example.sa.NonRegistLocation;
import com.example.sa.NonRegistTrashcan;
import com.example.sa.R;
import com.example.sa.RedgistMoney;
import com.example.sa.RegistLocation;
import com.example.sa.RegistTrashcan;
import com.example.sa.registerReviseAccount;
import com.example.sa.store.UserStore;

public class GuestVisitor extends AppCompatActivity implements Visitor {

    Context context;
    public GuestVisitor(Context context){
        this.context = context;
    }
    @Override
    public void visit(RedgistMoneyMediary redgistMoneyMediary) {
        redgistMoneyMediary.gotoPage(context,LoginActivity.class,"Please login");
    }

    @Override
    public void visit(ConnectCanMediary connectCanMediary) {
        connectCanMediary.gotoPage(context,LoginActivity.class,"Please login");
    }

    @Override
    public void visit(RegistLocationMediary registLocationMediary) {
        registLocationMediary.gotoPage(context,NonRegistLocation.class,"success");
    }

    @Override
    public void visit(RegistTrashcanMediary registTrashcanMediary) {
        registTrashcanMediary.gotoPage(context,NonRegistTrashcan.class,"success");
    }

    @Override
    public void visit(RedgistrReviseAccountMediary redgistrReviseAccountMediary) {
        redgistrReviseAccountMediary.gotoPage(context, LoginActivity.class,"success");
    }

}
