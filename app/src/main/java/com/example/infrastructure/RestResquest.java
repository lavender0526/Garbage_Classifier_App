package com.example.infrastructure;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

class RestRequest{
    OkHttpClient client = new OkHttpClient();
    RestRequest(){
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
    }
    Response post(String url, JSONObject jsonObject) throws IOException {
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(jsonObject.toString(), mediaType);
        Request request = new Request.Builder()
                .url("http://140.125.207.230:8080/"+url)
                .post(body)
                .build();
       Response response = this.client.newCall(request).execute();
            System.out.println(response.body().string());
            return  response;
    }
    Response get(String url) throws IOException {
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        Request request = new Request.Builder()
                .url("http://140.125.207.230:8080/"+url)
                .get()
                .build();
        Response response = this.client.newCall(request).execute();
        System.out.println(response.body().string());
        return  response;
    }
    Response put(String url, JSONObject jsonObject) throws IOException {
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(jsonObject.toString(), mediaType);
        Request request = new Request.Builder()
                .url("http://140.125.207.230:8080/"+url)
                .put(body)
                .build();
        Response response = this.client.newCall(request).execute();
        System.out.println(response.body().string());
        return  response;
    }
    Response patch(String url, JSONObject jsonObject) throws IOException {
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(jsonObject.toString(), mediaType);
        Request request = new Request.Builder()
                .url("http://140.125.207.230:8080/"+url)
                .patch(body)
                .build();
        Response response = this.client.newCall(request).execute();
        System.out.println(response.body().string());
        return  response;
    }
    Response delete(String url) throws IOException {
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        Request request = new Request.Builder()
                .url("http://140.125.207.230:8080/"+url)
                .delete()
                .build();
        Response response = this.client.newCall(request).execute();
        System.out.println(response.body().string());
        return  response;
    }
}