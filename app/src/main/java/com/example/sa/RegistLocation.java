package com.example.sa;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sa.Flyweight.FlyweightFactory;
import com.example.sa.Flyweight.locationImage;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
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
    int i = 0;
    String getLocation;
    private String selectedLocation;
    ArrayList<String> setLocation = new ArrayList<String>();
    ArrayList<String> location = new ArrayList<>();
    ImageView imageView ;
    Bitmap bitmap;
    FlyweightFactory factory;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist_location);
        imageView = (ImageView)findViewById(R.id.machineImageView);
        new getMachineLocationTask().execute();
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
                        while (i < jsonArray.length()) {
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


    public void btnGetLocationImage(View view){
        bitmap = factory.getImage(selectedLocation);
        imageView.setImageBitmap(bitmap);
    }
    public void zoomIn(View view) {
        Intent intent = new Intent(RegistLocation.this, locationImage.class);
        intent.putExtra("location",selectedLocation);
        System.out.println(selectedLocation);
        startActivity(intent);
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
        Intent intent = new Intent(RegistLocation.this,RedgistMoney.class);
        startActivity(intent);
    }
    public void btnRegistLocationSetting(View view) {
        Intent intent = new Intent(RegistLocation.this,Setting.class);
        startActivity(intent);
    }
}
