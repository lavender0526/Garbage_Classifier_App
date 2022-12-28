package com.example.sa;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.sa.Observer.BottleGarbage;
import com.example.sa.Observer.ConcreteAttribute;
import com.example.sa.Observer.GarbageAttribute;
import com.example.sa.Observer.GarbageCan;
import com.example.sa.Observer.IronGarbage;
import com.example.sa.Observer.PaperGarbage;
import com.example.sa.Observer.PlasticGarbage;
import com.example.sa.Visitor.Switch;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RegistTrashcan extends AppCompatActivity{
    private String value;
    TextView bottleStorage,ironStorage,plasticbagStorage;
    public static String bottleStorage_String;
    public static String ironStorage_String;
    public static String plasticbagStorage_String;
    public static String paperStorage_String;
    private int i=18;
    OkHttpClient client = new OkHttpClient();
    static GarbageAttribute garbageAttribute = new ConcreteAttribute();
    GarbageCan plasticGarbage = new PlasticGarbage();
    GarbageCan ironGarbage = new IronGarbage();
    GarbageCan bottleGarbage = new BottleGarbage();
    GarbageCan paperGarbage = new PaperGarbage();


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist_trashcan);

        bottleGarbage.setTextView((TextView) findViewById(R.id.bottleCanStorage));
        ironGarbage.setTextView((TextView) findViewById(R.id.IronbottleCanStorage));
        plasticGarbage.setTextView((TextView) findViewById(R.id.plasticbagCanStorage));
        paperGarbage.setTextView((TextView) findViewById(R.id.paperCanStorage));
        bottleGarbage.setCallClass("RegistTrashCan");
        ironGarbage.setCallClass("RegistTrashCan");
        plasticGarbage.setCallClass("RegistTrashCan");
        paperGarbage.setCallClass("RegistTrashCan");
    }


    public void gotoHome(View view) {
        Switch aswitch = new Switch("RegistTrashcan",this);
        aswitch.activity();
    }
    public void gotoLocation(View view) {
        Switch aswitch = new Switch("RegistLocation",this);
        aswitch.activity();
    }
    public void gotoAccount(View view) {
        Switch aswitch = new Switch("RedgistrReviseAccount",this);
        aswitch.activity();
    }

    public void gotoConnect(View view) {
        Switch aswitch = new Switch("ConnectCan",this);
        aswitch.activity();
    }
    public void gotoMoney(View view) {
        Switch aswitch = new Switch("RedgistMoney",this);
        aswitch.activity();
    }

    public void btnTrashcanUpdateStorage(View view) {
        new registTrashcanTask().execute();
        garbageAttribute.Create();
        garbageAttribute.Attach(plasticGarbage);
        garbageAttribute.Attach(ironGarbage);
        garbageAttribute.Attach(bottleGarbage);
        garbageAttribute.Attach(paperGarbage);
        garbageAttribute.Notify();
    }


    class registTrashcanTask extends AsyncTask<Void, Void,Boolean> {
        @Override
        protected Boolean doInBackground(Void... voids) {
            while (i !=22){
                Request request = new Request.Builder()
                        .url("http://140.125.207.230:8080/api/machine_storage/garbage_type/"+i)
                        .build();
                try (Response response = client.newCall(request).execute()) {
                    if (response.code() == 200) {
                        JSONArray jsonArray = new JSONArray(response.body().string());
                        value = jsonArray.toString();
                        onPostExecute();
                        i++;
                    }

                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        protected void onPostExecute() {
            if(i==18){
                bottleStorage_String = (value+"%");
            }else if(i==19){
                ironStorage_String = (value+"%");
            }else if(i==21){
                plasticbagStorage_String = (value+"%");
            }
            else if( i == 20){
                paperStorage_String = (value + "%");
            }
        }
    }

}
