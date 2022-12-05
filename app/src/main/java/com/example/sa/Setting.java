package com.example.sa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sa.Visitor.Page;
import com.example.sa.Visitor.Switch;
import com.example.sa.Visitor.Visitor;
import com.example.sa.store.UserStore;
import com.google.zxing.Result;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import me.dm7.barcodescanner.zxing.ZXingScannerView;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

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
