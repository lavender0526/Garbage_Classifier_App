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
        btnConfirm = findViewById(R.id.btnBackmain);
        btnBackmain = findViewById(R.id.btnConfirmRegist);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                class TestTask extends AsyncTask<Void, Void, Void> {
//                    @Override
//                    protected Void doInBackground(Void... voids) {
//                        JSONObject jsonObject = new JSONObject();
//                        EditText username = (EditText) findViewById(R.id.editTextTextPersonName6);
//                        EditText password = (EditText) findViewById(R.id.editTextTextPassword2);
//                        class BasicAuthInterceptor implements Interceptor {
//                            private String credentials;
//
//
//                            public BasicAuthInterceptor(EditText user, EditText password) {
//                                this.credentials = Credentials.basic(user, password);
//                            }
//                            @Override
//                            public Response intercept(Chain chain) throws IOException {
//                                Request request = chain.request();
//                                Request authenticatedRequest = request.newBuilder()
//                                        .header("Authorization", credentials).build();
//                                return chain.proceed(authenticatedRequest);
//                            }
//                        }
//                        OkHttpClient client = new OkHttpClient.Builder()
//                                .addInterceptor(new BasicAuthInterceptor(username,password))
//                                .build();
//                        RequestBody body = RequestBody.create(jsonObject.toString(), mediaType);
//                        final Request request = new Request.Builder()
//                                .url("http://140.125.207.230:8080/api/register"
//                                .post(body)
//                                .build();
//                        Response response = client.newCall(request).execute();
//                        try {
//                            jsonObject.put("password", password.getText().toString());
//                            jsonObject.put("userName", username.getText().toString());
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
//                        RequestBody body = RequestBody.create(jsonObject.toString(), mediaType);
//                        Request request = new Request.Builder()
//                                .url("http://140.125.207.230:8080/api/register")
//                                .post(body)
//                                .build();
//
//                        try (Response response = client.newCall(request).execute()) {
//                            System.out.println(response.body().string());
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//
//                        return null;
//                    }
//                }
//                new TestTask().execute();

//                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
//                startActivity(intent);
            }
        });

    }
}