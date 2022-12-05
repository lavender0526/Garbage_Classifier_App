package com.example.sa.Visitor;

import android.app.Activity;
import android.view.View;

import com.example.sa.ConnectCan;
import com.example.sa.Money;
import com.example.sa.NonRegistLocation;
import com.example.sa.NonRegistTrashcan;
import com.example.sa.RedgistMoney;
import com.example.sa.RegistLocation;
import com.example.sa.RegistTrashcan;

public interface Visitor{
     void visit(RedgistMoneyMediary redgistMoneyMediary);
     void visit(ConnectCanMediary connectCanMediary);
     void visit(RegistLocationMediary registLocationMediary);
     void visit(RegistTrashcanMediary registTrashcanMediary);
}
