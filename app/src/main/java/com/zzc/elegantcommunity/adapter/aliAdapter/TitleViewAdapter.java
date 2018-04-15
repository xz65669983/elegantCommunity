package com.zzc.elegantcommunity.adapter.aliAdapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;

/**
 * Created by Administrator on 2018/4/15.
 */

public class TitleViewAdapter extends DelegateAdapter.Adapter<TitleViewAdapter.TitleViewHolder> {


    @Override
    public LayoutHelper onCreateLayoutHelper() {

        LinearLayoutHelper linearLayoutHelper = new LinearLayoutHelper();
        linearLayoutHelper.setMarginBottom(20);
        return linearLayoutHelper;
    }

    @Override
    public TitleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(TitleViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 1;
    }

    static class TitleViewHolder extends RecyclerView.ViewHolder {



        public TitleViewHolder(View itemView) {
            super(itemView);
            itemView.findViewById()

        }

        @Override
        protected void finalize() throws Throwable {

            super.finalize();
        }
    }
}
