package com.example.sa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.sa.Iterator.ConcreteAggregate;
import com.example.sa.Iterator.Iterator;
import com.example.sa.Flyweight.FlyweightFactory;
import com.example.sa.Flyweight.locationImage;

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
    int i = 0;
    public String selectedLocation;
    ImageView machineImageView;
    ArrayList<String> getLocation = new ArrayList<String>();
    Bitmap bitmap;
    ConcreteAggregate concreteAggregate = new ConcreteAggregate();
    FlyweightFactory factory;

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
        bitmap = factory.getImage(selectedLocation);
        machineImageView.setImageBitmap(bitmap);
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

    public void zoomIn(View view) {
            Intent intent = new Intent(NonRegistLocation.this, locationImage.class);
            intent.putExtra("location",selectedLocation);
            System.out.println(selectedLocation);
            startActivity(intent);
    }
}