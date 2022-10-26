package com.example.sa.Observer;

import android.widget.TextView;

import com.example.sa.R;
import com.example.sa.RegistTrashcan;

public class BottleGarbage implements GarbageCan {
    TextView textView;

    public void Update() {

        textView.setText(RegistTrashcan.bottleStorage_String);
    }

    public void setTextView(TextView textView) {
        this.textView = textView;
    }
}