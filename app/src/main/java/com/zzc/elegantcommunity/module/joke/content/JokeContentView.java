package com.zzc.elegantcommunity.module.joke.content;

import android.view.View;

import com.zzc.elegantcommunity.Register;
import com.zzc.elegantcommunity.adapter.DiffCallback;
import com.zzc.elegantcommunity.bean.LoadingBean;
import com.zzc.elegantcommunity.module.base.BaseListFragment;
import com.zzc.elegantcommunity.util.OnLoadMoreListener;

import java.util.List;

import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;

/**
 * Created by Meiji on 2016/12/28.
 */

public class JokeContentView extends BaseListFragment<IJokeContent.Presenter> implements IJokeContent.View {

    public static JokeContentView newInstance() {
        return new JokeContentView();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        adapter = new MultiTypeAdapter(oldItems);
        Register.registerJokeContentItem(adapter);
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
        onLoadData();
    }

    @Override
    public void onLoadData() {
        onShowLoading();
        presenter.doLoadData();
    }

    @Override
    public void onSetAdapter(List<?> list) {
        Items newItems = new Items(list);
        newItems.add(new LoadingBean());
        DiffCallback.notifyDataSetChanged(oldItems, newItems, DiffCallback.JOKE, adapter);
        oldItems.clear();
        oldItems.addAll(newItems);
        canLoadMore = true;
    }

    @Override
    public void setPresenter(IJokeContent.Presenter presenter) {
        if (null == presenter) {
            this.presenter = new JokeContentPresenter(this);
        }
    }
}
