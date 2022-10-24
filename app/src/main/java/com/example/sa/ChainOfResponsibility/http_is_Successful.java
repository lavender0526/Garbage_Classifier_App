package com.example.sa.ChainOfResponsibility;

public class http_is_Successful implements httpNum{

    private httpNum nextInHttp;

    @Override
    public void setNexthttp(httpNum nexthttp) {

        this.nextInHttp = nexthttp;

    }

    @Override
    public boolean httpstate(Numbers request) {
        if (request.gethttpN1() == 200){
            return true;
        }else {
            nextInHttp.httpstate(request);
        }

        return false;
    }
}
