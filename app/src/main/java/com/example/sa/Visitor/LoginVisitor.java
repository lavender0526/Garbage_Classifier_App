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
    public void visit(RedgistMoneyMiddleware redgistMoneyMiddleware) {
        redgistMoneyMiddleware.gotoPage(context,RedgistMoney.class,"success");
    }

    @Override
    public void visit(ConnectCanMiddleware connectCanMiddleware) {
        connectCanMiddleware.gotoPage(context, Setting.class,"success");
    }

    @Override
    public void visit(RegistLocationMiddleware registLocationMiddleware) {
        registLocationMiddleware.gotoPage(context,RegistLocation.class,"success");
    }

    @Override
    public void visit(RegistTrashcanMiddleware registTrashcanMiddleware) {
        registTrashcanMiddleware.gotoPage(context,RegistTrashcan.class,"success");
    }

    @Override
    public void visit(RedgistrReviseAccountMiddleware redgistrReviseAccountMiddleware) {
        redgistrReviseAccountMiddleware.gotoPage(context, registerReviseAccount.class,"success");
    }

}
