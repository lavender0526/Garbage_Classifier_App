package com.example.sa.command;

public class Transfer_Money extends MoneyCommand{
    public Transfer_Money(receiver receiver){
        super(receiver);
    }

    @Override
    public int execute() {

        return receiver.changemoney();
    }

    public int Undo(){
        return receiver.Undomoney();
    }

}
