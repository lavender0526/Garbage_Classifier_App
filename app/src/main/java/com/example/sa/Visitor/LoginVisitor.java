package com.example.sa.Visitor;


import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sa.RedgistMoney;
import com.example.sa.RegistLocation;
import com.example.sa.RegistTrashcan;
import com.example.sa.Setting;
import com.example.sa.registerReviseAccount;

public class LoginVisitor extends AppCompatActivity implements AuthVisitor {
    Context context;
    public LoginVisitor(Context context){
        this.context = context;
    }
    @Override
    public void visit(RedgistMoneyMediary redgistMoneyMediary) {
        redgistMoneyMediary.gotoPage(context,RedgistMoney.class,"success");
    }

    @Override
    public void visit(ConnectCanMediary connectCanMediary) {
        connectCanMediary.gotoPage(context, Setting.class,"success");
    }

    @Override
    public void visit(RegistLocationMediary registLocationMediary) {
        registLocationMediary.gotoPage(context,RegistLocation.class,"success");
    }

    @Override
    public void visit(RegistTrashcanMediary registTrashcanMediary) {
        registTrashcanMediary.gotoPage(context,RegistTrashcan.class,"success");
    }

    @Override
    public void visit(RedgistrReviseAccountMediary redgistrReviseAccountMediary) {
        redgistrReviseAccountMediary.gotoPage(context, registerReviseAccount.class,"success");
    }

}
