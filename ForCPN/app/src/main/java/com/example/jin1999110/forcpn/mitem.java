package com.example.jin1999110.forcpn;

import org.litepal.crud.LitePalSupport;

/**
 * Created by jin1999110 on 2019/3/19.
 */

public class mitem extends LitePalSupport {
    private int normal;
    private String Date;
    private String result;

    public mitem(int I,String D){
        normal=I;
        Date=D;
    }


    public void setNormal(int I){
        normal=I;
    }
    public int getNormal(){
        return normal;
    }
    public void setDate(String D){
        Date=D;
    }
    public String getDate(){
        return Date;
    }
    public void setResult(String D){
        result=D;
    }
    public String getResult(){
        return result;
    }
}
