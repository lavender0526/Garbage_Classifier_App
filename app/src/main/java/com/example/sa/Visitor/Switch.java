package com.example.sa.Visitor;


public class Switch {
    private Page location;

    public Switch(Page location){
        this.location = location;
    }

    public boolean activity(){
        System.out.println("success");
        System.out.println(location);
        Visitor authorityVisitor = new AuthorityVisitor();
        return location.accept(authorityVisitor);

    }
}
