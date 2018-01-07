package com.zzc.elegantcommunity.module.news.comment;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.zzc.elegantcommunity.R;
import com.zzc.elegantcommunity.Register;
import com.zzc.elegantcommunity.adapter.DiffCallback;
import com.zzc.elegantcommunity.bean.LoadingBean;
import com.zzc.elegantcommunity.module.base.BaseListFragment;
import com.zzc.elegantcommunity.util.OnLoadMoreListener;
import com.zzc.elegantcommunity.util.SettingUtil;

import java.util.List;

import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;

/**
 * Created by Meiji on 2017/2/28.
 */

public class NewsCommentFragment extends BaseListFragment<INewsComment.Presenter> implements INewsComment.View {

    private static final String GROUP_ID = "groupId";
    private static final String ITEM_ID = "itemId";
    private static final String TAG = "NewsCommentFragment";
    private String groupId;
    private String itemId;

    public static NewsCommentFragment newInstance(String groupId, String itemId) {
        NewsCommentFragment instance = new NewsCommentFragment();
        Bundle bundle = new Bundle();
        bundle.putString(GROUP_ID, groupId);
        bundle.putString(ITEM_ID, itemId);
        instance.setArguments(bundle);
        return instance;
    }

    @Override
    protected int attachLayoutId() {
        return R.layout.fragment_list_toolbar;
    }

    @Override
    protected void initData() {
        Bundle arguments = getArguments();
        groupId = arguments.getString(GROUP_ID);
        itemId = arguments.getString(ITEM_ID);
        onLoadData();
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        initToolBar(toolbar, true, getString(R.string.title_comment));
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerView.smoothScrollToPosition(0);
            }
        });
        toolbar.setBackgroundColor(SettingUtil.getInstance().getColor());

        adapter = new MultiTypeAdapter(oldItems);
        Register.registerNewsCommentItem(adapter);
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
        setHasOptionsMenu(true);
    }

    @Override
    public void onRefresh() {
        presenter.doRefresh();
    }

    @Override
    public void onLoadData() {
        onShowLoading();
        presenter.doLoadData(groupId, itemId);
    }

    @Override
    public void onSetAdapter(final List<?> list) {
        Items newItems = new Items(list);
        newItems.add(new LoadingBean());
        DiffCallback.notifyDataSetChanged(oldItems, newItems, DiffCallback.NEWS_COMMENT, adapter);
        oldItems.clear();
        oldItems.addAll(newItems);
        canLoadMore = true;
    }

    @Override
    public void setPresenter(INewsComment.Presenter presenter) {
        if (null == presenter) {
            this.presenter = new NewsCommentPresenter(this);
        }
    }

    @Override
    public void fetchData() {

    }
}
