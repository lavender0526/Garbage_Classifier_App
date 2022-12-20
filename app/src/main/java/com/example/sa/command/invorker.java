package com.example.sa.command;

import java.util.ArrayList;
import java.util.List;

public class invorker {
    private List<MoneyCommand> moneyCommandList = new ArrayList<MoneyCommand>();
    private List<MoneyCommand> moneyCommandListUndo = new ArrayList<MoneyCommand>();
    int money;

    public void addMoneyCommand(MoneyCommand moneyCommand){moneyCommandList.add(moneyCommand);}
    public  void addMoneyCommandUndo(MoneyCommand moneyCommand){moneyCommandListUndo.add(moneyCommand);}

    public void excute(){
        for (MoneyCommand moneyCommand : moneyCommandList){
             money= moneyCommand.execute();
        }



        
    }

    public  void Undo(){
        for (MoneyCommand moneyCommand:moneyCommandListUndo){
           money = moneyCommand.Undo();
        }
    }

    public int getint(){
        return money;
    }
}
