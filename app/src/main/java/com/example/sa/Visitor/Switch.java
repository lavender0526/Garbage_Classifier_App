package com.example.sa.Visitor;


import android.content.Context;

import com.example.sa.store.UserStore;

public class Switch {
    private String location;
    private Context context;
    private Page registTrashcanMediary = new RegistTrashcanMediary();
    private Page registLocationMediary = new RegistLocationMediary();
    private Page redgistMoneyMediary = new RedgistMoneyMediary();
    private Page connectCanMediary = new ConnectCanMediary();
    private Page redgistrReviseAccountMediary = new RedgistrReviseAccountMediary();
    public Switch(String location,Context context){
        this.context = context;
        this.location = location;
    }

    public void activity(){
        System.out.println("success");
        System.out.println(location);
        AuthVisitor guestVisitor = new GuestVisitor(context);
        AuthVisitor loginVisitor = new LoginVisitor(context);
        if(UserStore.userName == null){
            if(location == "RegistTrashcan"){
                registTrashcanMediary.accept(guestVisitor);
            }
            else if(location == "RegistLocation"){
                registLocationMediary.accept(guestVisitor);
            }
            else if(location == "RedgistMoney"){
                redgistMoneyMediary.accept(guestVisitor);
            }
            else if(location == "ConnectCan"){
                connectCanMediary.accept(guestVisitor);
            }
            else if(location == "RedgistrReviseAccount"){
                redgistrReviseAccountMediary.accept(guestVisitor);
            }
            else if(location == "NonRegistTrashcan"){
                registTrashcanMediary.accept(guestVisitor);
            }
            else{
                registLocationMediary.accept(guestVisitor);
            }
        }
        else{
            if(location == "RegistTrashcan"){
                registTrashcanMediary.accept(loginVisitor);
            }
            else if(location == "RegistLocation"){
                registLocationMediary.accept(loginVisitor);
            }
            else if(location == "RedgistMoney"){
                redgistMoneyMediary.accept(loginVisitor);
            }
            else if(location == "ConnectCan"){
                connectCanMediary.accept(loginVisitor);
            }
            else if(location == "RedgistrReviseAccount"){
                redgistrReviseAccountMediary.accept(loginVisitor);
            }
            else if(location == "NonRegistTrashcan"){
                registTrashcanMediary.accept(loginVisitor);
            }
            else{
                registLocationMediary.accept(loginVisitor);
            }
        }

    }
}
