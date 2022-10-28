package com.example.sa.Prototype;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.ArrayList;
import java.util.HashMap;

public class imagePrototype implements Cloneable{
    private static HashMap<String,Bitmap> ilist = new HashMap<>();
    String location;
    public imagePrototype(){

    }

    public void setImage(String location,Bitmap image){
        ilist.put(location,image);
    }
    public void getlocation(String location){
        this.location = location;
    }

    public Object clone() throws CloneNotSupportedException{
        Bitmap temp = ilist.get(location);
        return  temp;
    }
}
