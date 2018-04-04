package com.zzc.elegantcommunity.binder.activitylist;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zzc.elegantcommunity.R;
import com.zzc.elegantcommunity.model.issueactivity.ActivityDetialsModel;
import com.zzc.elegantcommunity.model.issueactivity.IssueActivityModel;

import me.drakeet.multitype.ItemViewBinder;

/**
 * Created by zhangzhengchao on 2018/4/4.
 */

public class IssueActivityViewBinder extends ItemViewBinder<ActivityDetialsModel, IssueActivityViewBinder.ViewHolder> {


    private static final String TAG = "IssueActivityViewBinder";

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
         Log.i(TAG,"进来造View了！！！！！！！！");
        View view = inflater.inflate(R.layout.fragment_issue_activity, parent, false);
        return new IssueActivityViewBinder.ViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull ActivityDetialsModel item) {


    }

    class ViewHolder extends RecyclerView.ViewHolder {



        ViewHolder(View itemView) {
            super(itemView);

        }
    }
}
