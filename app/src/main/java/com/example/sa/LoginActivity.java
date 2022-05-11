package com.example.sa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LoginActivity extends AppCompatActivity {
    private Button btnConfirm,btnBackmain;
    OkHttpClient client = new OkHttpClient();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnConfirm = findViewById(R.id.btnConfirm);
        btnBackmain = findViewById(R.id.btnBackmain);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText username = (EditText) findViewById(R.id.editTextTextPersonName6);
                EditText password = (EditText) findViewById(R.id.editTextTextPassword2);
                OkHttpClient client = new OkHttpClient().newBuilder()
                        .build();
                MediaType mediaType = MediaType.parse("application/json");
                RequestBody body = RequestBody.create(mediaType,
                        "{\r\n     \"username\":,"+username+",\r\n" +
                                "\"password\":\""+password+"\r\n}");
                Request request = new Request.Builder()
                        .url("http://140.125.207.230:8080/api/login")
                        .method("POST", body)
                        .addHeader("Content-Type", "application/json")
                        .build();
                try (Response response = client.newCall(request).execute()) {
                    if (response.code()==200 || response.code()==201){
                        Intent intent = new Intent(LoginActivity.this, RegistTrashcan.class);
                        startActivity(intent);
                    }
                } catch (IOException e) {
                    e.loginFailed();
                }

            }
            public void loginFailed extends Exception{
                
            }
        });

    }
}