package com.example.sa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RegistLocation extends AppCompatActivity {
    OkHttpClient client = new OkHttpClient();
    String value;
    int i=0;
    boolean temp = false;
    ArrayList<String> location = new ArrayList<String>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist_location);
        Spinner spinnerLocation = (Spinner) findViewById(R.id.spinner11);
        ArrayAdapter spinnerArrayAdapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item,location );
        spinnerLocation.setAdapter(spinnerArrayAdapter);
        class registLocationTask extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {
                Request request = new Request.Builder()
                    .url("http://140.125.207.230:8080/api/machines")
                    .build();

                try (Response response = client.newCall(request).execute()) {
                    if (response.code() == 200) {
                        JSONArray jsonArray = new JSONArray(response.body().string());
                        try {
                            while (true){
                                value =jsonArray.getJSONObject(i).getString("location");
                                location.add(value);
                                System.out.println(i);
                                System.out.println(location);
                                i++;
                            }
                        }
                        catch (JSONException e){
                            e.printStackTrace();


//                            spinnerArrayAdapter.setAdapter
//                            spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_value);
//                            spinnerLocation.setAdapter(spinnerArrayAdapter);
                        }


                    }
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }

                return null;
            }
        }
        new registLocationTask().execute();
    }
    public void putLocation(){
//        spinnerLocation.setAdapter(spinnerArrayAdapter);
//        spinnerLocation.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);
    }
}