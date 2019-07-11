package com.example.tadeu17.dronetrack;

public class Globals{
    private static Globals instance;

    // Global variable
    private int user_id;
    private String name;
    private String location;
    // Restrict the constructor from being instantiated
    private Globals(){}

    public void setUserId(int d){
        this.user_id=d;
    }
    public int getUserId(){
        return this.user_id;
    }

    public void setLocation(String d){
        this.location=d;
    }
    public String getLocation(){
        return this.location;
    }

    public void setName(String d){
        this.name=d;
    }
    public String getName(){
        return this.name;
    }


    public static synchronized Globals getInstance(){
        if(instance==null){
            instance=new Globals();
        }
        return instance;
    }
}
