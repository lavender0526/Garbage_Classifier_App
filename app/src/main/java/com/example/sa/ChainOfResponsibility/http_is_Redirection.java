package com.example.sa.ChainOfResponsibility;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import com.example.sa.RegistTrashcan;

import okhttp3.Response;

public class http_is_Redirection implements httpNum{

    private httpNum nextInHttp;

    @Override
    public void setNexthttp(httpNum nexthttp) {

        this.nextInHttp = nexthttp;

    }

    @Override
    public void httpstate(Numbers request) {


        System.out.println(request.getResponse().code());
        if (request.getResponse().code() >= 100 && request.getResponse().code() < 600) {
            System.out.println(request.getResponse().code());
            new AlertDialog.Builder(request.getContext())
                    .setTitle("Redirection")
                    .setMessage("this system is developed to help ambulance " +
                            "reach the emergency site or hospital faster and safer.")
                    .setPositiveButton("ok", new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    }).setNegativeButton("cancel",null).create()
                    .show();
        } else {
            nextInHttp.httpstate(request);
        }


    }
}
