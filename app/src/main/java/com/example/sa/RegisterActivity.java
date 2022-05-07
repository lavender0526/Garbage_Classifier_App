package com.example.sa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RegisterActivity extends AppCompatActivity {
    private Button btnBackMain,btnLogin;
    OkHttpClient client = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btnBackMain=findViewById(R.id.btnBackMain);
        btnLogin=findViewById(R.id.btnLogin);
        btnBackMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                class TestTask extends AsyncTask<Void, Void, Void> {
                    @Override
                    protected Void doInBackground(Void... voids) {
                        JSONObject jsonObject = new JSONObject();
                        try {
                            jsonObject.put("email", "123@gmail.com");
                            jsonObject.put("lastName", "test");
                            jsonObject.put("name", "test1");
                            jsonObject.put("password", "123456");
                            jsonObject.put("userName", "test1");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        MediaType mediaType  = MediaType.get("application/json; charset=utf-8");
                        RequestBody body = RequestBody.create(jsonObject.toString(), mediaType);
                        Request request = new Request.Builder()
                                .url("http://140.125.207.230:8080/api/register")
                                .post(body)
                                .build();

                        try (Response response = client.newCall(request).execute()) {
                            System.out.println(response.body().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        return null;
                    }
                }
                new TestTask().execute();

                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}