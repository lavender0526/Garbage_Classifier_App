package com.example.sa.ChainOfResponsibility;

import static com.example.sa.R.id.content;
import static com.example.sa.R.id.loginMsgIncorrect;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.example.sa.LoginActivity;
import com.example.sa.R;
import com.example.sa.RegistTrashcan;

public class loginError implements httpNum{
    LoginActivity loginActivity = new LoginActivity();

    TextView msg;
    private httpNum nextInHttp;





    @Override
    public void setNexthttp(httpNum nexthttp) {

        this.nextInHttp = nexthttp;

    }






    public void httpstate(Numbers request) {



        System.out.println(request.getResponse().code());
        if (request.getResponse().code() == 204 ) {
            System.out.println("ERROR");
            loginActivity.showmsg(true);



        } else {
            nextInHttp.httpstate(request);
        }


    }




}
