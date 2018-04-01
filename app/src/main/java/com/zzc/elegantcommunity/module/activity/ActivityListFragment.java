package com.zzc.elegantcommunity.module.activity;

import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.zzc.elegantcommunity.InitApp;
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
import com.zzc.elegantcommunity.util.ActivityCacheCountUtils;
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

    @Override
    protected void initView(View view) {
        super.initView(view);
        adapter = new MultiTypeAdapter(oldItems);
        adapter.register(BriefActivityModel.class, new ActivitListViewBinder());
        adapter.register(LoadingBean.class, new LoadingViewBinder());
        adapter.register(LoadingEndBean.class, new LoadingEndViewBinder());
        recyclerView.setAdapter(adapter);

        //注册上拉加载更多
        recyclerView.addOnScrollListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                if (canLoadMore) {
                    canLoadMore = false;
                    ActivityCacheCountUtils instance = ActivityCacheCountUtils.getInstance();
                    Long oldestId = instance.getOldestId();
                    getData(oldestId - 1, 5)
                            .doOnNext(new Consumer<BriefAcitivtyResponseModel>() {
                                @Override
                                public void accept(BriefAcitivtyResponseModel briefAcitivtyResponseModel) throws Exception {
                                    List<BriefActivityModel> briefActivityModelList = briefAcitivtyResponseModel.getBriefActivityModelList();
                                    if(briefActivityModelList!=null && briefActivityModelList.size()!=0){
                                        ActivityCacheCountUtils instance = ActivityCacheCountUtils.getInstance();
                                        instance.saveOldestId((briefActivityModelList.get(briefActivityModelList.size()-1).getId()));
                                    }
                                }
                            }).observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Consumer<BriefAcitivtyResponseModel>() {
                                @Override
                                public void accept(BriefAcitivtyResponseModel briefAcitivtyResponseModel) throws Exception {
                                    showData(briefAcitivtyResponseModel);
                                }
                            });


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
        if (oldItems.size() != 0) {

            BriefActivityModel briefActivityModel = (BriefActivityModel) list.get(list.size() - 1);
                oldItems.remove(oldItems.size() - 1);
                newItems.addAll(oldItems);
                newItems.addAll(list);
            if(briefActivityModel.getId()==1){
                newItems.add(new LoadingEndBean());
            }else{
                newItems.add(new LoadingBean());
            }
//            DiffCallback.notifyDataSetChanged(oldItems, newItems, DiffCallback.ACTIVITY_LIST, adapter);
                adapter.setItems(newItems);
                adapter.notifyDataSetChanged();
                oldItems = newItems;
            if(briefActivityModel.getId()==1){
                canLoadMore = false;
            }else{
                canLoadMore = true;
            }
        } else {
            newItems.addAll(list);
            newItems.add(new LoadingBean());
            oldItems = newItems;
            adapter.setItems(oldItems);
            adapter.notifyDataSetChanged();
            canLoadMore = true;
        }




    }

    /**
     * 下拉加载最新
     */
    @Override
    public void onLoadData() {
        onShowLoading();

        initData();
        onHideLoading();

    }

    @Override
    protected void initData() throws NullPointerException {

        Log.i(TAG, "进入函数initData");
        getData(Long.valueOf(-1), 5)
                .doOnNext(new Consumer<BriefAcitivtyResponseModel>() {
            @Override
            public void accept(BriefAcitivtyResponseModel briefAcitivtyResponseModel) throws Exception {
                List<BriefActivityModel> briefActivityModelList = briefAcitivtyResponseModel.getBriefActivityModelList();
                if (briefActivityModelList != null && briefActivityModelList.size() != 0) {
                    Long latestid = briefActivityModelList.get(0).getId();
                    Log.d(TAG, "latestid:+" + latestid);
                    Long oldestid = briefActivityModelList.get(briefActivityModelList.size() - 1).getId();
                    Log.d(TAG, "oldestid:+" + oldestid);
                    ActivityCacheCountUtils instance = ActivityCacheCountUtils.getInstance();
                    instance.saveLatestId(latestid);
                    instance.saveOldestId(oldestid);
                    oldItems.clear();

                }
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BriefAcitivtyResponseModel>() {
                    @Override
                    public void accept(BriefAcitivtyResponseModel briefAcitivtyResponseModel) throws Exception {
                        showData(briefAcitivtyResponseModel);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e(TAG, throwable.getMessage());
                    }
                });


    }

    private Observable<BriefAcitivtyResponseModel> getData(Long startNum, int size) {
        ActivityService activityService = RxRetrofitWithGson.getRxRetrofitInstance().create(ActivityService.class);


        BriefActivityRequestModel barm = new BriefActivityRequestModel();
        barm.setPageSize(size);
        barm.setStartNum(startNum);
        return activityService.getNewestActivities(barm)
                .subscribeOn(Schedulers.io());

    }


    private void showData(BriefAcitivtyResponseModel briefAcitivtyResponseModel) {
        Log.i(TAG, "进入函数showData");

        Log.d(TAG, "收到的返回码：" + briefAcitivtyResponseModel.getResultCode());
        if (!briefAcitivtyResponseModel.getResultCode().equals("0000")) {
            Toast.makeText(InitApp.AppContext, "系统繁忙", Toast.LENGTH_LONG).show();
            return;
        }
        Log.d(TAG, "收到的返回数据：" + briefAcitivtyResponseModel.getResultMessage());
        List<BriefActivityModel> briefActivityModelList =
                briefAcitivtyResponseModel.getBriefActivityModelList();
        Log.d(TAG, "收到数据条数" + briefActivityModelList.size());
        if (briefActivityModelList != null && briefActivityModelList.size() != 0) {
            onSetAdapter(briefActivityModelList);
            canLoadMore = true;
        }
    }

}

