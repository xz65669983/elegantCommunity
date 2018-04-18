package com.zzc.elegantcommunity.adapter.aliAdapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.zzc.elegantcommunity.R;

import java.util.ArrayList;

/**
 * Created by zhangzhengchao on 2018/4/12.
 */

public class EachItemViewAdapter  extends MyDelegateAdapter<EachItemViewAdapter.EachItemViewHolder>{

    private ArrayList<EachItemBean> eachItemBeans;

    public EachItemViewAdapter(Context context, LayoutHelper layoutHelper, int count) {
        super(context, layoutHelper, count);
    }

    public EachItemViewAdapter(Context context, LayoutHelper layoutHelper, int count, @NonNull VirtualLayoutManager.LayoutParams layoutParams) {
        super(context, layoutHelper, count, layoutParams);
    }

    public void setBeans(ArrayList<EachItemBean> eachItemBeans){
        this.eachItemBeans=eachItemBeans;
    }




    @Override
    public EachItemViewAdapter.EachItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new EachItemViewAdapter.EachItemViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_each_item, parent, false));
    }

    @Override
    public void onBindViewHolder(EachItemViewAdapter.EachItemViewHolder holder, int position) {
        // only vertical
        holder.itemView.setLayoutParams(
                new VirtualLayoutManager.LayoutParams(mLayoutParams));

        EachItemBean eachItemBean = eachItemBeans.get(position);
        holder.headPicture.setImageBitmap(eachItemBean.getHeadPicture());
        holder.tvContent.setText(eachItemBean.getContent());
        if(!eachItemBean.isHasArrowPicture()){
            holder.arrowPicture.setVisibility(View.INVISIBLE);
        }

    }
;
    @Override
    public int getItemCount() {
        return eachItemBeans.size();
    }


    @Override
    protected void onBindViewHolderWithOffset(EachItemViewAdapter.EachItemViewHolder holder, int position, int offsetTotal) {

    }


    static class EachItemViewHolder extends RecyclerView.ViewHolder {

        public ImageView headPicture;
        public TextView tvContent;
        public ImageView arrowPicture;

        public EachItemViewHolder(View itemView) {
            super(itemView);
            headPicture = itemView.findViewById(R.id.iv_headPicture);
            tvContent=itemView.findViewById(R.id.tv_content);
            arrowPicture=itemView.findViewById(R.id.iv_arrowPicture);
        }

        @Override
        protected void finalize() throws Throwable {
            super.finalize();
        }
    }


    public static class EachItemBean{
        private Bitmap headPicture;
        private String content;
        private boolean  hasArrowPicture;

        public Bitmap getHeadPicture() {
            return headPicture;
        }

        public void setHeadPicture(Bitmap headPicture) {
            this.headPicture = headPicture;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public boolean isHasArrowPicture() {
            return hasArrowPicture;
        }

        public void setHasArrowPicture(boolean hasArrowPicture) {
            this.hasArrowPicture = hasArrowPicture;
        }

    }
}