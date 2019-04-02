package com.example.jin1999110.forcpn;

import java.util.List;

/**
 * ProjectName: NettyInAction
 *
 * @author xxl
 * <p>
 * Created by xxl on - 2018-12-28 22:20
 **/
public class FramePo {
    private String string;
    private byte [] bytes;


    public FramePo(String string, byte[] bytes) {
        this.string = string;
        this.bytes = bytes;
    }

    public String getString() {
        return string;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public FramePo() {
    }
}
