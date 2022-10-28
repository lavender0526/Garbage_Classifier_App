package com.example.sa.ChainOfResponsibility;

import android.content.Context;

import com.example.sa.LoginActivity;

import okhttp3.Response;

public class Numbers  {
    private Context context1;
    private Response response1;
    private String body1;

    public Numbers(Response response,String body,Context context){
        response1 = response;
        body1 = body;
        context1 = context;

    }

    public String getBody(){return body1;}
    public Response getResponse(){return response1;}
    public Context getContext(){return context1;}

}
