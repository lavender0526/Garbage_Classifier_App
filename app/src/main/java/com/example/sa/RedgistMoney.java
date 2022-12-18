package com.example.sa;


import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sa.Bridge.ChangeFont;
import com.example.sa.Bridge.opensansvariablefontwdthwght;
import com.example.sa.ChainOfResponsibility.Numbers;
import com.example.sa.ChainOfResponsibility.httpNum;
import com.example.sa.ChainOfResponsibility.http_is_Client_Error;
import com.example.sa.ChainOfResponsibility.http_is_Informational;
import com.example.sa.ChainOfResponsibility.http_is_Redirection;
import com.example.sa.ChainOfResponsibility.http_is_Server_Error;
import com.example.sa.ChainOfResponsibility.http_is_Successful;
import com.example.sa.ChainOfResponsibility.loginError;
import com.example.sa.Proxy.WalletProxy;
import com.example.sa.Proxy.WalletService;
import com.example.sa.Visitor.Switch;
import com.example.sa.command.Command;
import com.example.sa.command.Concrete_Commands;
import com.example.sa.command.receiver;
import com.example.sa.store.UserStore;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RedgistMoney extends AppCompatActivity {
    String username = UserStore.getInstance().getUsername();
    OkHttpClient client = new OkHttpClient();
    HashMap<String,String> setTextView = new HashMap<String,String>();

    TextView banktype,acctcode,date,balance,moneyview;
    EditText inputmoney ;
    String money;
    WalletService walletProxy= new WalletProxy();

    private Command command;
    private com.example.sa.command.receiver receiver;
    JSONObject raw = null;
//    private  receiver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_redgist_money);
        banktype = (TextView)findViewById(R.id.bankTypeView);
        acctcode = (TextView)findViewById(R.id.accountCodeView);
        date = (TextView)findViewById(R.id.walletUpdateDate);
        balance = (TextView)findViewById(R.id.walletBalance);
        new getBankInfor().execute();

    }

    public void gotoAccount(View view) {
        Switch aswitch = new Switch("RedgistrReviseAccount",this);
        aswitch.activity();
    }

    public void gotoLocation(View view) {
        Switch aswitch = new Switch("RegistLocation",this);
        aswitch.activity();
    }

    public void gotoConnect(View view) {
        Switch aswitch = new Switch("ConnectCan",this);
        aswitch.activity();
    }

    public void gotoHome(View view) {
        Switch aswitch = new Switch("RegistTrashcan",this);
        aswitch.activity();
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


    public void btngomoney(View view) {
        if (inputmoney != null) {
            receiver = new receiver(Integer.parseInt(inputmoney.getText().toString()), Double.parseDouble(setTextView.get("balance")));
            command = new Concrete_Commands(receiver);
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(RedgistMoney.this);
            alertDialog.setView(R.layout.activity_bank_change_money);
            AlertDialog alertDialog1 = alertDialog.create();
            alertDialog1.show();
            moneyview = alertDialog1.findViewById(R.id.Viewchangemoney);
            moneyview.setText(String.valueOf(command.execute()));
            alertDialog1.findViewById(R.id.btngomoneyOK).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    balance.setText(String.valueOf(command.unexecute()));
                    alertDialog1.dismiss();

//                api
                    class updatamoney extends AsyncTask<Void, Void,Boolean> {
                        @Override
                        protected Boolean doInBackground(Void... voids) {
                            JSONObject jsonObject = new JSONObject();
                            try {
                                jsonObject.put("amount", Integer.parseInt(inputmoney.getText().toString()));
                                jsonObject.put("bank_name", setTextView.get("bank_code"));
                                jsonObject.put("receiver", username);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            MediaType mediaType = MediaType.get("application/json; charset=utf-8");
                            RequestBody body = RequestBody.create(jsonObject.toString(), mediaType);
                            Request request = new Request.Builder()
                                    .url("http://140.125.207.230:8080/api/transfer_money_record")
                                    .post(body)
                                    .build();

                            try (Response response = client.newCall(request).execute()) {
                                raw = new JSONObject(response.body().string());

                                //TODO: Store all user information by login response
                                return true;

                            } catch (IOException | JSONException e) {
                                e.printStackTrace();
                            }
                            return false;
                        }

                        protected void onPostExecute(boolean result) {
//                        要將輸入值改成API出来的
                            if(result) {
                                try {
                                    balance.setText(raw.getJSONObject(String.valueOf(0)).getString("updatamoney"));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                alertDialog1.dismiss();
                            }
                        }
                    }


                }
            });
            alertDialog1.findViewById(R.id.btncamcelmoney).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    balance.setText(String.valueOf(command.unexecute()));
                    alertDialog1.dismiss();
                }
            });


        }else {
            AlertDialog.Builder alter = new AlertDialog.Builder(RedgistMoney.this);
            alter.setMessage("請輸入金額\n" + "請你當個聰明的人");
            alter.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            alter.show();
        }


    }

}
