package com.example.sa;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class registerReviseAccount extends AppCompatActivity {
    private String username;
    TextView userName;
    EditText password;
    OkHttpClient client = new OkHttpClient();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_revise_account);
        userName = (TextView)  findViewById(R.id.username);
        Intent intent = getIntent();
        username = intent.getStringExtra("userLoginName");
        userName.setText(username);
        password = (EditText) findViewById(R.id.reviseInputPassword);

    }
    public void btnRegistReviseAccountSetting(View view) {
        Intent intent = new Intent(registerReviseAccount.this,Setting.class);
        startActivity(intent);
    }
    public void btnRegistReviseAccountHome(View view) {
        Intent intent = new Intent(registerReviseAccount.this,RegistTrashcan.class);
        startActivity(intent);
    }
    public void btnRegistReviseAccountLocation(View view) {
        Intent intent = new Intent(registerReviseAccount.this,RegistLocation.class);
        startActivity(intent);
    }
    public void btnRegistReviseAccountMoney(View view) {
        Intent intent = new Intent(registerReviseAccount.this,Money.class);
        startActivity(intent);
    }
    public void btnReviseAcct(View view) {
        new registerReviseAccountTrashcanTask().execute();
    }
    class registerReviseAccountTrashcanTask extends AsyncTask<Void, Void,Boolean> {
        @Override
        protected Boolean doInBackground(Void... voids) {
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("password", password.getText().toString());
                jsonObject.put("username", username);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            MediaType mediaType = MediaType.get("application/json; charset=utf-8");
            RequestBody body = RequestBody.create(jsonObject.toString(), mediaType);
            Request request = new Request.Builder()
                    .url("http://140.125.207.230:8080/api/changePassword")
                    .put(body)
                    .build();

            try (Response response = client.newCall(request).execute()) {
                if(response.code()==200){
                    return true;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;
        }

        protected void onPostExecute(Boolean result) {
            if(result) {
                AlertDialog.Builder alertDialog =
                        new AlertDialog.Builder(registerReviseAccount.this);
                alertDialog.setTitle("Password changed successful");
                alertDialog.setMessage("Password changed successful");
                alertDialog.setCancelable(true);
                alertDialog.show();
                password.setText("");
            }
            else {
                AlertDialog.Builder alertDialog =
                        new AlertDialog.Builder(registerReviseAccount.this);
                alertDialog.setTitle("ERROR");
                alertDialog.setMessage("ERROR");
                alertDialog.setCancelable(true);
                alertDialog.show();
            }
        }
    }
}