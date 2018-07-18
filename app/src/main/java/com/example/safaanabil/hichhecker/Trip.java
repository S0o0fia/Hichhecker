package com.example.safaanabil.hichhecker;

/**
 * Created by Safaa Nabil on 6/25/2018.
 */

public class Trip {

    private  String phone , price , date , fcity , tcity , time ;

    public Trip ()
    {

    }

    public  Trip (String phone ,String price , String date , String fcity , String tcity , String time)
    {
        this.phone = phone;
        this.price = price ;
        this.date = date ;
        this.fcity = fcity;
        this.tcity = tcity;
        this.time = time;
    }
     public  void setTime (String time)
     {
         this.time = time;
     }
    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public  void setPrice (String price)
    {
        this.price =price;
    }

    public void setDate (String date)
    {
        this.date = date;
    }

    public  void setFcity (String fcity)
    {
        this.fcity = fcity;
    }

    public void setTcity (String tcity)
    {
        this.tcity = tcity;
    }

    public String getPhone ()
    {
         return this.phone;
    }

    public  String getPrice ()
    {
        return this.price;

    }

    public String getDate ()
    {
        return this.date;

    }

    public String getFcity ()
    {
        return this.fcity;
    }

    public String getTcity ()
    {
        return this.tcity;
    }

     public  String getTime ()

     {
         return this.time;
     }

}
