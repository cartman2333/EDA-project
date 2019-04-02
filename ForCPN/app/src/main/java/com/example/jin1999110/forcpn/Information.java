package com.example.jin1999110.forcpn;

import org.litepal.crud.LitePalSupport;

import java.io.Serializable;

/**
 * Created by jin1999110 on 2018/12/14.
 */

public class Information extends LitePalSupport implements Serializable {
    private String type;
    private String Date;

    public Information(String I,String D){
        type=I;
        Date=D;
    }


    public void settype(String I){
        type=I;
    }
    public String gettype(){
        return type;
    }
    public void setDate(String D){
        Date=D;
    }
    public String getDate(){
        return Date;
    }

}
