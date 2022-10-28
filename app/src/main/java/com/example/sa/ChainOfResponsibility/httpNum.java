package com.example.sa.ChainOfResponsibility;

import android.content.Context;

import com.example.sa.LoginActivity;

import okhttp3.Response;

public interface httpNum  {

    public void setNexthttp(httpNum nexthttp);
    public void httpstate(Numbers request);


}
