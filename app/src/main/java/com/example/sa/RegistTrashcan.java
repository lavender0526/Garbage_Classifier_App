package com.example.sa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.sa.store.UserStore;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RegistTrashcan extends AppCompatActivity {
    private String value;
    TextView bottleStorage,ironStorage,plasticbagStorage;
    private int i=18;
    OkHttpClient client = new OkHttpClient();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist_trashcan);
        bottleStorage = (TextView) findViewById(R.id.bottleCanStorage);
        ironStorage = (TextView) findViewById(R.id.IronbottleCanStorage);
        plasticbagStorage = (TextView) findViewById(R.id.plasticbagCanStorage);
    }

    public void btnRegistTrashcanAccount(View view) {
        Intent intent = new Intent(RegistTrashcan.this,registerReviseAccount.class);
        startActivity(intent);
    }
    public void btnRegistTrashcanLocation(View view) {
        Intent intent = new Intent(RegistTrashcan.this,RegistLocation.class);
        startActivity(intent);
    }

    public void btnRegistTrashcanMoney(View view) {
        Intent intent = new Intent(RegistTrashcan.this,RedgistMoney.class);
        startActivity(intent);
    }

    public void btnRegistTrashcanSetting(View view) {
        Intent intent = new Intent(RegistTrashcan.this,Setting.class);
        startActivity(intent);
    }

    public void btnTrashcanUpdateStorage(View view) {
        new registTrashcanTask().execute();
    }
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

}