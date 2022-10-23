package com.example.sa.chainOfResponsibility;

public class http_is_404 implements httpNum{

    private httpNum nextInHttp;

    @Override
    public void setNexthttp(httpNum nexthttp) {

        this.nextInHttp = nexthttp;

    }

    @Override
    public boolean httpstate(Numbers request) {
        if (request.gethttpN1() == 404){
            return false;
        }else {
            nextInHttp.httpstate(request);
        }

        return false;
    }
}
