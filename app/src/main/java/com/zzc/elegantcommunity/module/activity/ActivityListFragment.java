package com.zzc.elegantcommunity.module.activity;

import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;

import com.zzc.elegantcommunity.adapter.DiffCallback;
import com.zzc.elegantcommunity.bean.ActivityList.ActivityListBean;
import com.zzc.elegantcommunity.bean.LoadingBean;
import com.zzc.elegantcommunity.bean.LoadingEndBean;
import com.zzc.elegantcommunity.binder.LoadingEndViewBinder;
import com.zzc.elegantcommunity.binder.LoadingViewBinder;
import com.zzc.elegantcommunity.binder.activitylist.ActivitListViewBinder;
import com.zzc.elegantcommunity.module.base.BaseListFragment;
import com.zzc.elegantcommunity.util.OnLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;

/**
 * Created by zhangzhengchao on 2018/1/29.
 */

public class ActivityListFragment extends BaseListFragment<IActivityList.Presenter> implements IActivityList.View,SwipeRefreshLayout.OnRefreshListener{


    public static ActivityListFragment newInstance(){
        return new ActivityListFragment();
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        adapter = new MultiTypeAdapter(oldItems);
        adapter.register(ActivityListBean.class, new ActivitListViewBinder());
        adapter.register(LoadingBean.class, new LoadingViewBinder());
        adapter.register(LoadingEndBean.class, new LoadingEndViewBinder());
        //注册上拉加载更多
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                if (canLoadMore) {
                    canLoadMore = false;
                    presenter.doLoadMoreData();
                }
            }
        });
    }

    @Override
    public void fetchData() {
        super.fetchData();
        for(int i=0;i<=10;i++){
            ActivityListBean activityListBean = new ActivityListBean();
            activityListBean.setExtra("哈哈哈哈哈哈"+i);
            activityListBean.setTitle("张峥超大帅哥"+i);
            oldItems.add(activityListBean);
        }
        ((ActivityListPresenter)this.presenter).setDataList((ArrayList)oldItems);

        oldItems.add(new LoadingBean());
        adapter.setItems(oldItems);
        canLoadMore=true;

    }


    @Override
    public void setPresenter(IActivityList.Presenter presenter) {
        if (null == presenter) {
            this.presenter = new ActivityListPresenter(this);
        }

    }

    @Override
    public void onSetAdapter(List<?> list) {
        Items newItems = new Items(list);
        newItems.add(new LoadingBean());
        DiffCallback.notifyDataSetChanged(oldItems, newItems, DiffCallback.ACTIVITY_LIST, adapter);
        oldItems.clear();
        oldItems.addAll(newItems);
        canLoadMore = true;

    }

    @Override
    public void onLoadData() {
        onShowLoading();
        presenter.doLoadData();
    }

    @Override
    protected void initData() throws NullPointerException {

    }




}
