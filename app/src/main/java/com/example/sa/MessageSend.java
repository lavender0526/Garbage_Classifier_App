package com.example.sa;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.sa.Strategy.MachineService;
import com.example.sa.Strategy.MoneyService;
import com.example.sa.Strategy.Service;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MessageSend {
    public RecyclerView chatsRV;
    public ImageButton sendMsgIB;
    public EditText userMsgEdt;
    private final String USER_KEY = "user";
    private final String BOT_KEY = "bot";
    private Button moneyBtn,machineBtn,backBtn;
    int state= 0;
    // creating a variable for
    // our volley request queue.
    private RequestQueue mRequestQueue;
    // creating a variable for array list and adapter class.
    private ArrayList<MessageModal> messageModalArrayList = new ArrayList<>();
    public MessageRVAdapter messageRVAdapter =  new MessageRVAdapter(messageModalArrayList, Contact.getMainActivity());;
    Service machineService = new MachineService();
    Service moneyService = new MoneyService();


    public void sendMessage(String userMsg) {

        // below line is to pass message to our
        // array list which is entered by the user.
        messageModalArrayList.add(new MessageModal(userMsg, USER_KEY));
        messageRVAdapter.notifyDataSetChanged();

        // url for our brain
        // make sure to add mshape for uid.
        // make sure to add your url.
//        String url = "https://api.brainshop.ai/get?bid=170792&key=h5EopavL9uKBVD8u&uid=[uid]&msg=[" + moneyBtn.getText().toString() + "]";
        String url = "http://api.brainshop.ai/get?bid=170792&key=h5EopavL9uKBVD8u&uid=uid&msg=" + userMsg;
        // creating a variable for our request queue.
        RequestQueue queue = Volley.newRequestQueue(Contact.getMainActivity());

        // on below line we are making a json object request for a get request and passing our url .
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    // in on response method we are extracting data
                    // from json response and adding this response to our array list.
                    String botResponse = response.getString("cnt");
                    moneyService.HandleProblem(botResponse);
                    machineService.HandleProblem(botResponse);

                    messageModalArrayList.add(new MessageModal(botResponse, BOT_KEY));

                    // notifying our adapter as data changed.
                    messageRVAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();

                    // handling error response from bot.
                    messageModalArrayList.add(new MessageModal("No response", BOT_KEY));
                    messageRVAdapter.notifyDataSetChanged();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error);
                // error handling.
                messageModalArrayList.add(new MessageModal("Sorry no response found", BOT_KEY));
                Toast.makeText(Contact.getMainActivity(), "No response from the bot..", Toast.LENGTH_SHORT).show();
            }
        });
        int MY_SOCKET_TIMEOUT_MS = 5000;
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy( MY_SOCKET_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        // at last adding json object
        // request to our queue.
        queue.add(jsonObjectRequest);



    }
}
