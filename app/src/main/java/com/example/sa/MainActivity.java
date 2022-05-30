package com.example.sa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.sa.Firebase.FCMService;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {
    private Button btnLogin,btnRegister,btnTrashcan,btnLocation;
    public static final String TAG = FCMService.TAG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLogin=findViewById(R.id.btnMainLogin);
        btnRegister=findViewById(R.id.btnMainRegister);
        btnTrashcan=findViewById(R.id.btnMainTrashcan);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
        btnTrashcan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,NonRegistTrashcan.class);
                startActivity(intent);
            }
        });

        FirebaseMessaging.getInstance().subscribeToTopic("news");
        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener<String>() {
            @Override
            public void onComplete(@NonNull Task<String> task) {
                if (!task.isSuccessful())return;
                String token = task.getResult();
                Log.d(TAG, "onComplete: "+token);
            }
        });





    }

    public void btnLocation(View view) {
        Intent intent = new Intent(MainActivity.this,NonRegistLocation.class);
        startActivity(intent);
    }





}