package com.example.sa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RedgistMoney extends AppCompatActivity {
    private String username;
    OkHttpClient client = new OkHttpClient();
    HashMap<String,String> setTextView = new HashMap<String,String>();
    TextView banktype,acctcode,date,balance;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_redgist_money);
        Intent intent2 = getIntent();
        username = intent2.getStringExtra("userLoginName");
        banktype = (TextView)findViewById(R.id.bankTypeView);
        acctcode = (TextView)findViewById(R.id.accountCodeView);
        date = (TextView)findViewById(R.id.walletUpdateDate);
        balance = (TextView)findViewById(R.id.walletBalance);
        new getBankInfor().execute();

    }
    class getBankInfor extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            Request request = new Request.Builder()
                    .url("http://140.125.207.230:8080/api/back_acct/username/"+username)
                    .build();
            try (Response response = client.newCall(request).execute()) {
                if (response.code() == 200) {
                    JSONObject jsonArray = new JSONObject(response.body().string());
                    setTextView.put("bank_code",jsonArray.getJSONObject("bank_type").getString("bank_code"));
                    setTextView.put("acct_code",jsonArray.getString("account_code"));
                    setTextView.put("date",jsonArray.getJSONObject("user").getJSONObject("wallet").getString("time_stamp"));
                    setTextView.put("balance",jsonArray.getJSONObject("user").getJSONObject("wallet").getString("change_value"));
                }

            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            return null;
        }
        protected void onPostExecute(Void aVoid) {
            long datetime = Long.valueOf(setTextView.get("date"));
            java.util.Date time=new java.util.Date((long)datetime*1000);
            banktype.setText(setTextView.get("bank_code"));
            acctcode.setText(setTextView.get("acct_code"));
            date.setText(String.valueOf(time).substring(0,19));
            balance.setText(setTextView.get("balance"));
        }
    }

    public void btnCreatedMoneyAccount(View view) {
        Intent intent = new Intent(RedgistMoney.this,registerReviseAccount.class);
        intent.putExtra("userLoginName",username);
        startActivity(intent);
    }
    public void btnCreatedMoneyLocation(View view) {
        Intent intent = new Intent(RedgistMoney.this,RegistLocation.class);
        intent.putExtra("userLoginName",username);
        startActivity(intent);
    }

    public void btnCreatedMoneySetting(View view) {
        Intent intent = new Intent(RedgistMoney.this,Setting.class);
        intent.putExtra("userLoginName",username);
        startActivity(intent);
    }

    public void btnCreatedMoneyHome(View view) {
        Intent intent = new Intent(RedgistMoney.this,RegistTrashcan.class);
        intent.putExtra("userLoginName",username);
        startActivity(intent);
    }

    public void btnUpdateWalletBalance(View view) {
        class updateBalance extends AsyncTask<Void, Void, Void> {
            @Override
            protected Void doInBackground(Void... voids) {
                Request request = new Request.Builder()
                        .url("http://140.125.207.230:8080/api/walletInfro/"+username)
                        .build();
                try (Response response = client.newCall(request).execute()) {
                    if (response.code() == 200) {
                        JSONObject jsonArray = new JSONObject(response.body().string());
                        setTextView.put("date",jsonArray.getString("time_stamp"));
                        setTextView.put("balance",jsonArray.getString("change_value"));
                    }

                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
                return null;
            }
            protected void onPostExecute(Void aVoid) {
                long datetime = Long.valueOf(setTextView.get("date"));
                java.util.Date time=new java.util.Date((long)datetime*1000);
                date.setText(String.valueOf(time).substring(0,19));
                balance.setText(setTextView.get("balance"));
            }
    }
        new updateBalance().execute();
}
}
