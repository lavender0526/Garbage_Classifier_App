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

public class NonRegistTrashcan extends AppCompatActivity {
    private int i=18;
    private String value;
    TextView bottleStorage,ironStorage,plasticbagStorage;
    OkHttpClient client = new OkHttpClient();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_non_regist_trashcan);
        bottleStorage = (TextView) findViewById(R.id.plasticCanStorageNon);
        ironStorage = (TextView) findViewById(R.id.ironbottleCanStorageNon);
        plasticbagStorage = (TextView) findViewById(R.id.plasticbagCanStorageNon);
    }

    public void btnNonRegistUpdateStorage(View view) {
        new nonRegistTrashcanTask().execute();
    }

    public void btnNonRegistBackMain(View view) {
        Intent intent = new Intent(NonRegistTrashcan.this,MainActivity.class);
        startActivity(intent);
    }

    class nonRegistTrashcanTask extends AsyncTask<Void, Void,Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            while (i !=22){
                Request request = new Request.Builder()
                        .url("http://140.125.207.230:8080/api/machine_storage/garbage_type/"+i)
                        .build();
                try (Response response = client.newCall(request).execute()) {
                    if (response.code() == 200) {
                        JSONArray jsonArray = new JSONArray(response.body().string());
                        value = jsonArray.toString();
                        i++;
                    }

                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        protected void onPostExecute(Void voids) {
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