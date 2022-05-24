package com.example.sa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RegistLocation extends AppCompatActivity {
    OkHttpClient client = new OkHttpClient();
    int i=0;
    String value;
    JSONObject value2;
    private String selectedLocation;
    ArrayList<String> getLocation = new ArrayList<String>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist_location);
        ArrayList<String> location = new ArrayList<>();
        class getMachineLocationTask extends AsyncTask<Void, Void, Boolean> {
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
        new getMachineLocationTask().execute();
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
//    public void btnGetLocationImage(View view) {
//        class getMachineLocationTask extends AsyncTask<Void, Void, Void> {
//            @Override
//            protected Void doInBackground(Void... voids) {
//                JSONObject jsonObject = new JSONObject();
//                try {
//                    jsonObject.put(selectedLocation);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                Request request = new Request.Builder()
//                        .url("http://140.125.207.230:8080/api/machines/location/")
//                        .put(body)
//                        .build();
//
//                try (Response response = client.newCall(request).execute()) {
//                    System.out.println(response.code());
//                    if (response.code() == 200) {
//                        JSONObject jsonArray = new JSONObject(response.body().string());
//                        try {
//                            value2 = jsonArray.getJSONObject("machinePicture");
//                            System.out.println(value2);
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                } catch (IOException | JSONException e) {
//                    e.printStackTrace();
//                }
//
//                return null;
//            }
//        }
//        new getMachineLocationTask().execute();
//
//    }
//    public void btnRegistLocationSetting(View view) {
//        Intent intent = new Intent(RegistLocation.this,Setting.class);
//        startActivity(intent);
//    }
//    public void btnRegistLocationHome(View view) {
//        Intent intent = new Intent(RegistLocation.this,MainActivity.class);
//        startActivity(intent);
//    }
//    public void btnRegistLocationAccount(View view) {
//        Intent intent = new Intent(RegistLocation.this,registerReviseAccount.class);
//        startActivity(intent);
//    }
//    public void btnRegistLocationMoney(View view) {
//        Intent intent = new Intent(RegistLocation.this,Money.class);
//        startActivity(intent);
//    }
//

}