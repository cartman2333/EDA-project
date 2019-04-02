package com.example.jin1999110.forcpn;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import static com.example.jin1999110.forcpn.FramePoString.getPoString;
import static com.example.jin1999110.forcpn.FrameString.getString;

/**
 * Created by jin1999110 on 2018/12/14.
 */

public class InfAdapter extends RecyclerView.Adapter<InfAdapter.ViewHolder> {
    private List<Information> minform;
    public InfAdapter(List<Information> informList){
        minform=informList;
    }
    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card,parent,false);
        ViewHolder holder=new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Information information=minform.get(position);
        if(information.gettype()=="dcu")
        {
            holder.picture.setImageResource(R.drawable.dcu);
            holder.date.setText(getString(information.getDate()));
        }
        else
        {
            holder.picture.setImageResource(R.drawable.cpn);
            holder.date.setText(getPoString(information.getDate()));
        }
    }




    @Override
    public int getItemCount() {
        return minform.size();
    }
    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView date;
        ImageView picture;
        View cardview;
        public ViewHolder(View itemView) {
            super(itemView);
            cardview=itemView;

            picture=(ImageView)itemView.findViewById(R.id.imageview);
            date=(TextView)itemView.findViewById(R.id.data);


        }
    }
}
