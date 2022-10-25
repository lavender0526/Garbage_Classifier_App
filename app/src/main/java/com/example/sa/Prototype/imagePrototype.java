package com.example.sa.Prototype;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class imagePrototype implements Cloneable{
    private Bitmap image;

    public Bitmap setImage(Bitmap image){
        this.image = image;
        return image;
    }
    public Bitmap getImage(){
        return image;//這裡get過去就變null了
    }
}
