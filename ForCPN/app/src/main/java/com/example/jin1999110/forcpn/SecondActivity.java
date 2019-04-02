package com.example.jin1999110.forcpn;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.litepal.LitePal;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SecondActivity extends AppCompatActivity {
    private List<register> list = LitePal.findAll(register.class);;
    private secondAdapter minfadapter=new secondAdapter(list);
    private RecyclerView recyclerView;
    private Button Add,test;
    private EditText address;
    private EditText value;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(getResources().getColor(R.color.colorDark));
        setContentView(R.layout.activity_second);

        recyclerView=(RecyclerView)findViewById(R.id.rerecyclerview) ;
        recyclerView.setAdapter(minfadapter);
        LinearLayoutManager manager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);

        minfadapter.notifyDataSetChanged();

        Add=(Button)findViewById(R.id.update);
        address=(EditText)findViewById(R.id.editText1);
        value=(EditText)findViewById(R.id.editText2);
        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String a=address.getText().toString();
                String v=value.getText().toString();
                register r=new register(a,v);
                String regEx = "[0-9a-fA-F]{8}";
                Pattern pat = Pattern.compile(regEx);
                Matcher mat = pat.matcher(a);
                if(mat.matches()&&!LitePal.isExist(register.class,"address = ?",r.getAddress())){
                    r.save();
                    list.add(r);
                    minfadapter.notifyDataSetChanged();
                }else{
                    Toast.makeText(SecondActivity.this,"请输入正确寄存器地址！",Toast.LENGTH_SHORT).show();
                }

            }
        });
        test=(Button)findViewById(R.id.mtest);
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //实例化对象
                /*fragment = new AttachFragment();
                fragment.show(getFragmentManager(), "dialog");*/
                Intent i=new Intent(SecondActivity.this,ItemActivity.class);
                startActivity(i);
                //Log.d("intent", "onClick: ");
            }
        });
    }
}
