package com.example.sa.chainOfResponsibility;

public class http_is_Client_Error implements httpNum{

    private httpNum nextInHttp;

    @Override
    public void setNexthttp(httpNum nexthttp) {

        this.nextInHttp = nexthttp;

    }

    @Override
    public void httpstate(Numbers request) {
        if (request.gethttpN1() >= 400 && request.gethttpN1() < 500){



        }else {
            nextInHttp.httpstate(request);
        }


    }
}
