package com.example.sa.Observer;

import android.widget.TextView;

import com.example.sa.NonRegistTrashcan;
import com.example.sa.R;
import com.example.sa.RegistTrashcan;

public class IronGarbage implements GarbageCan {
    TextView textView;
    String CallClass;
    public void Update(){

        if (CallClass == "RegistTrashCan"){
            textView.setText(RegistTrashcan.paperStorage_String);
        }
        else{
            textView.setText(NonRegistTrashcan.paperStorage_String);
        }
//        textView.setText(RegistTrashcan.ironStorage_String);
//        textView.setText(NonRegistTrashcan.ironStorage_String);
    }
    public void setTextView(TextView textView){
        this.textView = textView;
    }
    public void setCallClass(String callClass){this.CallClass = callClass;}
}
