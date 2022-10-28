package com.example.sa.Prototype;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class imagePrototype implements Cloneable{
    private Bitmap image;

    public void setImage(Bitmap image){
        this.image = image;
    }
    public Bitmap getImage(){
        return image;
    }
    public Object clone() throws CloneNotSupportedException{
        return (imagePrototype) super.clone();
    }
}
