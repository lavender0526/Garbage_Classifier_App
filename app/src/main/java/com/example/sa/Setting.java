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

class linkTask extends AsyncTask<String, Void, Void> {
    @Override
    protected Void doInBackground(String... params) {
        // http://140.125.207.230:8080/api/machine/{machineId}/link
        String machineURL = params[0];
        int userId = UserStore.userId;

        // http://140.125.207.230:8080/api/machine/{machineId}/link/{userId}
        String url = String.format("%s/%d",machineURL,userId);
        System.out.println(userId);
        System.out.println(url);
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(new JSONObject().toString(), mediaType);
        Request request = new Request.Builder()
                .url(url)
                .patch(body)
                .build();
        OkHttpClient client = new OkHttpClient();
        try (Response response = client.newCall(request).execute()) {
            System.out.println(response.code());
            if (response.code() == 200) {
                System.out.println("Success...");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}

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
        new linkTask().execute(machineURL);
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