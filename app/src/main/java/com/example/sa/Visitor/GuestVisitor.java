package com.example.sa.Visitor;


import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sa.LoginActivity;
import com.example.sa.NonRegistLocation;
import com.example.sa.NonRegistTrashcan;

public class GuestVisitor extends AppCompatActivity implements AuthVisitor {

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
