package com.example.sa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
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
    int i = 0;
    String getLocation, machineImage;
    private String selectedLocation;
    ArrayList<String> setLocation = new ArrayList<String>();
    ArrayList<String> location = new ArrayList<>();
    ImageView imageView = findViewById(R.id.machineImageView);

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist_location);
        new getMachineLocationTask().execute();
    }

    public void btnGetLocationImage(View view) {
        new registLocationGetImageTask().execute();
    }

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
                            getLocation = jsonArray.getJSONObject(i).getString("location");
                            location.add(getLocation);
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
            if (result) {
                int k = 0;
                while (k < location.size()) {
                    setLocation.add(location.get(k));
                    k++;
                }
                setSpinner();
            }
        }
    }

    private void setSpinner() {
        Spinner mspinner = (Spinner) findViewById(R.id.nonRegistSelectedLocation);
        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, setLocation);
        mspinner.setAdapter(adapter);
        mspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedLocation = (String) mspinner.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                System.out.println("NN");
            }
        });
    }

    class registLocationGetImageTask extends AsyncTask<Void, Void, Boolean> {
        @Override
        protected Boolean doInBackground(Void... voids) {
            Request request = new Request.Builder()
                    .url("http://140.125.207.230:8080/api/machines/location/" + selectedLocation)
                    .build();

            try (Response response = client.newCall(request).execute()) {
                if (response.code() == 200) {
                    System.out.println(response.code());
                    JSONObject json = new JSONObject(response.body().string());
                    machineImage = json.getString("machinePicture");
                    return true;
                }
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }

            return false;
        }

        protected void onPostExecute(Boolean result) {
            if (result) {
                System.out.println(machineImage);
            }
        }
    }
    public void btnRegistLocationHome(View view) {
        Intent intent = new Intent(RegistLocation.this,RegistTrashcan.class);
        startActivity(intent);
    }
    public void btnRegistLocationAccount(View view) {
        Intent intent = new Intent(RegistLocation.this,registerReviseAccount.class);
        startActivity(intent);
    }
    public void btnRegistLocationMoney(View view) {
        Intent intent = new Intent(RegistLocation.this,Money.class);
        startActivity(intent);
    }
    public void btnRegistLocationSetting(View view) {
        Intent intent = new Intent(RegistLocation.this,Setting.class);
        startActivity(intent);
    }
}
