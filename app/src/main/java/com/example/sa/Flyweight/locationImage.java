package com.example.sa.Flyweight;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;


import com.example.sa.R;

public class locationImage extends AppCompatActivity{
    ImageView imageview;
    Bitmap photo;
    String location;
    FlyweightFactory factory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_image);
        Intent intent = getIntent();
        location = intent.getStringExtra("location");
        imageview = (ImageView) findViewById(R.id.imageView22);
        imageview.setImageBitmap(setPhoto());

    }
    public Bitmap setPhoto(){
        photo =factory.getImage(location);
        return photo;
    }

}