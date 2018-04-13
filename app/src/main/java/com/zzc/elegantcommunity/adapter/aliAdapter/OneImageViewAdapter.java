package com.zzc.elegantcommunity.adapter.aliAdapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.zzc.elegantcommunity.R;


/**
 * Created by zhangzhengchao on 2018/4/12.
 */

public class OneImageViewAdapter extends MyDelegateAdapter<OneImageViewAdapter.MainViewHolder>{


    public OneImageViewAdapter(Context context, LayoutHelper layoutHelper, int count) {
        super(context, layoutHelper, count);
    }

    public OneImageViewAdapter(Context context, LayoutHelper layoutHelper, int count, @NonNull VirtualLayoutManager.LayoutParams layoutParams) {
        super(context, layoutHelper, count, layoutParams);
    }


    @Override
    public OneImageViewAdapter.MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new OneImageViewAdapter.MainViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_one_imageview, parent, false));
    }

    @Override
    public void onBindViewHolder(OneImageViewAdapter.MainViewHolder holder, int position) {
        // only vertical
        if(mLayoutParams!=null){
            holder.itemView.setLayoutParams(
                    new VirtualLayoutManager.LayoutParams(mLayoutParams));
        }

    }


    @Override
    protected void onBindViewHolderWithOffset(OneImageViewAdapter.MainViewHolder holder, int position, int offsetTotal) {

        holder.oneImageView.setImageResource(R.mipmap.app_splash);
    }

   /* @Override
    public int getItemCount() {
        return mCount;
    }*/


    static class MainViewHolder extends RecyclerView.ViewHolder {

        public ImageView oneImageView;

        public MainViewHolder(View itemView) {
            super(itemView);
            oneImageView = itemView.findViewById(R.id.oneImageView);
        }

        @Override
        protected void finalize() throws Throwable {

            super.finalize();
        }
    }
}