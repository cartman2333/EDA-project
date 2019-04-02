package com.example.jin1999110.forcpn;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class ItemActivity extends AppCompatActivity {

    private List<mitem> list=new ArrayList<>();;
    private ItemAdapter madapter=new ItemAdapter(list);
    private RecyclerView recyclerView;

    final mitem temp1=new mitem(3,"照明设备");
    final mitem temp2=new mitem(3,"中央空调");
    @SuppressLint("HandlerLeak")
    private Handler handler=new Handler() {
        public void handleMessage(Message msg) {

            synchronized (this) {
                switch (msg.what) {
                    case 1:
                        madapter.notifyDataSetChanged();
                        break;

                    default:
                        break;
                }
            }

        }
    };
    private Button mstart;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        mstart=(Button)findViewById(R.id.mstart);

        getWindow().setStatusBarColor(getResources().getColor(R.color.colorDark));
        //getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        recyclerView=(RecyclerView)findViewById(R.id.recyclerview) ;
        recyclerView.setAdapter(madapter);
        LinearLayoutManager manager=new LinearLayoutManager(this);

        recyclerView.setLayoutManager(manager);

        init();
        mstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                temp2.setNormal(2);
                temp1.setNormal(2);
                list.set(0,temp1);
                list.set(1,temp2);
                madapter.notifyDataSetChanged();

                new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        try {
                            Thread.sleep(3000);//休眠3秒
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        temp2.setNormal(1);
                        temp2.setResult("0x0d2123");
                        list.set(1,temp2);
                        temp1.setNormal(0);
                        temp1.setResult("0x000000");
                        list.set(0,temp1);
                        Message message=handler.obtainMessage();
                        message.what=1;
                        message.sendToTarget();

                    }
                }.start();
            }
        });
    }
    public void init(){

        list.add(temp1);
        list.add(temp2);
        madapter.notifyDataSetChanged();
    }

}
