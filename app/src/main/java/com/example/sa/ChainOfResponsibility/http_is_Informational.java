package com.example.sa.ChainOfResponsibility;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import com.example.sa.RegistTrashcan;

import okhttp3.Response;

public class http_is_Informational implements httpNum{

    private httpNum nextInHttp;

    @Override
    public void setNexthttp(httpNum nexthttp) {

        this.nextInHttp = nexthttp;

    }

    public void httpstate(Numbers request) {


        System.out.println(request.getResponse().code());
        if (request.getResponse().code() >= 100 && request.getResponse().code() < 200) {
            System.out.println(request.getResponse().code());
            System.out.println("OK");
        } else {
            nextInHttp.httpstate(request);
        }


    }
}
