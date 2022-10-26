package com.example.sa.ChainOfResponsibility;

public class http_is_Server_Error implements httpNum{

    private httpNum nextInHttp;

    @Override
    public void setNexthttp(httpNum nexthttp) {

        this.nextInHttp = nexthttp;

    }

    @Override
    public void httpstate(Numbers request) {
        if (request.gethttpN1() >= 500 && request.gethttpN1() < 600){

        }else {
            nextInHttp.httpstate(request);
        }


    }
}
