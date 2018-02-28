package com.zzc.elegantcommunity.module.activity;

import com.zzc.elegantcommunity.bean.ActivityList.ActivityListBean;
import com.zzc.elegantcommunity.util.TimeUtil;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by Administrator on 2018/2/3.
 */

public class ActivityListPresenter implements IActivityList.Presenter {

    private IActivityList.View view;
    private String time;

    private List<ActivityListBean> dataList = new ArrayList<>();
    private int beanCount = 11;

    public ActivityListPresenter(IActivityList.View view) {
        this.view = view;
        this.time = TimeUtil.getCurrentTimeStamp();
    }

    @Override
    public void doRefresh() {
        if (dataList.size() != 0) {
            dataList.clear();
            time = TimeUtil.getCurrentTimeStamp();
        }
        view.onShowLoading();
        doLoadData();

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

        Observable.create(new ObservableOnSubscribe<List<ActivityListBean>>() {

            @Override
            public void subscribe(ObservableEmitter<List<ActivityListBean>> e) throws Exception {

                for (int i = beanCount; i < beanCount + 10; i++) {
                    ActivityListBean activityListBean = new ActivityListBean();
                    activityListBean.setTitle("张峥超大帅哥" + i);
                    activityListBean.setExtra("哈哈哈哈哈" + i);
                    dataList.add(activityListBean);
                }
                beanCount = beanCount + 10;
                e.onNext(dataList);

            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<ActivityListBean>>() {
                    @Override
                    public void accept(List<ActivityListBean> activityListBeans) throws Exception {
                        doSetAdapter(activityListBeans);
                    }
                });
    }

    @Override
    public void doLoadMoreData() {
        doLoadData();
    }

    @Override
    public void doSetAdapter(List<ActivityListBean> dataBeen) {
        view.onSetAdapter(dataList);
        view.onHideLoading();

    }

    @Override
    public void doShowNoMore() {

    }
}
