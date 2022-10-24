package com.example.sa;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NonRegistLocation extends AppCompatActivity {
    OkHttpClient client = new OkHttpClient();
    String value;
    int i = 0, k = 0;
    private String selectedLocation;
    private String machineImage;
    ImageView machineImageView;
    ArrayList<String> getLocation = new ArrayList<String>();
    ArrayList<String> location = new ArrayList<String>();

    //prototype
    Bitmap bp = null;
    float scaleWidth;
    float scaleHeight;
    int h;
    boolean num = false;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_non_regist_location);
        machineImageView = (ImageView) findViewById(R.id.nonRegistMachineImageView);

        //Prototype
        //create 矩陣
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        bp=BitmapFactory.decodeResource(getResources().R.drawable.nonRegistMachineImageView);
        int width = bp.getWidth();
        int height = bp.getHeight();
        int w = dm.widthPixels;//get screen width
        int h = dm.heightPixels;//get screen height
        scaleHeight = ((float)h)/height;
        scaleWidth = ((float)w)/width;
        machineImageView.setImageBitmap(bp);
        new nonRegistLocationTask().execute();
    }

    public boolean onTouchEvent(MotionEvent event){
        switch (event.getAction()){
            //check first touch in the screen will trigger it
            case MotionEvent.ACTION_DOWN:
                if (num==true) {
                    Matrix matrix = new Matrix();
                    matrix.postScale(scaleWidth, scaleHeight);
                    Bitmap newBitmap = Bitmap.createBitmap(bp,0,0,bp.getWidth(),bp.getHeight(),matrix,true);
                    machineImageView.setImageBitmap(newBitmap);
                    num = false;
                }else{
                    Matrix matrix = new Matrix();
                    matrix.postScale(1.0f,1.0f);
                    Bitmap newBitmap = Bitmap.createBitmap(bp,0,0,bp.getWidth(),bp.getHeight(),matrix,true);
                    machineImageView.setImageBitmap(newBitmap);
                    num=true;
                }
                break;
        }
        return super.onTouchEvent(event);
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
            if (result) {
                int k = 0;
                while (k < location.size()) {
                    getLocation.add(location.get(k));
                    k++;
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
                System.out.println(selectedLocation);
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
                Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                machineImageView.setImageBitmap(decodedByte);
            }
        }
    }
}