package com.zzc.elegantcommunity.module.activity;
import android.support.v4.widget.SwipeRefreshLayout;
import com.zzc.elegantcommunity.module.base.BaseListFragment;


import java.util.List;

/**
 * Created by zhangzhengchao on 2018/1/29.
 */

public class ActivityListFragment extends BaseListFragment<IActivityList.Presenter> implements IActivityList.View,SwipeRefreshLayout.OnRefreshListener{




    @Override
    public void setPresenter(IActivityList.Presenter presenter) {

    }

    @Override
    public void onSetAdapter(List<?> list) {

    }

    @Override
    public void onLoadData() {

    }

    @Override
    protected void initData() throws NullPointerException {

    }




}
