package com.zzc.elegantcommunity.module.issueActivity;

import android.util.Log;
import android.view.View;

import com.zzc.elegantcommunity.binder.activitylist.IssueActivityViewBinder;
import com.zzc.elegantcommunity.model.issueactivity.ActivityDetialsModel;
import com.zzc.elegantcommunity.module.activity.IActivityList;
import com.zzc.elegantcommunity.module.base.BaseListFragment;

import java.util.List;

import me.drakeet.multitype.MultiTypeAdapter;

/**
 * Created by zhangzhengchao on 2018/4/4.
 */

public class IssueActivityFragment extends BaseListFragment<IActivityList.Presenter> {
    @Override
    public void setPresenter(IActivityList.Presenter presenter) {

    }

    @Override
    public void onSetAdapter(List<?> list) {

    }

    @Override
    protected void initData() throws NullPointerException {
        Log.i(TAG, "你为什么不出来~！！！！！！");

        oldItems.add(new ActivityDetialsModel());
        adapter.setItems(oldItems);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        swipeRefreshLayout.setEnabled(false);
        adapter = new MultiTypeAdapter(oldItems);
        adapter.register(ActivityDetialsModel.class, new IssueActivityViewBinder());
        recyclerView.setAdapter(adapter);


    }

}
