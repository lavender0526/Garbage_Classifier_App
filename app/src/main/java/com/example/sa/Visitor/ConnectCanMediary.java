package com.example.sa.Visitor;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ConnectCanMediary implements Page{


    @Override
    public void gotoPage(Context context, Class<?> tClass,String text) {
        Intent intent = new Intent(context.getApplicationContext(), tClass);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        Toast.makeText(context.getApplicationContext(),text, Toast.LENGTH_SHORT).show();
        context.getApplicationContext().startActivity(intent);
    }

    @Override
    public void accept(Visitor visoitor) {
         visoitor.visit(this);
    }
}
