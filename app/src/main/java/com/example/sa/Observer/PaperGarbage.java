package com.example.sa.Observer;

import android.widget.TextView;

import com.example.sa.RegistTrashcan;

public class PaperGarbage implements GarbageCan {
    TextView textView;

    public void Update() {

        textView.setText(RegistTrashcan.paperStorage_String);
    }

    public void setTextView(TextView textView) {
        this.textView = textView;
    }
}