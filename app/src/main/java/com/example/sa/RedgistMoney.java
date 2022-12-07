package com.example.sa;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sa.Proxy.WalletProxy;
import com.example.sa.Proxy.WalletService;
import com.example.sa.command.Command;
import com.example.sa.command.Concrete_Commands;
import com.example.sa.command.receiver;
import com.example.sa.store.UserStore;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import okhttp3.OkHttpClient;

public class RedgistMoney extends AppCompatActivity {
    String username = UserStore.getInstance().getUsername();
    OkHttpClient client = new OkHttpClient();
    HashMap<String,String> setTextView = new HashMap<String,String>();
    TextView banktype,acctcode,date,balance,moneyview;
    EditText inputmoney;
    String money;
    WalletService walletProxy= new WalletProxy();
    private Command command;
    private receiver receiver;
//    private  receiver;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_redgist_money);
        banktype = (TextView)findViewById(R.id.bankTypeView);
        acctcode = (TextView)findViewById(R.id.accountCodeView);
        date = (TextView)findViewById(R.id.walletUpdateDate);
        balance = (TextView)findViewById(R.id.walletBalance);
        inputmoney = (EditText)findViewById(R.id.inputmoney);
        new getBankInfor().execute();

    }
    public void gotoAccount(View view) {
        Intent intent = new Intent(RedgistMoney.this,registerReviseAccount.class);
        startActivity(intent);
    }
    public void gotoLocation(View view) {
        Intent intent = new Intent(RedgistMoney.this,RegistLocation.class);
        startActivity(intent);
    }

    public void gotoConnect(View view) {
        Intent intent = new Intent(RedgistMoney.this, Setting.class);
        startActivity(intent);
    }

    public void gotoHome(View view) {
        Intent intent = new Intent(RedgistMoney.this,RegistTrashcan.class);
        startActivity(intent);
    }

    class getBankInfor extends AsyncTask<Void, Void, Boolean> {
        @Override
        protected Boolean doInBackground(Void... voids) {
            JSONObject jsonArray=walletProxy.getWalletInfo();
            try {
                Log.d("INFO",jsonArray.toString());
                setTextView.put("bank_code",jsonArray.getJSONObject("bank_type").getString("bank_code"));
                setTextView.put("acct_code",jsonArray.getString("account_code"));
                setTextView.put("date",jsonArray.getJSONObject("user").getJSONObject("wallet").getString("time_stamp"));
                setTextView.put("balance",jsonArray.getJSONObject("user").getJSONObject("wallet").getString("change_value"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return false;
        }
        protected void onPostExecute(Boolean result) {
            if (result){
                long datetime = Long.valueOf(setTextView.get("date"));
                java.util.Date time=new java.util.Date((long)datetime*1000);
                banktype.setText(setTextView.get("bank_code"));
                acctcode.setText(setTextView.get("acct_code"));
                date.setText(String.valueOf(time).substring(0,19));
                balance.setText(setTextView.get("balance"));
            }
        }
    }

    public void btnUpdateWalletBalance(View view) {
        class updateBalance extends AsyncTask<Void, Void, Void> {
            @Override
            protected Void doInBackground(Void... voids) {
                JSONObject jsonArray = walletProxy.updateWalletInfo(username);
                try {
                    setTextView.put("date",jsonArray.getString("time_stamp"));
                    setTextView.put("balance",jsonArray.getString("change_value"));
                } catch (JSONException e) {
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
//    public String getchangemoney(){
//        return inputmoney.getText().toString();
//    }
//    public String getmoney(){
//        return setTextView.get("balance");
//    }
    public void btngomoney(View view){

        money = balance.getText().toString();
        System.out.println(money);
        receiver = new receiver(inputmoney.getText().toString() ,setTextView.get("balance"));
        command = new Concrete_Commands(receiver);
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(RedgistMoney.this);
        alertDialog.setView(R.layout.activity_bank_change_money);
        AlertDialog alertDialog1 = alertDialog.create();
        alertDialog1.show();
        moneyview = alertDialog1.findViewById(R.id.Viewchangemoney);
        moneyview.setText(command.execute());
        alertDialog1.findViewById(R.id.btngomoneyOK).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                balance.setText(command.execute());
            }
        });
    }





}
