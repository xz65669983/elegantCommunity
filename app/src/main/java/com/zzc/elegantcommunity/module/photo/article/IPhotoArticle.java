package com.zzc.elegantcommunity.module.photo.article;


import com.zzc.elegantcommunity.bean.photo.PhotoArticleBean;
import com.zzc.elegantcommunity.module.base.IBaseListView;
import com.zzc.elegantcommunity.module.base.IBasePresenter;

import java.util.List;

interface IPhotoArticle {

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
        void doSetAdapter(List<PhotoArticleBean.DataBean> dataBeen);

        void doShowNoMore();
    }
}
