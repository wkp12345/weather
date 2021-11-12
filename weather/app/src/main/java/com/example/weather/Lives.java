package com.example.weather;

/**
 * Created by 旋风小伙 on 2017/4/27.
 */

public class Lives {
    private String province;  //省

    private String city;   //市

    private String adcode;  //编码

    private String weather;  //天气

    private String temperature;  //温度

    private String humidity;  //湿度

    private String reporttime;  //更新时间

    public void setProvince(String province){
        this.province = province;
    }

    public String getProvince(){
        return this.province;
    }

    public void setCity(String city){
        this.city = city;
    }

    public String getCity(){
        return this.city;
    }

    public void setAdcode(String adcode){
        this.adcode = adcode;
    }

    public String getAdcode(){
        return this.adcode;
    }

    public void setWeather(String weather){
        this.weather = weather;
    }

    public String getWeather(){
        return this.weather;
    }

    public void setTemperature(String temperature){
        this.temperature = temperature;
    }

    public String getTemperature(){
        return this.temperature;
    }

    public void setHumidity(String humidity){
        this.humidity = humidity;
    }

    public String getHumidity(){
        return this.humidity;
    }

    public void setReporttime(String reporttime){
        this.reporttime = reporttime;
    }

    public String getReporttime(){
        return this.reporttime;
    }
}
