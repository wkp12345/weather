package com.example.weather;

import java.util.ArrayList;

public class city {
    private String fid;  //城市id
    private String parentid;  //上一级城市的id
    private String name;
    public ArrayList<city> list = new ArrayList<>();
    public void setFid(String f){
        this.fid=f;
    }
    public String getFid(){
        return this.fid;
    }
    public void setParentid(String p){
        this.parentid=p;
    }
    public String getParentid(){
        return this.parentid;
    }
    public void setName(String n){
        this.name=n;
    }
    public String getName(){
        return this.name;
    }
}
