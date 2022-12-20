package com.example.sa.command;

public abstract class MoneyCommand {
    receiver receiver;
     public MoneyCommand(receiver receiver){
         this.receiver = receiver;
     }

     public abstract int execute();
     public abstract int Undo();


}
