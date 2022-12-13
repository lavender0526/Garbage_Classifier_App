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

    public void gotoHome(View view) {
        Switch localSwitch = new Switch("RegistTrashcan",this);
        localSwitch.activity();
    }
    public void gotoAccount(View view) {
        Switch localSwitch = new Switch("RedgistrReviseAccount",this);
        localSwitch.activity();
    }
    public void gotoLocation(View view) {
        Switch localSwitch = new Switch("RegistLocation",this);
        localSwitch.activity();
    }
    public void gotoMoney(View view) {
        Switch localSwitch = new Switch("RedgistMoney",this);
        localSwitch.activity();
    }
    public void btnConnectCan(View view) {
        Switch localSwitch = new Switch("ConnectCan",this);
        localSwitch.activity();
    }

}
