package com.example.safaanabil.hichhecker;

/**
 * Created by Safaa Nabil on 6/21/2018.
 */

public class Resultinfo {


    private  String name, price ,  fcity , tcity ;

    public Resultinfo ()
    {

    }

    public Resultinfo (String name ,String price , String fcity , String tcity ) {
        this.price = price;
        this.fcity = fcity;
        this.tcity = tcity;

    }



    public  void setName (String name)
    {
        this.name = name;
    }
    public  void setPrice (String price)
    {
        this.price =price;
    }

    public  void setFcity (String fcity)
    {
        this.fcity = fcity;
    }

    public void setTcity (String tcity)
    {
        this.tcity = tcity;
    }



    public  String getPrice ()
    {
        return this.price;

    }


    public  String getName ()
    {
        return this.name;
    }
    public String getFcity ()
    {
        return this.fcity;
    }

    public String getTcity ()
    {
        return this.tcity;
    }


}
