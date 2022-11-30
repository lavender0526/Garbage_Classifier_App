package com.example.sa.Proxy;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class UserInfo {
    private static int userId;
    private static String username = "FKTTim";
    private static String lastName;
    private static String firstName;
    private static String email;

    public static void setUserId(int userId) {
        UserInfo.userId = userId;
    }

    public static void setUsername(String username) {
        UserInfo.username = username;
    }

    public static void setLastName(String lastName) {
        UserInfo.lastName = lastName;
    }

    public static void setFirstName(String firstName) {
        UserInfo.firstName = firstName;
    }

    public static void setEmail(String email) {
        UserInfo.email = email;
    }

    public static int getUserId() {
        return userId;
    }


    public static String getUsername() {
        return username;
    }

    public static String getLastName() {
        return lastName;
    }

    public static String getFirstName() {
        return firstName;
    }
    public static String getEmail() {
        return email;
    }

    private static OkHttpClient client = new OkHttpClient();
    public static void getUserInfo(){
        Request request = new Request.Builder()
                .url("http://140.125.207.230:8080/api/userinfo/"+username)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if(response.code() == 200){
                JSONObject jsonObject = new JSONObject(response.body().string());
                userId=jsonObject.getInt("id");
                username=jsonObject.getString("userName");
                email=jsonObject.getString("email");
                lastName=jsonObject.getString("lastName");
                firstName=jsonObject.getString("name");
            }

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }
    public static void getUserInfo(String name){
        Request request = new Request.Builder()
                .url("http://140.125.207.230:8080/api/userinfo/"+name)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if(response.code() == 200){
                JSONObject jsonObject = new JSONObject(response.body().string());
                userId=jsonObject.getInt("id");
                username=jsonObject.getString("userName");
                email=jsonObject.getString("email");
                lastName=jsonObject.getString("lastName");
                firstName=jsonObject.getString("name");
            }

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }
}
