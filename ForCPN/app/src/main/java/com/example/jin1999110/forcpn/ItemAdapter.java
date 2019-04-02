package com.example.jin1999110.forcpn;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by jin1999110 on 2019/3/19.
 */

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {
    private List<mitem> mitem;
    private Animation rotateAnimation;
    private LinearInterpolator interpolator;
    public ItemAdapter(List<mitem> informList){
        mitem=informList;
    }
    @Override
    public ItemAdapter.ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        ItemAdapter.ViewHolder holder=new ItemAdapter.ViewHolder(view);

        //LinearLayout ll_refresh_location = (LinearLayout) view.findViewById(R.id.boool);
        rotateAnimation = AnimationUtils.loadAnimation(parent.getContext(), R.anim.anim_circle_rotate);
        interpolator = new LinearInterpolator();
        rotateAnimation.setInterpolator(interpolator);

        return holder;
    }

    @Override
    public void onBindViewHolder(ItemAdapter.ViewHolder holder, int position) {
        mitem information=mitem.get(position);
        if(information.getNormal()==1)
        {
            holder.picture.setImageResource(R.drawable.right);
            holder.date.setText(information.getDate());
            holder.result.setText("测试通过，返回结果："+information.getResult());
        }
        else if (information.getNormal()==0)
        {
            holder.picture.setImageResource(R.drawable.notright);
            holder.date.setText(information.getDate());
            holder.result.setText("测试失败，返回结果："+information.getResult());
        }
        else if(information.getNormal()==2)
        {
            holder.picture.setImageResource(R.drawable.loading);
            holder.picture.startAnimation(rotateAnimation);
            holder.date.setText(information.getDate());
            holder.result.setText("等待结果");
        }
        else
        {
            holder.picture.setImageResource(R.drawable.munload);
            holder.date.setText(information.getDate());
            holder.result.setText("未测试");

        }
    }




    @Override
    public int getItemCount() {
        return mitem.size();
    }
    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView date;
        TextView result;
        ImageView picture;
        View cardview;
        public ViewHolder(View itemView) {
            super(itemView);
            cardview=itemView;

            picture=(ImageView)itemView.findViewById(R.id.boool);
            date=(TextView)itemView.findViewById(R.id.items);
            result=(TextView)itemView.findViewById(R.id.result);



        }
    }
}
