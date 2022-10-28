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
        if (request.getResponse().code() >= 200 && request.getResponse().code() < 300 && request.getResponse().code() != 204 ) {
            new AlertDialog.Builder(request.getContext())
                    .setTitle("00")
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
