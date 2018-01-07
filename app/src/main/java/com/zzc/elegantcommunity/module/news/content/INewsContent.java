package com.zzc.elegantcommunity.module.news.content;


import com.zzc.elegantcommunity.bean.news.MultiNewsArticleDataBean;
import com.zzc.elegantcommunity.module.base.IBasePresenter;
import com.zzc.elegantcommunity.module.base.IBaseView;

/**
 * Created by Meiji on 2016/12/17.
 */

interface INewsContent {

    interface View extends IBaseView<Presenter> {

        /**
         * 加载网页
         */
        void onSetWebView(String url, boolean flag);
    }

    interface Presenter extends IBasePresenter {

        /**
         * 请求数据
         */
        void doLoadData(MultiNewsArticleDataBean dataBean);
    }
}
