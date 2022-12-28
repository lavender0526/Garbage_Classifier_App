package com.example.sa;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import com.example.sa.RegistLocation;
import com.example.sa.Bridge.CreateFont;
import com.example.sa.Bridge.Font;
import com.example.sa.Bridge.opensansvariablefontwdthwght;
import com.example.sa.Bridge.robotocondensedbold;
import com.example.sa.Bridge.robotocondenseditalic;
import com.example.sa.Bridge.robotocondensedregular;
import com.example.sa.Bridge.ubuntubold;
import com.example.sa.Bridge.ubuntuitalic;
import com.example.sa.Observer.BottleGarbage;
import com.example.sa.Observer.ConcreteAttribute;
import com.example.sa.Observer.GarbageAttribute;
import com.example.sa.Observer.GarbageCan;
import com.example.sa.Observer.IronGarbage;
import com.example.sa.Observer.PaperGarbage;
import com.example.sa.Observer.PlasticGarbage;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NonRegistTrashcan extends AppCompatActivity{
    private int i=18;
    public static String bottleStorage_String;
    public static String ironStorage_String;
    public static String plasticbagStorage_String;
    public static String paperStorage_String;
    private String value,str;
    TextView fontText1,fontText2;
    Spinner spinner;
    OkHttpClient client = new OkHttpClient();
    com.example.sa.Bridge.CreateFont CreateFont;
    Typeface typeface;
    Context context = this;
    Font createFont;
    GarbageAttribute garbageAttribute2 = new ConcreteAttribute();
    GarbageCan plasticGarbage2 = new PlasticGarbage();
    GarbageCan ironGarbage2 = new IronGarbage();
    GarbageCan bottleGarbage2 = new BottleGarbage();
    GarbageCan paperGarbage2 = new PaperGarbage();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_non_regist_trashcan);
        bottleGarbage2.setTextView((TextView) findViewById(R.id.bottleStorageNon));
        ironGarbage2.setTextView((TextView) findViewById(R.id.ironbottleCanStorageNon));
        plasticGarbage2.setTextView((TextView) findViewById(R.id.plasticbagCanStorageNon));
        paperGarbage2.setTextView((TextView) findViewById(R.id.paperCanStorage2));
        bottleGarbage2.setCallClass("0");
        ironGarbage2.setCallClass("0");
        plasticGarbage2.setCallClass("0");
        paperGarbage2.setCallClass("0");


        fontText1 = (TextView) findViewById(R.id.Text1);
        fontText2 = (TextView) findViewById(R.id.Text2);
        spinner = (Spinner) findViewById(R.id.spinner1);

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
        fontText1.setText("請選擇字體");
        switch (str){
            case "opensansvariablefontwdthwght":
                fontText2.setText("opensanswdthwght");
                createFont = new CreateFont(context,new opensansvariablefontwdthwght());
                fontText1.setTypeface(createFont.change());
                fontText2.setTypeface(createFont.change());

                break;
            case "robotocondensedbold":
                fontText2.setText("robotocondensedbold");
                createFont = new CreateFont(context,new robotocondensedbold());
                fontText1.setTypeface(createFont.change());
                fontText2.setTypeface(createFont.change());
                break;
            case "robotocondenseditalic":
                fontText2.setText("robotocondenseditalic");
                createFont = new CreateFont(context,new robotocondenseditalic());
                fontText1.setTypeface(createFont.change());
                fontText2.setTypeface(createFont.change());
                break;
            case "robotocondensedregular":
                fontText2.setText("robotocondensedregular");
                createFont = new CreateFont(context,new robotocondensedregular());
                fontText1.setTypeface(createFont.change());
                fontText2.setTypeface(createFont.change());
                break;
            case "ubuntubold":
                fontText2.setText("ubuntubold");
                createFont = new CreateFont(context,new ubuntubold());
                fontText1.setTypeface(createFont.change());
                fontText2.setTypeface(createFont.change());
                break;
            case "ubuntuitalic":
                fontText2.setText("ubuntuitalic");
                createFont = new CreateFont(context,new ubuntuitalic());
                fontText1.setTypeface(createFont.change());
                fontText2.setTypeface(createFont.change());
                break;
            default:
                fontText2.setText("opensanswdthwght");
                createFont = new CreateFont(context,new opensansvariablefontwdthwght());
                fontText1.setTypeface(createFont.change());
                fontText2.setTypeface(createFont.change());
    }}
    public void btnNonRegistUpdateStorage(View view) {
        new nonRegistTrashcanTask().execute();
        garbageAttribute2.Create();
        garbageAttribute2.Attach(plasticGarbage2);
        garbageAttribute2.Attach(ironGarbage2);
        garbageAttribute2.Attach(bottleGarbage2);
        garbageAttribute2.Attach(paperGarbage2);
        garbageAttribute2.Notify();
    }

    public void btnNonRegistBackMain(View view) {
        Intent intent = new Intent(NonRegistTrashcan.this,Home.class);
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
                bottleStorage_String = (value+"%");
            }else if(i==19){
                ironStorage_String = (value+"%");
            }else if(i==21){
                plasticbagStorage_String = (value+"%");
            }
            else if( i == 20){
                paperStorage_String = (value + "%");
                System.out.println("00");
                System.out.println(paperStorage_String);
                System.out.println("01");
            }

        }

    }


}
