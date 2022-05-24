package com.example.sa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
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
    ArrayList<String> location = new ArrayList<String>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist_location);
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
                            while (i<=3){
                                value =jsonArray.getJSONObject(i).getString("location");
                                location.add(value);
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
        Spinner spinnerLocation = (Spinner) findViewById(R.id.spinnerNonRegist);
        ArrayAdapter spinnerArrayAdapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item,location );
        spinnerLocation.setAdapter(spinnerArrayAdapter);
        spinnerLocation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
//                String ss = (String) spinnerLocation.getSelectedItem();
                System.out.println("ss");
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                System.out.println("ERROR");
            }

        });
    }
}