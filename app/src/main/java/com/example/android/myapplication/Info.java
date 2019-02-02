package com.example.android.myapplication;

public class Info {

    String name,pass,id,email;

    public Info(String name,String pass,String email,String id)
    {
        this.name= name;
        this.email=email;
       this.id=id;
        this.pass= pass;
    }

    public Info(){}


    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getId(){return id;}

    public String getPass() {
        return pass;
    }
}
