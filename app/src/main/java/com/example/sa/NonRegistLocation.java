package com.example.sa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
    int i=0,k=0;
    private String selectedLocation;
    ArrayList<String> getLocation = new ArrayList<String>();
    public void btnNonRegistLocationBackmain(View view) {
        Intent intent = new Intent(NonRegistLocation.this,MainActivity.class);
        startActivity(intent);
    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_non_regist_location);
        ArrayList<String> location = new ArrayList<String>();
        class nonRegistLocationTask extends AsyncTask<Void, Void, Boolean> {
            @Override
            protected Boolean doInBackground(Void... voids) {
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
                        return true;
                    }
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }

                return false;
            }
            protected void onPostExecute(Boolean result) {
                if(result){
                    int k=0;
                    while (k< location.size()){
                        getLocation.add(location.get(k));
                        k++;
                    }
                    setSpinner();
                }
            }
        }
        new nonRegistLocationTask().execute();
    }
    private void setSpinner() {
        Spinner mspinner = (Spinner) findViewById(R.id.nonRegistSelectedLocation);
        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, getLocation);
        mspinner.setAdapter(adapter);
        mspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedLocation =  (String) mspinner.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                System.out.println("NN");
            }
        });
    }


//    public void btnNonRegistGetLocation(View view) {
//        class nonRegistGetImageTask extends AsyncTask<Void, Void, Boolean> {
//            @Override
//            protected Boolean doInBackground(Void... voids) {
//                Request request = new Request.Builder()
//                        .url("http://140.125.207.230:8080/api/machines")
//                        .build();
//
//                try (Response response = client.newCall(request).execute()) {
//                    if (response.code() == 200) {
//                        JSONArray jsonArray = new JSONArray(response.body().string());
//                        try {
//                            while (i <= 3) {
//                                value = jsonArray.getJSONObject(i).getString("location");
//                                location.add(value);
//                                i++;
//                            }
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                        return true;
//                    }
//                } catch (IOException | JSONException e) {
//                    e.printStackTrace();
//                }
//
//                return false;
//            }
//            protected void onPostExecute(Boolean result) {
//                if(result){
//                    int k=0;
//                    while (k< location.size()){
//                        getLocation.add(location.get(k));
//                        k++;
//                    }
//                    setSpinner();
//                }
//            }
//        }
//        new nonRegistGetImageTask().execute();
//    }
}