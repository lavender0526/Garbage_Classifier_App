package com.example.sa.command;

import com.example.sa.RedgistMoney;

public class receiver  {
    int changemoney;
    int Money;
    double Bankmoney,money,almoney;
    public receiver(int money,double bankmoney){
        this.Money=money;
        this.Bankmoney=bankmoney;
        changemoney();
    }


    public int changemoney(){
        changemoney = Money;
        money = Bankmoney;
        almoney = money - changemoney;
        return (int)almoney;
    }

    public int Undomoney(){
        return (int)money;
    }


}
