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

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LoginActivity extends AppCompatActivity {
    public Button btnConfirm,btnBackmain;
    OkHttpClient client = new OkHttpClient();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnConfirm = findViewById(R.id.btnLoginConfirm);
        btnBackmain = findViewById(R.id.btnLoginBackmain);
        btnBackmain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                class TestTask extends AsyncTask<Void, Void, Void> {
                    @Override
                    protected Void doInBackground(Void... voids) {
                        JSONObject jsonObject = new JSONObject();
                        EditText username = (EditText) findViewById(R.id.loginInputUsername);
                        EditText password = (EditText) findViewById(R.id.loginInputPassword);
                        try {
                            jsonObject.put("password", password.getText().toString());
                            jsonObject.put("userName", username.getText().toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
                        RequestBody body = RequestBody.create(jsonObject.toString(), mediaType);
                        Request request = new Request.Builder()
                                .url("http://140.125.207.230:8080/api/login")
                                .post(body)
                                .build();

                        try (Response response = client.newCall(request).execute()) {
                            if(response.code()==200){
                                Intent intent = new Intent(LoginActivity.this, RegistTrashcan.class);
                                startActivity(intent);
                            }
                        } catch (IOException e) {
                            Intent intent = new Intent(LoginActivity.this, RegistTrashcan.class);
                            startActivity(intent);
                        }
                        return null;
                    }
                }
                new TestTask().execute();
            }
//                EditText username = (EditText) findViewById(R.id.loginInputUsername);
//                EditText password = (EditText) findViewById(R.id.loginInputPassword);
//                OkHttpClient client = new OkHttpClient().newBuilder()
//                        .build();
//                MediaType mediaType = MediaType.parse("application/json");
//                RequestBody body = RequestBody.create(mediaType,
//                        "{\r\n     \"password\":,"+password+",\r\n" +
//                                "\"username\":\""+username+"\r\n}");
//                Request request = new Request.Builder()
//
//                        .url("http://140.125.207.230:8080/api/login")
//                        .method("POST", body)
//                        .addHeader("Content-Type", "application/json")
//                        .build();
//
//                try (Response response = client.newCall(request).execute()) {
//                    if(response.isSuccessful()){
//                        loginSuceed();
//                    }
//                }
//                catch (IOException e) {
//                    Intent intent = new Intent(LoginActivity.this,RegistTrashcan.class);
//                    startActivity(intent);
//                }
//
//            }
        });

}}