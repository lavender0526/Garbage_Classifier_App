package com.example.sa.ChainOfResponsibility;

public class http_is_Redirection implements httpNum{

    private httpNum nextInHttp;

    @Override
    public void setNexthttp(httpNum nexthttp) {

        this.nextInHttp = nexthttp;

    }

    @Override
    public void httpstate(Numbers request) {
        if (request.gethttpN1() >= 300 && request.gethttpN1() < 400){


        }else {
            nextInHttp.httpstate(request);
        }

        }
}
