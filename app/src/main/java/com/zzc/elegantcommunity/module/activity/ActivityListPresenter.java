package com.zzc.elegantcommunity.module.activity;

import com.zzc.elegantcommunity.model.issueactivity.BriefActivityModel;
import com.zzc.elegantcommunity.util.TimeUtil;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2018/2/3.
 */

public class ActivityListPresenter implements IActivityList.Presenter {

    private IActivityList.View view;
    private String time;

    private List<BriefActivityModel> dataList = new ArrayList<>();
    private int beanCount = 11;

    public ActivityListPresenter(IActivityList.View view) {
        this.view = view;
        this.time = TimeUtil.getCurrentTimeStamp();
    }

    //下拉刷新
    @Override
    public void doRefresh() {

        view.onShowLoading();
        ActivityListFragment view = (ActivityListFragment) this.view;
        view.initData();
        view.onHideLoading();

    }

    @Override
    public void doShowNetError() {

    }

    @Override
    public void doLoadData(String... category) {

        // 释放内存
        if (dataList.size() > 150) {
            dataList.clear();
        }
        dataList.clear();

    }

    @Override
    public void doLoadMoreData() {
//        doLoadData();
    }

    @Override
    public void doSetAdapter(List<BriefActivityModel> dataBeen) {
        view.onSetAdapter(dataList);
        view.onHideLoading();

    }

    @Override
    public void doShowNoMore() {

    }
}
