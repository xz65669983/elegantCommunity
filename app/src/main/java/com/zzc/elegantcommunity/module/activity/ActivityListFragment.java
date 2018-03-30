package com.zzc.elegantcommunity.module.activity;

import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.View;

import com.zzc.elegantcommunity.bean.LoadingBean;
import com.zzc.elegantcommunity.bean.LoadingEndBean;
import com.zzc.elegantcommunity.binder.LoadingEndViewBinder;
import com.zzc.elegantcommunity.binder.LoadingViewBinder;
import com.zzc.elegantcommunity.binder.activitylist.ActivitListViewBinder;
import com.zzc.elegantcommunity.model.issueactivity.BriefAcitivtyResponseModel;
import com.zzc.elegantcommunity.model.issueactivity.BriefActivityModel;
import com.zzc.elegantcommunity.model.issueactivity.BriefActivityRequestModel;
import com.zzc.elegantcommunity.module.base.BaseListFragment;
import com.zzc.elegantcommunity.retrofit.RxRetrofitWithGson;
import com.zzc.elegantcommunity.retrofit.issueActivity.ActivityService;
import com.zzc.elegantcommunity.util.OnLoadMoreListener;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;

/**
 * Created by zhangzhengchao on 2018/1/29.
 */

public class ActivityListFragment extends BaseListFragment<IActivityList.Presenter> implements IActivityList.View, SwipeRefreshLayout.OnRefreshListener {


    public static ActivityListFragment newInstance() {
        return new ActivityListFragment();
    }

    boolean isfirstComing = true;

    @Override
    protected void initView(View view) {
        super.initView(view);
        adapter = new MultiTypeAdapter(oldItems);
        adapter.register(BriefActivityModel.class, new ActivitListViewBinder());
        adapter.register(LoadingBean.class, new LoadingViewBinder());
        adapter.register(LoadingEndBean.class, new LoadingEndViewBinder());
        //注册上拉加载更多
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                if (canLoadMore) {
                    canLoadMore = false;
                    getDateandShow(0,5);
                    onHideLoading();
                }
            }
        });
    }

    @Override
    public void fetchData() {
        super.fetchData();


    }


    @Override
    public void setPresenter(IActivityList.Presenter presenter) {
        if (null == presenter) {
            this.presenter = new ActivityListPresenter(this);
        }

    }

    @Override
    public void onSetAdapter(List<?> list) {
        Items newItems = new Items();
        oldItems.remove(oldItems.size() - 1);
        newItems.addAll(oldItems);
        newItems.addAll(list);
        newItems.add(new LoadingBean());
        oldItems.add(new LoadingBean());
//        DiffCallback.notifyDataSetChanged(oldItems, newItems, DiffCallback.ACTIVITY_LIST, adapter);
        oldItems.clear();
        oldItems.addAll(newItems);
        canLoadMore = true;
        adapter.notifyDataSetChanged();

    }

    @Override
    public void onLoadData() {
        onShowLoading();
        presenter.doLoadData();
    }

    @Override
    protected void initData() throws NullPointerException {

        Log.i(TAG,"进入函数initData");
        getDateandShow(0,5);

    }

    private Observable<BriefAcitivtyResponseModel> getData(int startNum, int size){
        ActivityService activityService = RxRetrofitWithGson.getRxRetrofitInstance().create(ActivityService.class);


            BriefActivityRequestModel barm = new BriefActivityRequestModel();
            barm.setPageSize(size);
            barm.setStartNum(startNum);
            return activityService.getNewestActivities(barm)
                    .subscribeOn(Schedulers.io());


    }

    private void getDateandShow(int startNum, int size){
        Log.i(TAG,"进入函数getDateandShow");
        getData(startNum,size)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BriefAcitivtyResponseModel>() {
                    @Override
                    public void accept(BriefAcitivtyResponseModel briefAcitivtyResponseModel) throws Exception {
                        List<BriefActivityModel> briefActivityModelList =
                                briefAcitivtyResponseModel.getBriefActivityModelList();
                        Log.i(TAG,"收到数据条数"+briefActivityModelList.size());
                        if (briefActivityModelList != null && briefActivityModelList.size() != 0) {
                            oldItems.addAll(briefActivityModelList);
                            onSetAdapter(oldItems);
                            canLoadMore = true;
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e(TAG, throwable.getMessage());
                    }
                });
    }


}
