package com.example.sa.ChainOfResponsibility;

import static com.example.sa.R.id.content;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sa.LoginActivity;
import com.example.sa.R;
import com.example.sa.RegistTrashcan;

public class loginError implements httpNum{
    LoginActivity loginActivity = new LoginActivity();


    private httpNum nextInHttp;





    @Override
    public void setNexthttp(httpNum nexthttp) {

        this.nextInHttp = nexthttp;

    }






    public void httpstate(Numbers request) {


        System.out.println(request.getResponse().code());
        if (request.getResponse().code() == 204 ) {
            System.out.println("ERROR");

            request.getTextView().setVisibility(View.VISIBLE);



        } else {
            nextInHttp.httpstate(request);
        }


    }




}
