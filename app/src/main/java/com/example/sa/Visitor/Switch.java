package com.example.sa.Visitor;


import android.content.Context;

import com.example.sa.store.UserStore;

public class Switch {
    private String location;
    private Context context;
    private static Page registTrashcanMiddleware = new RegistTrashcanMiddleware();
    private static Page registLocationMiddleware = new RegistLocationMiddleware();
    private static Page redgistMoneyMiddleware = new RedgistMoneyMiddleware();
    private static Page connectCanMiddleware = new ConnectCanMiddleware();
    private static Page redgistrReviseAccountMiddleware = new RedgistrReviseAccountMiddleware();
    public Switch(String location,Context context){
        this.context = context;
        this.location = location;
    }

    public void activity(){
        System.out.println("success");
        System.out.println(location);
        AuthVisitor authVisitor;

        if(UserStore.getInstance().getUsername() == null){
            authVisitor = new GuestVisitor(context);
        }
        else {
            authVisitor = new LoginVisitor(context);
        }

        if(location == "RegistTrashcan"){
            registTrashcanMiddleware.accept(authVisitor);
        }
        else if(location == "RegistLocation"){
            registLocationMiddleware.accept(authVisitor);
        }
        else if(location == "RedgistMoney"){
            redgistMoneyMiddleware.accept(authVisitor);
        }
        else if(location == "ConnectCan"){
            connectCanMiddleware.accept(authVisitor);
        }
        else if(location == "RedgistrReviseAccount"){
            redgistrReviseAccountMiddleware.accept(authVisitor);
        }
        else if(location == "NonRegistTrashcan"){
            registTrashcanMiddleware.accept(authVisitor);
        }
        else{
            registLocationMiddleware.accept(authVisitor);
        }

    }
}
