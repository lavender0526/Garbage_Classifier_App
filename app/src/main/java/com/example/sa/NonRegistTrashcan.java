package com.example.sa;

import static java.security.AccessController.getContext;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.sa.Bridge.CreateFont;
import com.example.sa.Bridge.opensansvariablefontwdthwght;
import com.example.sa.Bridge.robotocondensedbold;
import com.example.sa.Bridge.robotocondenseditalic;
import com.example.sa.Bridge.robotocondensedregular;
import com.example.sa.Bridge.ubuntubold;
import com.example.sa.Bridge.ubuntuitalic;
import com.example.sa.Bridge.ubunturegular;
import com.google.android.material.navigation.NavigationBarView;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NonRegistTrashcan extends AppCompatActivity{
    private int i=18;
    private String value,str;
    TextView bottleStorage,ironStorage,plasticbagStorage,fontText1,fontText2;
    Spinner spinner;
    OkHttpClient client = new OkHttpClient();
    com.example.sa.Bridge.CreateFont CreateFont;
    Typeface typeface;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_non_regist_trashcan);
        bottleStorage = (TextView) findViewById(R.id.plasticCanStorageNon);
        ironStorage = (TextView) findViewById(R.id.ironbottleCanStorageNon);
        plasticbagStorage = (TextView) findViewById(R.id.plasticbagCanStorageNon);
        fontText1 = (TextView) findViewById(R.id.Text1);
        fontText2 = (TextView) findViewById(R.id.Text2);
        spinner = (Spinner) findViewById(R.id.spinner1);

        typeface = ResourcesCompat.getFont(this, R.font.opensansvariablefontwdthwght);
//        typeface = Typeface.createFromAsset(getAssets(),"opensansvariablefontwdthwght.ttf");
        ArrayAdapter<CharSequence> adapter =
                ArrayAdapter.createFromResource(this,    //對應的Context
                        R.array.spinnerValue,                             //資料選項內容
                        android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setSelection(2, false);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                str = (String) spinner.getSelectedItem();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }

        });
    }
    public void BTNchangeFont(View view){
        switch (str){
            case "opensansvariablefontwdthwght":
                fontText1.setText("我想你");
                fontText1.setTypeface(typeface);
                CreateFont = new CreateFont(new opensansvariablefontwdthwght(),fontText1,fontText2);
                System.out.println(str);
                break;
            case "robotocondensedbold":
                CreateFont = new CreateFont(new robotocondensedbold(),fontText1,fontText2);
                System.out.println(str);
                break;
            case "robotocondenseditalic":
                CreateFont = new CreateFont(new robotocondenseditalic(),fontText1,fontText2);
                System.out.println(str);
                break;
            case "robotocondensedregular":
                CreateFont = new CreateFont(new robotocondensedregular(),fontText1,fontText2);
                System.out.println(str);
                break;
            case "ubuntubold":
                CreateFont = new CreateFont(new ubuntubold(),fontText1,fontText2);
                System.out.println(str);
                break;
            case "ubuntuitalic":
                CreateFont = new CreateFont(new ubuntuitalic(),fontText1,fontText2);
                System.out.println(str);
                break;
            default:
                CreateFont = new CreateFont(new ubunturegular(),fontText1,fontText2);
                System.out.println(str);
    }}
    public void btnNonRegistUpdateStorage(View view) {
        new nonRegistTrashcanTask().execute();
    }

    public void btnNonRegistBackMain(View view) {
        Intent intent = new Intent(NonRegistTrashcan.this,MainActivity.class);
        startActivity(intent);
    }

    class nonRegistTrashcanTask extends AsyncTask<Void, Void,Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            while (i !=22){
                Request request = new Request.Builder()
                        .url("http://140.125.207.230:8080/api/machine_storage/garbage_type/"+i)
                        .build();
                try (Response response = client.newCall(request).execute()) {
                    if (response.code() == 200) {
                        JSONArray jsonArray = new JSONArray(response.body().string());
                        value = jsonArray.toString();
                        i++;
                    }


                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        protected void onPostExecute(Void voids) {
            if(i==18){
                bottleStorage.setText(value+"%");
            }else if(i==19){
                ironStorage.setText(value+"%");
            }else if(i==21){
                plasticbagStorage.setText(value+"%");
            }
            else{
                System.out.println("11");
            }
        }

    }
}
