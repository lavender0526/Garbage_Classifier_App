package com.example.sa.Proxy;

import android.util.Log;

import com.example.sa.store.UserStore;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class WalletServiceImpl implements WalletService{

    private OkHttpClient client = new OkHttpClient();
    @Override
    public JSONObject getWalletInfo() {
        //TODO:getUsername from singleton Pattern
        String username = UserStore.getInstance().getUsername();
        Log.d("INFO",username);
        Request request = new Request.Builder()
                .url("http://140.125.207.230:8080/api/back_acct/username/"+username)
                .build();

        try (Response response = client.newCall(request).execute()) {
            Log.d("INFO", String.valueOf(response.code()));
            if(response.code() == 200){
                JSONObject jsonObject = new JSONObject(response.body().string());
                return jsonObject;
            }

        } catch (IOException|JSONException e) {
            e.printStackTrace();
        }
        return null;
    }


    public Boolean createBankAcct(String accountCode,String bankType) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("account_code", accountCode);
        jsonObject.put("bank_type", bankType);
        jsonObject.put("user", UserStore.getInstance().getUserId());//TODO: Get From Singleton Pattern
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(jsonObject.toString(), mediaType);
        Request request = new Request.Builder()
                .url("http://140.125.207.230:8080/api/bank_acct/")
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if(response.code() == 200 || response.code() ==201){
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public JSONObject updateWalletInfo(String username) {
        Request request = new Request.Builder()
                .url("http://140.125.207.230:8080/api/walletInfo/"+username)
                .build();

        try (Response response = client.newCall(request).execute()) {
            Log.d("INFO", String.valueOf(response.code()));
            if (response.code() == 200) {
                JSONArray jsonArray = new JSONArray(response.body().string());
                JSONObject jsonObject = null;
                Log.d("INFO",jsonArray.toString());
                for (int i = 0; i < jsonArray.length(); i++) {
                    jsonObject =jsonArray.getJSONObject(0);
                    Log.d("INFO",jsonObject.toString());
                }
                return jsonObject;
            }

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

}
