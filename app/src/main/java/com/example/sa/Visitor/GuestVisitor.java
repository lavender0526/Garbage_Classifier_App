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
    public void visit(RedgistMoneyMiddleware redgistMoneyMiddleware) {
        redgistMoneyMiddleware.gotoPage(context,LoginActivity.class,"Please login");
    }

    @Override
    public void visit(ConnectCanMiddleware connectCanMiddleware) {
        connectCanMiddleware.gotoPage(context,LoginActivity.class,"Please login");
    }

    @Override
    public void visit(RegistLocationMiddleware registLocationMiddleware) {
        registLocationMiddleware.gotoPage(context,NonRegistLocation.class,"success");
    }

    @Override
    public void visit(RegistTrashcanMiddleware registTrashcanMiddleware) {
        registTrashcanMiddleware.gotoPage(context,NonRegistTrashcan.class,"success");
    }

    @Override
    public void visit(RedgistrReviseAccountMiddleware redgistrReviseAccountMiddleware) {
        redgistrReviseAccountMiddleware.gotoPage(context, LoginActivity.class,"success");
    }

}
