package com.example.sa.Bridge;

abstract class Font {
    protected ChangeFont changefont;

    protected Font(ChangeFont changefont){
        this.changefont = changefont;
    }

    abstract public void change();
}
