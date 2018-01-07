package com.zzc.elegantcommunity.retrofit;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * Created by zhangzhengchao on 2017/12/27.
 */

public class RxRetrofit {
    static Retrofit retrofit=null;

    public static synchronized Retrofit getRxRetrofitInstance(){
        if(retrofit==null){
            retrofit = new Retrofit.Builder()
                    // .baseUrl("http://kikipar.imwork.net:29296/")
                    .baseUrl(ConstantURL.URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
