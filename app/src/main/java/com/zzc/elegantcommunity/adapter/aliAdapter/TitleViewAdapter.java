package com.zzc.elegantcommunity.adapter.aliAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.zzc.elegantcommunity.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.zip.Inflater;

/**
 * Created by Administrator on 2018/4/15.
 */

public class TitleViewAdapter extends DelegateAdapter.Adapter<TitleViewAdapter.TitleViewHolder> {



    private ArrayList<TitleViewHolderBean> titleViewHolderBeans;

    private Context mContext;

    public TitleViewAdapter(Context context){
        mContext=context;
    }

    public ArrayList<TitleViewHolderBean> getTitleViewHolderBeans() {
        return titleViewHolderBeans;
    }

    public void setTitleViewHolderBeans(ArrayList<TitleViewHolderBean> titleViewHolderBeans) {
        this.titleViewHolderBeans = titleViewHolderBeans;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {

        LinearLayoutHelper linearLayoutHelper = new LinearLayoutHelper();
        linearLayoutHelper.setMarginBottom(20);
        return linearLayoutHelper;
    }

    @Override
    public TitleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TitleViewAdapter.TitleViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_title_view, parent, false));
    }

    @Override
    public void onBindViewHolder(TitleViewHolder holder, int position) {
        VirtualLayoutManager.LayoutParams layoutParams = new VirtualLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 300);

        holder.itemView.setLayoutParams(
                new VirtualLayoutManager.LayoutParams(layoutParams));

        TitleViewHolderBean titleViewHolderBean = titleViewHolderBeans.get(position);

        holder.tvContent.setText(titleViewHolderBean.getContent());
        holder.tvTitle.setText(titleViewHolderBean.getTvTitle());
        holder.tvSignupCount.setText(titleViewHolderBean.getTvSignupCount());


    }

    @Override
    public int getItemCount() {
        return 1;
    }

    static class TitleViewHolder extends RecyclerView.ViewHolder {
       private TextView tvSignupCount;
       private  TextView tvTitle;
       private TextView tvContent;


        public TitleViewHolder(View itemView) {
            super(itemView);
            tvSignupCount = itemView.findViewById(R.id.tv_signup_count);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvContent = itemView.findViewById(R.id.tv_content);
        }

        @Override
        protected void finalize() throws Throwable {

            super.finalize();
        }
    }


   public static class TitleViewHolderBean{
       private  String tvSignupCount;
       private String tvTitle;
       private String content;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getTvSignupCount() {
            return tvSignupCount;
        }

        public void setTvSignupCount(String tvSignupCount) {
            this.tvSignupCount = tvSignupCount;
        }

        public String getTvTitle() {
            return tvTitle;
        }

        public void setTvTitle(String tvTitle) {
            this.tvTitle = tvTitle;
        }


    }
}
