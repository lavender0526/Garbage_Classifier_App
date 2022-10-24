package com.example.sa.chainOfResponsibility;

public class http_is_Successful implements httpNum{

    private httpNum nextInHttp;

    @Override
    public void setNexthttp(httpNum nexthttp) {

        this.nextInHttp = nexthttp;

    }

    @Override
    public void httpstate(Numbers request) {
        if (request.gethttpN1() >= 200 && request.gethttpN1() < 300){

        }else {
            nextInHttp.httpstate(request);
        }


    }
}
