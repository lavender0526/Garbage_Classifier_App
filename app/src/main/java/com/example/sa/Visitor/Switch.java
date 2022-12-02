package com.example.sa.Visitor;


import com.example.sa.ConnectCan;
import com.example.sa.NonRegistLocation;
import com.example.sa.NonRegistTrashcan;
import com.example.sa.RedgistMoney;
import com.example.sa.RegistLocation;
import com.example.sa.RegistTrashcan;

public class Switch {
    private String location;
    private Page RegistTrashcan = new RegistTrashcan();
    private Page RegistLocation = new RegistLocation();
    private Page RedgistMoney = new RedgistMoney();
    private Page ConnectCan = new ConnectCan();
    private Page NonRegistTrashcan = new NonRegistTrashcan();
    private Page NonRegistLocation = new NonRegistLocation();
    public Switch(String location){
        this.location = location;
    }

    public boolean activity(){
        System.out.println("success");
        System.out.println(location);
        Visitor authorityVisitor = new AuthorityVisitor();
        if(location == "RegistTrashcan"){
            return RegistTrashcan.accept(authorityVisitor);
        }
        else if(location == "RegistLocation"){
            return RegistLocation.accept(authorityVisitor);
        }
        else if(location == "RedgistMoney"){
            return RedgistMoney.accept(authorityVisitor);
        }
        else if(location == "ConnectCan"){
            return ConnectCan.accept(authorityVisitor);
        }
        else if(location == "NonRegistTrashcan"){
            return NonRegistTrashcan.accept(authorityVisitor);
        }
        else{
            return NonRegistLocation.accept(authorityVisitor);
        }

    }
}
