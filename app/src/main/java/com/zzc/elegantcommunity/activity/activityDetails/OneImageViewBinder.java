package com.zzc.elegantcommunity.activity.activityDetails;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.zzc.elegantcommunity.R;

import me.drakeet.multitype.ItemViewBinder;

/**
 * Created by Administrator on 2018/2/17.
 */

public class OneImageViewBinder extends ItemViewBinder <OneImageViewBean,OneImageViewBinder.OneImageViewHolder>{


    @NonNull
    @Override
    protected OneImageViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View view = inflater.inflate(R.layout.item_one_imageview, parent, false);

        return new OneImageViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull OneImageViewHolder holder, @NonNull OneImageViewBean item) {
//        holder.imageView.setImageResource(R.mipmap.app_splash);
        holder.imageView.setBackgroundColor(Color.BLUE);
    }

    public class OneImageViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;

        public OneImageViewHolder(View itemView) {
            super(itemView);
             imageView = itemView.findViewById(R.id.oneImageView);
        }
    }
}
