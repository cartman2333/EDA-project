package com.example.jin1999110.forcpn;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.litepal.LitePal;
import org.litepal.crud.DataSupport;
import org.litepal.crud.LitePalSupport;
import org.litepal.exceptions.DataSupportException;

import java.util.List;

import static com.example.jin1999110.forcpn.FrameString.getString;

/**
 * Created by jin1999110 on 2018/12/25.
 */

public class secondAdapter extends RecyclerView.Adapter<secondAdapter.ViewHolder> {
    private List<register> minform;
    public secondAdapter(List<register> informList){
        minform=informList;
    }
    @Override
    public secondAdapter.ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card2,parent,false);
        secondAdapter.ViewHolder holder=new secondAdapter.ViewHolder(view);

        return holder;
    }



    @Override
    public void onBindViewHolder(final secondAdapter.ViewHolder holder, final int position) {
        final register r=minform.get(position);

        holder.value.setText(r.getData());
        holder.address.setText(r.getAddress());

        holder.del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Log.d("secondadapter", "onClick: "+LitePal.findFirst(register.class).getAddress());
                LitePal.deleteAll(register.class,"address = ?",r.getAddress());
                minform.remove(position);
                notifyDataSetChanged();
            }
        });
        holder.save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                r.setAddress(holder.address.getText().toString());
                r.setData(holder.value.getText().toString());
                r.save();
            }
        });
    }




    @Override
    public int getItemCount() {
        return minform.size();
    }
    static class ViewHolder extends RecyclerView.ViewHolder {
        EditText value;
        EditText address;
        Button del;
        Button save;
        public ViewHolder(View itemView) {
            super(itemView);

            address=(EditText) itemView.findViewById(R.id.address);
            save=(Button) itemView.findViewById(R.id.save);
            value=(EditText) itemView.findViewById(R.id.value);
            del=(Button)itemView.findViewById(R.id.delete);


        }
    }
}
