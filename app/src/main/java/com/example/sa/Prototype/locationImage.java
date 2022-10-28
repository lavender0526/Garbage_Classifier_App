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
    Bitmap photo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_image);

        intent = getIntent();
        photo= intent.getParcelableExtra("photo");
        prototype.setImage(photo);
        imageview = (ImageView) findViewById(R.id.imageView22);
        imageview.setImageBitmap(prototype.getImage());
        System.out.println(prototype.clone());
    }
}