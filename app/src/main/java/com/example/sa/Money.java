package com.example.sa;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Money extends AppCompatActivity {
    private String username,checkBank;
    private int userID;
    OkHttpClient client = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money);
        Intent intent2 = getIntent();
        username = intent2.getStringExtra("userLoginName");
        new confirmCreatedBankTask().execute();
    }
    public void btnCreatBankAcct(View view) {
        new creatBankAcctTask().execute();
    }
    class confirmCreatedBankTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            Request request = new Request.Builder()
                    .url("http://140.125.207.230:8080/api/back_acct/username/"+username)
                    .build();
            try (Response response = client.newCall(request).execute()) {
                if (response.code() == 200) {
                    JSONObject jsonArray = new JSONObject(response.body().string());
                    checkBank = jsonArray.getString("bank_type");
                    check();
                }

            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
    class creatBankAcctTask extends AsyncTask<Void, Void, Boolean> {
        @Override
        protected Boolean doInBackground(Void... voids) {
            JSONObject jsonObject = new JSONObject();
            EditText bankType = findViewById(R.id.inputBanktype);
            EditText accountCode = findViewById(R.id.inputAccountcode);
            try {
                jsonObject.put("account_code", accountCode.getText().toString());
                jsonObject.put("bank_type", bankType.getText().toString());
                jsonObject.put("user", userID);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            MediaType mediaType = MediaType.get("application/json; charset=utf-8");
            RequestBody body = RequestBody.create(jsonObject.toString(), mediaType);
            Request request = new Request.Builder()
                    .url("http://140.125.207.230:8080/api/bank_acct")
                    .post(body)
                    .build();

            try (Response response = client.newCall(request).execute()) {
                System.out.println(response.code());
                if (response.code() == 200 || response.code() ==201) {
                    return true;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;
        }
        protected void onPostExecute(Boolean result) {
            if (result){
                Intent intent = new Intent(Money.this, CreatedMoney.class);
                startActivity(intent);
            }else{
                System.out.println("ERROR");
            }
        }

    }
    protected void check(){
        System.out.println(checkBank);
        if (checkBank != "null") {
            Intent intent = new Intent(Money.this, RedgistMoney.class);
            intent.putExtra("userLoginName", username);
            startActivity(intent);
        }
    }
    public void btnMoneyAccount(View view) {
        Intent intent = new Intent(Money.this,registerReviseAccount.class);
        intent.putExtra("userLoginName",username);
        startActivity(intent);
    }
    public void btnMoneyLocation(View view) {
        Intent intent = new Intent(Money.this,RegistLocation.class);
        intent.putExtra("userLoginName",username);
        startActivity(intent);
    }

    public void btnMoneySetting(View view) {
        Intent intent = new Intent(Money.this,Setting.class);
        intent.putExtra("userLoginName",username);
        startActivity(intent);
    }

    public void btnMoneyHome(View view) {
        Intent intent = new Intent(Money.this,RegistTrashcan.class);
        intent.putExtra("userLoginName",username);
        startActivity(intent);
    }

}

