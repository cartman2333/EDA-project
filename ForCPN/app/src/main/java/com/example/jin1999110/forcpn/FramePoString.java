package com.example.jin1999110.forcpn;

import android.graphics.Color;
import android.text.SpannableString;
import android.text.style.BackgroundColorSpan;

/**
 * Created by jin1999110 on 2018/12/28.
 */

public class FramePoString {
    private static int [] lengths = new int[]{
           18,1,1,2,2
    };
    private static BackgroundColorSpan[] spans = new BackgroundColorSpan[]{
            new BackgroundColorSpan(Color.parseColor("#19CAAD")),new BackgroundColorSpan(Color.parseColor("#D6D5B7")),
            new BackgroundColorSpan(Color.parseColor("#8CC7B5")), new BackgroundColorSpan(Color.parseColor("#D1BA74")),
            new BackgroundColorSpan(Color.parseColor("#A0EEE1")),new BackgroundColorSpan(Color.parseColor("#E6CEAC")),
            new BackgroundColorSpan(Color.parseColor("#BEE7E9")),new BackgroundColorSpan(Color.parseColor("#ECAD6E")),
            new BackgroundColorSpan(Color.parseColor("#BEEDC7")),new BackgroundColorSpan(Color.parseColor("#F4606C")),
            new BackgroundColorSpan(Color.parseColor("#19CAAD")),new BackgroundColorSpan(Color.parseColor("#D6D5B7")),
            new BackgroundColorSpan(Color.parseColor("#8CC7B5"))
    };
    public static SpannableString getPoString(String string){
        SpannableString spannableString = new SpannableString(string);
        int start=0 ,end =0;
        for(int i=0;i<=4;i++){
            start = end;
            if (i==4)
                end = spannableString.length();
            else
                end+=lengths[i];
            spannableString.setSpan(spans[i],start,end,SpannableString.SPAN_INCLUSIVE_EXCLUSIVE);
        }
        return spannableString;
    }
}
