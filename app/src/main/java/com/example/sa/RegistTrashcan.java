package com.example.sa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RegistTrashcan extends AppCompatActivity {
    public String username;
    private String value;
    private Button btnUpdate;
    int i=18;
    OkHttpClient client = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist_trashcan);
        Intent intent2 = getIntent();
        username = intent2.getStringExtra("userLoginName");
        btnUpdate = findViewById(R.id.btnUpdateStorage);
        TextView bottleStorage = (TextView) findViewById(R.id.bottleCanStorage);
        TextView ironStorage = (TextView) findViewById(R.id.IronbottleCanStorage);
        TextView plasticbagStorage = (TextView) findViewById(R.id.plasticbagCanStorage);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                class registTrashcanTask extends AsyncTask<Void, Void,Boolean> {
                    @Override
                    protected Boolean doInBackground(Void... voids) {
                        while (i !=22){
                            Request request = new Request.Builder()
                                    .url("http://140.125.207.230:8080/api/machine_storage/garbage_type/"+i)
                                    .build();
                            try (Response response = client.newCall(request).execute()) {
                                if (response.code() == 200) {
                                    JSONArray jsonArray = new JSONArray(response.body().string());
                                    value = jsonArray.toString();
                                    onPostExecute();
                                    i++;
                                }

                            } catch (IOException | JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        return null;
                    }

                    protected void onPostExecute() {
                        if(i==18){
                            bottleStorage.setText(value+"%");
                        }else if(i==19){
                            ironStorage.setText(value+"%");
                        }else if(i==21){
                            plasticbagStorage.setText(value+"%");
                        }
                        else{
                            System.out.println("11");
                        }
                    }
                }
                new registTrashcanTask().execute();
            }
        });
    }

    public void btnRegistTrashcanAccount(View view) {
        Intent intent = new Intent(RegistTrashcan.this,registerReviseAccount.class);
        intent.putExtra("userLoginName",username);
        startActivity(intent);
    }
    public void btnRegistTrashcanLocation(View view) {
        Intent intent = new Intent(RegistTrashcan.this,RegistLocation.class);
        intent.putExtra("userLoginName",username);
        startActivity(intent);
    }

    public void btnRegistTrashcanMoney(View view) {
        Intent intent = new Intent(RegistTrashcan.this,Money.class);
        intent.putExtra("userLoginName",username);
        startActivity(intent);
    }

    public void btnRegistTrashcanSetting(View view) {
        Intent intent = new Intent(RegistTrashcan.this,Setting.class);
        intent.putExtra("userLoginName",username);
        startActivity(intent);
    }
}