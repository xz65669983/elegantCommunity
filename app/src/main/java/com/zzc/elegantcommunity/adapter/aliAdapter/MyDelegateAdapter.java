package com.zzc.elegantcommunity.adapter.aliAdapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.VirtualLayoutManager;

/**
 * Created by zhangzhengchao on 2018/4/12.
 */

 abstract public class MyDelegateAdapter<VH extends RecyclerView.ViewHolder> extends DelegateAdapter.Adapter<VH> {

    protected Context mContext;
    protected LayoutHelper mLayoutHelper;
    protected VirtualLayoutManager.LayoutParams mLayoutParams;
    protected int mCount = 0;


    public MyDelegateAdapter(Context context, LayoutHelper layoutHelper, int count) {
        this(context, layoutHelper, count, null);
    }

    public MyDelegateAdapter(Context context, LayoutHelper layoutHelper, int count, @NonNull VirtualLayoutManager.LayoutParams layoutParams) {
        this.mContext = context;
        this.mLayoutHelper = layoutHelper;
        this.mCount = count;
        if(layoutParams!=null){
            this.mLayoutParams = layoutParams;
        }

    }
    @Override
    public int getItemCount() {
        return mCount;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return mLayoutHelper;
    }
}
