package com.example.sa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.sa.Iterator.ConcreteAggregate;
import com.example.sa.Iterator.Iterator;
import com.example.sa.Prototype.imagePrototype;
import com.example.sa.Prototype.locationImage;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NonRegistLocation extends AppCompatActivity {
    OkHttpClient client = new OkHttpClient();
    String value;
    int i = 0;
    public String selectedLocation;
    private String machineImage;
    ImageView machineImageView;
    ArrayList<String> getLocation = new ArrayList<String>();
    Bitmap decodedByte;
    ConcreteAggregate concreteAggregate = new ConcreteAggregate();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_non_regist_location);
        machineImageView = (ImageView) findViewById(R.id.locationImage);
        new nonRegistLocationTask().execute();
    }


    public void btnNonRegistLocationBackmain(View view) {
        Intent intent = new Intent(NonRegistLocation.this, MainActivity.class);
        startActivity(intent);
    }

    public void btnNonRegistGetLocation(View view) {
        new nonRegistLocationGetImageTask().execute();
    }

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
                        while (jsonArray.getJSONObject(i).getString("location")!=null) {
                            value = jsonArray.getJSONObject(i).getString("location");
                            concreteAggregate.add(value);
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
            Iterator  iterator = concreteAggregate.createIterator();
            if (result) {
                while (iterator.hasNext()) {
                    getLocation.add(iterator.next());
                    System.out.println(iterator.next());
                }
                setSpinner();
            }
        }
    }
    private void setSpinner() {
        Spinner mspinner = (Spinner) findViewById(R.id.nonRegistSelectedLocation);
        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, getLocation);
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

    class nonRegistLocationGetImageTask extends AsyncTask<Void, Void, Boolean> {
        @Override
        protected Boolean doInBackground(Void... voids) {
            Request request = new Request.Builder()
                    .url("http://140.125.207.230:8080/api/machines/location?location=" + selectedLocation)
                    .build();

            try (Response response = client.newCall(request).execute()) {
                if (response.code() == 200) {
                    JSONArray json = new JSONArray(response.body().string());
                    machineImage = json.getJSONObject(0).getString("machinePicture");
                    return true;
                }
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            return false;
        }

        protected void onPostExecute(Boolean result) {
            if (result) {
                byte[] decodedString = Base64.decode(machineImage, Base64.DEFAULT);
                decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                machineImageView.setImageBitmap(decodedByte);
            }
        }
    }
    public void imageZommin(View view) {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            decodedByte.compress(Bitmap.CompressFormat.PNG, 100, stream);
            imagePrototype prototype = new imagePrototype();
            prototype.setImage(selectedLocation,decodedByte);
            Intent intent = new Intent(NonRegistLocation.this, locationImage.class);
            intent.putExtra("location",selectedLocation);
            startActivity(intent);
    }
}