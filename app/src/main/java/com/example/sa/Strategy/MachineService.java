package com.example.sa.Strategy;

import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


public class MachineService extends AppCompatActivity implements Service{

    private Button button1,button2;

    public void getButton1 (Button button1){
        this.button1 = button1;
    }
    public void getButton2(Button button2){
        this.button2 = button2;
    }

    public void HandleProblem(String context){


        switch (context){
            case "You are asking about Machine":
                button1.setText("Machine was stuck by the garbage");

                button2.setText("Machine doesnt react");

            default:

        }
    }


}
