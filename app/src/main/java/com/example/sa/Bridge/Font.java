package com.example.sa.Bridge;

abstract class Font {
    protected ChangeFont Change;

    protected Font(ChangeFont Change){
        this.Change=Change;
    }

    abstract public void function();
}
