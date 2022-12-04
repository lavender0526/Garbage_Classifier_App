package com.example.sa.Flyweight;

import android.graphics.Bitmap;
import android.os.Handler;

import java.util.HashMap;

public class FlyweightFactory {
    private static Bitmap bitmap;

    public static final HashMap<String, Bitmap> ilist = new HashMap<>();

    public static Bitmap getImage(String location){

        if (ilist.get(location) == null){
            ConcreteLocation concreteLocation =new ConcreteLocation(location);
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    bitmap = concreteLocation.getImageFromback();
                    ilist.put(location,bitmap);
                }
            }, 3000);   //5 seconds


        }
        else {
            bitmap = ilist.get(location);
        }
        return bitmap;
    }
}
