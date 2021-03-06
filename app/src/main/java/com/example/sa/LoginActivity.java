package com.example.sa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.sa.store.UserStore;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LoginActivity extends AppCompatActivity {
    OkHttpClient client = new OkHttpClient();
    EditText username,password;
    TextView msg;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    }
    public void btnLoginBackMain(View view) {
        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
        startActivity(intent);
    }
    public void btnLoginConfirm(View view) {
        new loginTask().execute();
    }

    class loginTask extends AsyncTask<Void, Void, Boolean> {
        @Override
        protected Boolean doInBackground(Void... voids) {
            JSONObject jsonObject = new JSONObject();
            username = (EditText) findViewById(R.id.loginInputUsername);
            password = (EditText) findViewById(R.id.loginInputPassword);
            UserStore.userName = username.getText().toString();
            try {
                jsonObject.put("password", password.getText().toString());
                jsonObject.put("username", username.getText().toString());
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
                    JSONObject user = new JSONObject(response.body().string());
                    UserStore.userId = user.getInt("id");
                    return true;
                }

            } catch (IOException|JSONException e) {
                e.printStackTrace();
            }
            return false;
        }
        protected void onPostExecute(Boolean result) {
            msg = (TextView)  findViewById(R.id.loginMsgIncorrect);
            if (result){
                Intent intent = new Intent(LoginActivity.this, RegistTrashcan.class);
                startActivity(intent);
            }else{
                msg.setVisibility( View.VISIBLE);
            }
        }

    }

}