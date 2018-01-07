package com.zzc.elegantcommunity.module.news.article;

import android.os.Bundle;
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
 * Created by Meiji on 2017/5/18.
 */

public class NewsArticleView extends BaseListFragment<INewsArticle.Presenter> implements INewsArticle.View {

    private static final String TAG = "NewsArticleView";
    private String categoryId;

    public static NewsArticleView newInstance(String categoryId) {
        Bundle bundle = new Bundle();
        bundle.putString(TAG, categoryId);
        NewsArticleView view = new NewsArticleView();
        view.setArguments(bundle);
        return view;
    }

    @Override
    protected void initData() {
        categoryId = getArguments().getString(TAG);
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        adapter = new MultiTypeAdapter(oldItems);
        Register.registerNewsArticleItem(adapter);
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
        onLoadData();
    }

    @Override
    public void onLoadData() {
        onShowLoading();
        presenter.doLoadData(categoryId);
    }

    @Override
    public void onSetAdapter(final List<?> list) {
        Items newItems = new Items(list);
        newItems.add(new LoadingBean());
        DiffCallback.notifyDataSetChanged(oldItems, newItems, DiffCallback.MUlTI_NEWS, adapter);
        oldItems.clear();
        oldItems.addAll(newItems);
        canLoadMore = true;
    }

    @Override
    public void setPresenter(INewsArticle.Presenter presenter) {
        if (null == presenter) {
            this.presenter = new NewsArticlePresenter(this);
        }
    }
}
