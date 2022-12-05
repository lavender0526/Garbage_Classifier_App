package com.example.sa.Visitor;


import com.example.sa.store.UserStore;

public class Switch {
    private String location;
    private Page registTrashcanMediary = new RegistTrashcanMediary();
    private Page registLocationMediary = new RegistLocationMediary();
    private Page redgistMoneyMediary = new RedgistMoneyMediary();
    private Page connectCanMediary = new ConnectCanMediary();
    public Switch(String location){
        this.location = location;
    }

    public void activity(){
        System.out.println("success");
        System.out.println(location);
        Visitor guestVisitor = new GuestVisitor();
        Visitor loginVisitor = new LoginVisitor();
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
            else if(location == "NonRegistTrashcan"){
                registTrashcanMediary.accept(loginVisitor);
            }
            else{
                registLocationMediary.accept(loginVisitor);
            }
        }

    }
}
