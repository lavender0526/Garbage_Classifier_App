package com.example.sa.ChainOfResponsibility;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import com.example.sa.RegistTrashcan;

import okhttp3.Response;

public class http_is_Client_Error implements httpNum{

    private httpNum nextInHttp;

    @Override
    public void setNexthttp(httpNum nexthttp) {

        this.nextInHttp = nexthttp;

    }



    public void httpstate(Numbers request) {


        System.out.println(request.getResponse().code());
        if (request.getResponse().code() >= 400 && request.getResponse().code() < 500) {
            new AlertDialog.Builder(request.getContext())
                    .setTitle("client ERROR")
                    .setMessage("Please cheak your network.")
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
