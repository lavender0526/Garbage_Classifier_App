package com.example.sa.ChainOfResponsibility;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.UserHandle;
import android.view.Display;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.sa.LoginActivity;
import com.example.sa.R;
import com.example.sa.RegistTrashcan;
import com.example.sa.store.UserStore;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.Response;

public class http_is_Successful  implements httpNum{

    private httpNum nextInHttp;





    @Override
    public void setNexthttp(httpNum nexthttp) {

        this.nextInHttp = nexthttp;

    }






    public void httpstate(Numbers request) {


        System.out.println(request.getResponse().code());
        if (request.getResponse().code() >= 200 && request.getResponse().code() < 300 && request.getResponse().code() != 204 ) {
            System.out.println("OK");
            UserStore userStore = new UserStore();
            userStore.setUserName("testname");
            Intent intent = new Intent(request.getContext().getApplicationContext(), RegistTrashcan.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            request.getContext().getApplicationContext().startActivity(intent);
        } else {
            nextInHttp.httpstate(request);
        }


    }


}

