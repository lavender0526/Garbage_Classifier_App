package com.example.sa.command;

public class Concrete_Commands implements Command{
    private receiver receiver;
    String a,b;
    public Concrete_Commands(receiver receiver){
        this.receiver=receiver;
    }

    @Override
    public String execute() {
//        receiver = new receiver();
        return receiver.changemoney();

    }

    @Override
    public String unexecute() {
        return receiver.Undomoney();
    }
}
