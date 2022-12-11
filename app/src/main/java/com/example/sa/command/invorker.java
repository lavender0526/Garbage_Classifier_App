package com.example.sa.command;

import java.util.ArrayList;
import java.util.List;

public class invorker {
    private List<Command> commandList = new ArrayList<Command>();
    private List<Command> commandList1 = new ArrayList<Command>();

    public void addCommend(Command Command) {
        commandList.add(Command);
    }
    public void execute() {
        for(Command command : commandList) {
            command.execute();
        }
    }
    public void unexecute(){
        for (Command command : commandList1){
            command.unexecute();
        }
    }
}
