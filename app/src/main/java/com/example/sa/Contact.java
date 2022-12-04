package com.example.sa;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

public class Contact extends AppCompatActivity {

    private RecyclerView chatsRV;
    private ImageButton sendMsgIB;
    private EditText userMsgEdt;
    private final String USER_KEY = "user";
    private final String BOT_KEY = "bot";
    private Button moneyBtn,machineBtn,backBtn;
    int state= 0;
    // creating a variable for
    // our volley request queue.
    private RequestQueue mRequestQueue;
    // creating a variable for array list and adapter class.
    private ArrayList<MessageModal> messageModalArrayList;
    private MessageRVAdapter messageRVAdapter;
//    Service machineService = new MachineService();
//    Service moneyService = new MoneyService();
    MessageSend messageSend = new MessageSend();
    private static Context context;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        Contact.context = getApplicationContext();

        //button binding
        moneyBtn = findViewById(R.id.moneyBtn);
        machineBtn = findViewById(R.id.machineBtn);
        backBtn = findViewById(R.id.backBtn);
        //button onclickListener

        moneyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                messageSend.sendMessage(moneyBtn.getText().toString());
                messageSend.messageRVAdapter.notifyDataSetChanged();
                messageSend.moneyService.getButton1(moneyBtn);
                messageSend.moneyService.getButton2(machineBtn);
                state++;
            }
        });
        machineBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                messageSend.sendMessage(machineBtn.getText().toString());
                messageSend.messageRVAdapter.notifyDataSetChanged();
                messageSend.machineService.getButton1(moneyBtn);
                messageSend.machineService.getButton2(machineBtn);
                state++;
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(state !=0){
                    machineBtn.setText("Machine");
                    machineBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            messageSend.sendMessage(machineBtn.getText().toString());
                            messageSend.messageRVAdapter.notifyDataSetChanged();
                            messageSend.machineService.getButton1(moneyBtn);
                            messageSend.machineService.getButton2(machineBtn);
                            state++;
                        }
                    });
                    moneyBtn.setText("Money");
                    moneyBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            messageSend.sendMessage(moneyBtn.getText().toString());
                            messageSend.messageRVAdapter.notifyDataSetChanged();
                            messageSend.moneyService.getButton1(moneyBtn);
                            messageSend.moneyService.getButton2(machineBtn);
                            state++;
                        }
                    });
                }
                state = 0;
            }
        });
        // on below line we are initializing all our views.
        chatsRV = findViewById(R.id.idRVChats);
        sendMsgIB = findViewById(R.id.idIBSend);
        userMsgEdt = findViewById(R.id.idEdtMessage);

        // below line is to initialize our request queue.
        mRequestQueue = Volley.newRequestQueue(Contact.getMainActivity());
        mRequestQueue.getCache().clear();

        // creating a new array list
//        messageModalArrayList = new ArrayList<>();

        // adding on click listener for send message button.
        sendMsgIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // checking if the message entered
                // by user is empty or not.
                if (userMsgEdt.getText().toString().isEmpty()) {
                    // if the edit text is empty display a toast message.
                    Toast.makeText(Contact.getMainActivity(), "Please enter your message..", Toast.LENGTH_SHORT).show();
                    return;
                }

                // calling a method to send message
                // to our bot to get response.
                messageSend.sendMessage(userMsgEdt.getText().toString());

                // below line we are setting text in our edit text as empty
                userMsgEdt.setText("");
            }
        });

        // on below line we are initialing our adapter class and passing our array list to it.
        messageRVAdapter = new MessageRVAdapter(messageModalArrayList, this);

        // below line we are creating a variable for our linear layout manager.
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Contact.getMainActivity(), RecyclerView.VERTICAL, false);

        // below line is to set layout
        // manager to our recycler view.
        chatsRV.setLayoutManager(linearLayoutManager);

        // below line we are setting
        // adapter to our recycler view.
        chatsRV.setAdapter(messageSend.messageRVAdapter);

    }

    public static Context getMainActivity(){
        return Contact.context;
    }


}