package com.example.sa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.sa.ChainOfResponsibility.Numbers;
import com.example.sa.ChainOfResponsibility.httpNum;
import com.example.sa.ChainOfResponsibility.http_is_Redirection;
import com.example.sa.ChainOfResponsibility.http_is_Successful;
import com.example.sa.ChainOfResponsibility.http_is_Informational;
import com.example.sa.ChainOfResponsibility.http_is_Server_Error;
import com.example.sa.ChainOfResponsibility.http_is_Client_Error;
import com.example.sa.ChainOfResponsibility.loginError;
import com.example.sa.store.UserStore;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


class Wrapper {
    public Response response;
    public String body;
    Wrapper(Response res,String body){
        this.response = res;
        this.body = body;
    }
}





public class LoginActivity extends AppCompatActivity {
    OkHttpClient client = new OkHttpClient();
    EditText username,password;
    TextView msg;
    Context context;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Context Log = new LoginActivity();
        context = this;
        msg =  findViewById(R.id.loginMsgIncorrect);
    }

    public void btnLoginBackMain(View view) {
        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
        startActivity(intent);
    }
    public void btnLoginConfirm(View view) {
        new loginTask().execute();
    }

    class loginTask extends AsyncTask<Void, Void, Wrapper> {
        @Override
        protected Wrapper doInBackground(Void... voids) {
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
                Wrapper w = new Wrapper(response,response.body().string());
                return w;

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        protected void onPostExecute(Wrapper w) {

            System.out.println(w.body);
            httpNum Successful = new http_is_Successful();
            httpNum Informational = new http_is_Informational();
            httpNum Server_Error = new http_is_Server_Error();
            httpNum Client_Error = new http_is_Client_Error();
            httpNum loginE = new loginError();
            httpNum redirection = new http_is_Redirection();

            Informational.setNexthttp(Successful);
            Successful.setNexthttp(loginE);
            loginE.setNexthttp(redirection);
            redirection.setNexthttp(Client_Error);
            Client_Error.setNexthttp((Server_Error));

            Numbers request = new Numbers(w.response,w.body,context,msg);

            Informational.httpstate(request);


        }

    }




}