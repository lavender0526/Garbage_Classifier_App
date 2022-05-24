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

public class NonRegistLocation extends AppCompatActivity {
    OkHttpClient client = new OkHttpClient();
    String value;
    int i=0;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_non_regist_location);
        ArrayList<String> location = new ArrayList<String>();
//        Spinner spinnerLocation = (Spinner) findViewById(R.id.spinnerNonRegist);
        Spinner mspinner = (Spinner) findViewById(R.id.spinnerNonRegist);
//        ArrayAdapter adapter= new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item,location );
//        spinnerLocation.setAdapter(adapter);
//        spinnerLocation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
//                String ss = (String) spinnerLocation.getSelectedItem();
//                System.out.println(ss);
//            }
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//                System.out.println("ERROR");
//            }
//        });
        class nonRegistLocationTask extends AsyncTask<Void, Void, Void> {
            @Override
            protected Void doInBackground(Void... voids) {
                Request request = new Request.Builder()
                        .url("http://140.125.207.230:8080/api/machines")
                        .build();

                try (Response response = client.newCall(request).execute()) {
                    if (response.code() == 200) {
                        JSONArray jsonArray = new JSONArray(response.body().string());
                        try {
                            while (i <= 3) {
                                value = jsonArray.getJSONObject(i).getString("location");
                                location.add(value);
                                i++;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }

                return null;
            }
        }
        new nonRegistLocationTask().execute();
        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, location);
        mspinner.setAdapter(adapter);
        mspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String ttext =  (String) mspinner.getSelectedItem();
                System.out.println(ttext);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                System.out.println("NN");
            }
        });    }
}