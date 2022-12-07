package com.example.sa.command;

import com.example.sa.RedgistMoney;

public class receiver  {
    int changemoney,money,almoney;
    String a,b;
    public receiver(String a,String b){
        this.a=a;
        this.b=b;
        changemoney();
    }


    public String changemoney(){
        changemoney = Integer.parseInt(a);
        money = Integer.parseInt(b);
        almoney = money - changemoney;
        return String.valueOf(almoney);
    }

    public String Undomoney(){
        return String.valueOf(money);
    }


}
