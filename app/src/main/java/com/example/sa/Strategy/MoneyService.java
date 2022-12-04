package com.example.sa.Strategy;

import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MoneyService extends AppCompatActivity implements Service{
    private String context;
    private Button button1,button2;
    public void getButton1 (Button button1){
        this.button1 = button1;
    }
    public void getButton2(Button button2){
        this.button2 = button2;
    }
    public void HandleProblem(String context){
        switch (context){
            case "You are asking about Money":
                button1.setText("I didnt receive my feedback money");
                button2.setText("When can I receive my money ");
            default:

        }
    }
}
