package com.example.sa.ChainOfResponsibility;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.TextView;

import com.example.sa.LoginActivity;
import com.example.sa.R;

public class http_is_Successful implements httpNum{

    private httpNum nextInHttp;


    @Override
    public void setNexthttp(httpNum nexthttp) {

        this.nextInHttp = nexthttp;

    }

    @Override
    public void httpstate(Numbers request) {

        if (request.gethttpN1() >= 200 && request.gethttpN1() <300){
            new AlertDialog.Builder(LoginActivity)
                    .setTitle("about")
                    .setMessage("this system is develop to help ambulance " +
                            "reach the emergency site or hospital faster and safer.")
                    .setPositiveButton("ok", new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    }).setNegativeButton("cancel",null).create()
                    .show();




        }else {
            nextInHttp.httpstate(request);
        }


    }
}
