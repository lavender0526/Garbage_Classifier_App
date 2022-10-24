package com.example.sa.chainOfResponsibility;

public class http_is_Informational implements httpNum{

    private httpNum nextInHttp;

    @Override
    public void setNexthttp(httpNum nexthttp) {

        this.nextInHttp = nexthttp;

    }

    @Override
    public void httpstate(Numbers request) {
        if (request.gethttpN1() >= 100 && request.gethttpN1() < 200){



        }else {
            nextInHttp.httpstate(request);
        }


    }
}
