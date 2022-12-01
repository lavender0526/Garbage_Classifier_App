package com.example.sa.Visitor;


public class Switch {
    private boolean certificate;
    Page location;
    public Switch(boolean certificate,Page location){
        this.certificate = certificate;
        this.location = location;
    }

    public void activity(Page p){
        if(certificate){
            Visotor loginVisitor = new LoginVisitor();
            location.accept(loginVisitor,p);
        }
        else{
            Visotor guestVisitor = new GuestViitor();
            location.accept(guestVisitor,p);
        }
    }
}
