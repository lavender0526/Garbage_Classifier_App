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

import com.google.zxing.Result;


import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class Setting extends AppCompatActivity implements ZXingScannerView.ResultHandler {
    private String username;
    ZXingScannerView zXingScannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        Intent intent2 = getIntent();
        username = intent2.getStringExtra("userLoginName");
        zXingScannerView = findViewById(R.id.ZXingScannerView_QRCode);
        //取得相機權限
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
                ActivityCompat.checkSelfPermission(this
                        , Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA},
                    100);
        }else{
            //若先前已取得權限，則直接開啟
            openQRCamera();
        }

    }
    private void openQRCamera(){
        zXingScannerView.setResultHandler(this);
        zXingScannerView.startCamera();
    }
    /**取得權限回傳*/
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 100 && grantResults[0] ==0){
            openQRCamera();
        }else{
            Toast.makeText(this, "需要相機權限", Toast.LENGTH_SHORT).show();
        }
    }
    /**關閉QRCode相機*/
    @Override
    protected void onStop() {
        zXingScannerView.stopCamera();
        super.onStop();
    }
    /**取得QRCode掃描到的物件回傳*/
    @Override
    public void handleResult(Result rawResult) {
        String machineURL = rawResult.getText();
        System.out.println(machineURL);
//        openQRCamera();
    }

    public void btnsettingHome(View view) {
        Intent intent = new Intent(Setting.this,MainActivity.class);
        intent.putExtra("userLoginName",username);
        startActivity(intent);
    }
    public void btnsettingAccount(View view) {
        Intent intent = new Intent(Setting.this,registerReviseAccount.class);
        intent.putExtra("userLoginName",username);
        startActivity(intent);
    }
    public void btnsettingLocation(View view) {
        Intent intent = new Intent(Setting.this,RegistLocation.class);
        intent.putExtra("userLoginName",username);
        startActivity(intent);
    }
    public void btnsettingMoney(View view) {
        Intent intent = new Intent(Setting.this,Money.class);
        intent.putExtra("userLoginName",username);
        startActivity(intent);
    }
}