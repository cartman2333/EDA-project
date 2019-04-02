package com.example.jin1999110.forcpn;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        getWindow().setStatusBarColor(getResources().getColor(R.color.colorDark));
        ImageView m1=(ImageView)findViewById(R.id.m1);
        m1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Main2Activity.this,ItemActivity.class);
                startActivity(i);
            }
        });
    }
}
