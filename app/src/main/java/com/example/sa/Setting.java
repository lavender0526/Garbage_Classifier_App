package com.example.sa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.sa.Visitor.Switch;

public class Setting extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
    }

    public void btnsettingHome(View view) {
        Switch aswitch = new Switch("RegistTrashcan",this);
        aswitch.activity();
    }
    public void btnsettingAccount(View view) {
        Switch aswitch = new Switch("RedgistrReviseAccount",this);
        aswitch.activity();
    }
    public void btnsettingLocation(View view) {
        Switch aswitch = new Switch("RegistLocation",this);
        aswitch.activity();
    }
    public void btnsettingMoney(View view) {
        Switch aswitch = new Switch("RedgistMoney",this);
        aswitch.activity();
    }
    public void btnConnectCan(View view) {
        Switch aswitch = new Switch("ConnectCan",this);
        aswitch.activity();
    }

}
