package com.zzc.elegantcommunity.module.search.result;


import com.zzc.elegantcommunity.bean.news.MultiNewsArticleDataBean;
import com.zzc.elegantcommunity.module.base.IBaseListView;
import com.zzc.elegantcommunity.module.base.IBasePresenter;

import java.util.List;

/**
 * Created by Meiji on 2017/2/7.
 */

interface ISearchResult {

    interface View extends IBaseListView<Presenter> {

        /**
         * 请求数据
         */
        void onLoadData();
    }

    interface Presenter extends IBasePresenter {

        /**
         * 请求数据
         */
        void doLoadData(String... parameter);

        /**
         * 再起请求数据
         */
        void doLoadMoreData();

        /**
         * 设置适配器
         */
        void doSetAdapter(List<MultiNewsArticleDataBean> dataBeen);

        void doShowNoMore();
    }
}
