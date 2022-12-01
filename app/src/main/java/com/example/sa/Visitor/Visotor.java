package com.example.sa.Visitor;

import android.view.View;

import com.example.sa.ConnectCan;
import com.example.sa.Money;
import com.example.sa.NonRegistLocation;
import com.example.sa.NonRegistTrashcan;
import com.example.sa.RedgistMoney;
import com.example.sa.RegistLocation;
import com.example.sa.RegistTrashcan;

public interface Visotor{

    public void visit(RedgistMoney redgistMoney,Page p);
    public void visit(ConnectCan connectCan,Page p);
    public void visit(NonRegistLocation nonRegistLocation,Page p);
    public void visit(NonRegistTrashcan nonRegistTrashcan,Page p);
    public void visit(RegistLocation registLocation,Page p);
    public void visit(RegistTrashcan registTrashcan,Page p);
}
