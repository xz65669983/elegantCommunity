package com.zzc.elegantcommunity.module.video.article;


import com.zzc.elegantcommunity.bean.news.MultiNewsArticleDataBean;
import com.zzc.elegantcommunity.module.base.IBaseListView;
import com.zzc.elegantcommunity.module.base.IBasePresenter;

import java.util.List;

/**
 * Created by Meiji on 2017/3/29.
 */

public interface IVideoArticle {

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
        void doLoadData(String... category);

        /**
         * 再起请求数据
         */
        void doLoadMoreData();

        /**
         * 设置适配器
         */
        void doSetAdapter(List<MultiNewsArticleDataBean> dataBeen);
    }
}
