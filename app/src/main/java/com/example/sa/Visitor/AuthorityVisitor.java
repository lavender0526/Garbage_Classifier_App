package com.example.sa.Visitor;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sa.ConnectCan;
import com.example.sa.LoginActivity;
import com.example.sa.MainActivity;
import com.example.sa.NonRegistLocation;
import com.example.sa.NonRegistTrashcan;
import com.example.sa.R;
import com.example.sa.RedgistMoney;
import com.example.sa.RegistLocation;
import com.example.sa.RegistTrashcan;
import com.example.sa.store.UserStore;

public class AuthorityVisitor extends AppCompatActivity implements Visitor {
    UserStore userStore = new UserStore();
    String userName = userStore.getUserName();

    @Override
    public boolean visit(RedgistMoney redgistMoney) {
        System.out.println(redgistMoney);
        System.out.println("user:"+userName);
        if(userName != null){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public boolean visit(ConnectCan ConnectCan) {
        System.out.println(ConnectCan);
        System.out.println("user:"+userName);
        if(userName != null){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public boolean visit(NonRegistLocation nonRegistLocation) {
        System.out.println(nonRegistLocation);
        System.out.println("user:"+userName);
        if(userName != null){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public boolean visit(NonRegistTrashcan nonRegistTrashcan) {
        System.out.println(nonRegistTrashcan);
        System.out.println("user:"+userName);
        if(userName != null){
            return true;
        }
        else{
            return false;
        }
    }
    @Override
    public boolean visit(RegistLocation registLocation) {
        System.out.println(registLocation);
        System.out.println("user:"+userName);
        if(userName != null){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public boolean visit(RegistTrashcan registTrashcan) {
        System.out.println(registTrashcan);
        System.out.println("user:"+userName);
        if(userName != null){
            return true;
        }
        else{
            return false;
        }
    }
}
