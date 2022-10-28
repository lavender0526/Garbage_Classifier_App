package com.example.sa.Prototype;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.example.sa.NonRegistLocation;
import com.example.sa.R;

public class locationImage extends AppCompatActivity{
    imagePrototype prototype = new imagePrototype();
    ImageView imageview;
    Intent intent;
    String location;
    Bitmap photo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_image);

        intent = getIntent();
        location= intent.getStringExtra("location");
        prototype.getlocation(location);
        imageview = (ImageView) findViewById(R.id.imageView22);
        try {
            photo = (Bitmap) prototype.clone();
            imageview.setImageBitmap(photo);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }
}