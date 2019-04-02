package com.example.jin1999110.forcpn;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  {
    private List<Information> list =new ArrayList<>();
    private InfAdapter minfadapter=new InfAdapter(list);
    private RecyclerView recyclerView;
    private Button button;
    @SuppressLint("HandlerLeak")
    private Handler handler=new Handler(){
        public void handleMessage(Message msg){

            synchronized (this){
                switch (msg.what){
                    case 1:
                        String s=msg.getData().getString("text");
                        handler.removeMessages(1);
                        Information information=new Information("dcu",s);

                        if (!recyclerView.canScrollVertically(1)){
                            list.add(information);
                            minfadapter.notifyDataSetChanged();
                            recyclerView.scrollToPosition(list.size()-1);
                        }else{
                            list.add(information);
                            minfadapter.notifyDataSetChanged();
                        }
                        break;
                    case  2:
                        String v=msg.getData().getString("text");
                        Information information1=new Information("cpn",v);
                        if (!recyclerView.canScrollVertically(1)){
                            list.add(information1);
                            minfadapter.notifyDataSetChanged();
                            recyclerView.scrollToPosition(list.size()-1);
                        }else{
                            list.add(information1);
                            minfadapter.notifyDataSetChanged();
                        }

                        break;
                    default:
                        break;
                }
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(getResources().getColor(R.color.colorDark));
        //getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setContentView(R.layout.activity_main);

        recyclerView=(RecyclerView)findViewById(R.id.recycler_view) ;
        recyclerView.setAdapter(minfadapter);
        LinearLayoutManager manager=new LinearLayoutManager(this);

        recyclerView.setLayoutManager(manager);

        /*Information information=new Information("dcu","55FFAJSUWBSAASUH6712JASGDUWSA87283673GDASDADWE2355FFAJSUWBSAASUH6712JASGDUWSA87283673GDASDADWE2355FFAJSUWBSAASUH6712JASGDUWSA87283673GDASDADWE2355FFAJSUWBSAASUH6712JASGDUWSA87283673GDASDADWE2355FFAJSUWBSAASUH6712JASGDUWSA87283673GDASDADWE2355FFAJSUWBSAASUH6712JASGDUWSA87283673GDASDADWE2355FFAJSUWBSAASUH6712JASGDUWSA87283673GDASDADWE2355FFAJSUWBSAASUH6712JASGDUWSA87283673GDASDADWE2355FFAJSUWBSAASUH6712JASGDUWSA87283673GDASDADWE2355FFAJSUWBSAASUH6712JASGDUWSA87283673GDASDADWE2355FFAJSUWBSAASUH6712JASGDUWSA87283673GDASDADWE23");
        list.add(0,information);
        minfadapter.notifyDataSetChanged();*/

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                HandleSocket handleSocket = new HandleSocket();
                try {
                    Message message=handler.obtainMessage();
                    handleSocket.server(5004,message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        button=(Button)findViewById(R.id.Button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,SecondActivity.class);
                startActivity(intent);

            }
        });




    }


}
