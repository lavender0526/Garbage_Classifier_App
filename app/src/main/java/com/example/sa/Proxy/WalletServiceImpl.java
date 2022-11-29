package com.example.sa.Proxy;

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
    public void getWalletInfo() {
        //TODO:getUsername from singleton Pattern
        String username=UserInfo.getUsername();
        Request request = new Request.Builder()
                .url("http://140.125.207.230:8080/api/bank_acct/username/"+username)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if(response.code() == 200){
                JSONObject jsonObject = new JSONObject(response.body().string());
                String bankName=jsonObject.getJSONObject("bank_type").getString("bank_name");
                String bankCode=jsonObject.getJSONObject("bank_type").getString("bank_code");
                String accountCode=jsonObject.getString("account_code");
                double walletValue=jsonObject.getJSONObject("wallet").getDouble("change_value");
            }

        } catch (IOException|JSONException e) {
            e.printStackTrace();
        }
    }


    public Boolean createBankAcct(String accountCode,String bankType) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("account_code", accountCode);
        jsonObject.put("bank_type", bankType);
        jsonObject.put("user", UserInfo.getUserId());//TODO: Get From Singleton Pattern
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

}
