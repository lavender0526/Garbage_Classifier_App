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

public class RegisterActivity extends AppCompatActivity {
    EditText firstname,lastname,email,username,password;
    OkHttpClient client = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    public void btnRegistBackMain(View view) {
        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
        startActivity(intent);
    }
    public void btnRegistConfirm(View view) {
        new RegisterTask().execute();
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    class RegisterTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            JSONObject jsonObject = new JSONObject();
            firstname = (EditText) findViewById(R.id.inputFirstName);
            lastname = (EditText) findViewById(R.id.inputLastName);
            email = (EditText) findViewById(R.id.inputEmail);
            username = (EditText) findViewById(R.id.inputUsername);
            password = (EditText) findViewById(R.id.inputPassword);
            try {
                jsonObject.put("email", email.getText().toString());
                jsonObject.put("lastName", lastname.getText().toString());
                jsonObject.put("name", firstname.getText().toString());
                jsonObject.put("password", password.getText().toString());
                jsonObject.put("userName", username.getText().toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            MediaType mediaType = MediaType.get("application/json; charset=utf-8");
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

}