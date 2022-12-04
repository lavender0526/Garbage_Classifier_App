package com.example.sa.ChainOfResponsibility;

import android.content.Context;
import android.widget.TextView;

import com.example.sa.LoginActivity;

import okhttp3.Response;

public class Numbers  {
    private Context context1;
    private Response response1;
    private String body1;
    private TextView textView;
    public Numbers(Response response,String body,Context context,TextView textView){
        response1 = response;
        body1 = body;
        context1 = context;
        this.textView = textView;
    }

    public String getBody(){return body1;}
    public Response getResponse(){return response1;}
    public Context getContext(){return context1;}
    public TextView getTextView(){return  this.textView;}
}
