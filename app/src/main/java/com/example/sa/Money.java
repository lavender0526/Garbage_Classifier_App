package com.example.sa;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sa.Proxy.WalletProxy;
import com.example.sa.Proxy.WalletService;
import com.example.sa.Visitor.Page;
import com.example.sa.Visitor.Visotor;
import com.example.sa.store.UserStore;

import org.json.JSONException;

import okhttp3.OkHttpClient;

public class Money extends AppCompatActivity{
    int userID =UserStore.userId;
    private String checkBank;
    EditText bankType,accountCode;
    OkHttpClient client = new OkHttpClient();
    WalletService walletProxy= new WalletProxy();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money);
    }
    public void btnCreatBankAcct(View view) {
        new creatBankAcctTask().execute();
    }

    class creatBankAcctTask extends AsyncTask<Void, Void, Boolean> {
        @Override
        protected Boolean doInBackground(Void... voids) {
            bankType = findViewById(R.id.inputBanktype);
            accountCode = findViewById(R.id.inputAccountcode);
            try {
                return walletProxy.createBankAcct(accountCode.getText().toString(),bankType.getText().toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return false;
        }
        protected void onPostExecute(Boolean result) {
            if (result){
                Intent intent = new Intent(Money.this, RedgistMoney.class);
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
            startActivity(intent);
        }
    }
    public void gotoAccount(View view) {
//                Factory.changePage("Acct",Money.this);
        Intent intent = new Intent(Money.this,registerReviseAccount.class);
        startActivity(intent);
    }
    public void gotoLocation(View view) {
        Intent intent = new Intent(Money.this,RegistLocation.class);
        startActivity(intent);
    }

    public void gotoConnect(View view) {
        Intent intent = new Intent(Money.this,Setting.class);
        startActivity(intent);
    }

    public void gotoHome(View view) {
        Intent intent = new Intent(Money.this,RegistTrashcan.class);
        startActivity(intent);
    }
    public void gotoMoney(View view) {
        Intent intent = new Intent(Money.this,Money.class);
        startActivity(intent);
    }

}

