package com.example.sa.Observer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Observer;

public class ConcreteAttribute implements GarbageAttribute {
    private HashMap<GarbageAttribute,List<GarbageCan>> garbageList = new HashMap<>();

    public void Attach(GarbageCan garbageCan){
        List<GarbageCan> tempList = garbageList.get(this);
        tempList.add(garbageCan);
        garbageList.put(this,tempList);
    }
    public void Notify (){
        List<GarbageCan> tempList = garbageList.get(this);
        for(GarbageCan O : tempList){
            O.Update();
        }

    }
    public void Create(){
        garbageList.put(this,new ArrayList<>());
    }
}
