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
        System.out.printf("go RegistTrashcan");
        Switch aswitch = new Switch("RegistTrashcan");
        aswitch.activity();
//        if(isAccept){
//            Intent intent = new Intent(Setting.this, RegistTrashcan.class);
//            startActivity(intent);
//        }
//        else{
//            Intent intent = new Intent(Setting.this, LoginActivity.class);
//            startActivity(intent);
//        }
    }
    public void btnsettingAccount(View view) {
        Intent intent = new Intent(Setting.this,registerReviseAccount.class);
        startActivity(intent);
    }
    public void btnsettingLocation(View view) {
        System.out.printf("go RegistLocation");
        Switch aswitch = new Switch("RegistLocation");
        aswitch.activity();
//        if(isAccept){
//            Intent intent = new Intent(Setting.this, RegistLocation.class);
//            startActivity(intent);
//        }
//        else{
//            Intent intent = new Intent(Setting.this, LoginActivity.class);
//            startActivity(intent);
//        }
    }
    public void btnsettingMoney(View view) {
        System.out.printf("go RedgistMoney");
        Switch aswitch = new Switch("RedgistMoney");
        aswitch.activity();
//        if(isAccept){
//            Intent intent = new Intent(Setting.this, RedgistMoney.class);
//            startActivity(intent);
//        }
//        else{
//            Intent intent = new Intent(Setting.this, LoginActivity.class);
//            startActivity(intent);
//        }
    }
    public void btnConnectCan(View view) {
        System.out.printf("go ConnectCan");
        Switch aswitch = new Switch("ConnectCan");
        aswitch.activity();
//        if(isAccept){
//            Intent intent = new Intent(Setting.this, Setting.class);
//            startActivity(intent);
//        }
//        else{
//            Intent intent = new Intent(Setting.this, LoginActivity.class);
//            startActivity(intent);
//        }
    }

}
