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
     boolean visit(RedgistMoney redgistMoney);
     boolean visit(ConnectCan connectCan);
     boolean visit(NonRegistLocation nonRegistLocation);
     boolean visit(NonRegistTrashcan nonRegistTrashcan);
     boolean visit(RegistLocation registLocation);
     boolean visit(RegistTrashcan registTrashcan);
}
