package com.example.jin1999110.forcpn;

import org.litepal.crud.LitePalSupport;

import java.io.Serializable;

/**
 * Created by jin1999110 on 2018/12/25.
 */

public class register extends LitePalSupport implements Serializable {
    private String address;
    private String Data;

    public register(String I,String D){
        address=I;
        Data=D;
    }


    public void setAddress(String I){
        address=I;
    }
    public String getAddress(){
        return address;
    }
    public void setData(String D){
        Data=D;
    }
    public String getData(){
        return Data;
    }

}
