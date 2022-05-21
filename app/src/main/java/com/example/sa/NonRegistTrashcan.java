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
    private Button btnUpdate,btnBackmain;
    int i=18;
    private String value;
    OkHttpClient client = new OkHttpClient();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_non_regist_trashcan);
        TextView bottleStorage = (TextView) findViewById(R.id.plasticCanStorageNon);
        TextView ironStorage = (TextView) findViewById(R.id.ironbottleCanStorageNon);
        TextView plasticbagStorage = (TextView) findViewById(R.id.plasticbagCanStorageNon);
        btnUpdate=findViewById(R.id.btnNonRegistTrashcanUpdate);
        btnBackmain=findViewById(R.id.btnNonRegistTrashcanBackmain);
        btnBackmain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NonRegistTrashcan.this,MainActivity.class);
                startActivity(intent);
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                class nonRegistTrashcanTask extends AsyncTask<Void, Void,Boolean> {
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
                new nonRegistTrashcanTask().execute();
            }
        });
    }
}