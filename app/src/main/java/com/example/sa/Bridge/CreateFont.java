package com.example.sa.Bridge;


class CreateFont extends Font{

    public CreateFont(ChangeFont Change){
        super(Change);
    }
    @Override
    public void function() {
        Change.method();
    }
}
