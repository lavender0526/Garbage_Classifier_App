package com.example.sa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button btnLogin,btnRegister,btnTrashcan;
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

    }

    public void btnLocation(View view) {
        Intent intent = new Intent(MainActivity.this,NonRegistLocation.class);
        startActivity(intent);
    }
}