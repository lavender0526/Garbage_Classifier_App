package com.example.sa.ChainOfResponsibility;

public class http_is_Client_Error implements httpNum{

    private httpNum nextInHttp;

    @Override
    public void setNexthttp(httpNum nexthttp) {

        this.nextInHttp = nexthttp;

    }

    @Override
    public boolean httpstate(Numbers request) {
        if (request.gethttpN1() == 502){

            return false;
        }else {
            nextInHttp.httpstate(request);
        }

        return false;
    }
}
