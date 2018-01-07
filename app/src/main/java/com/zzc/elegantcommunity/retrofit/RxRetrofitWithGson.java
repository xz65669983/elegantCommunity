package com.zzc.elegantcommunity.retrofit;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2017/12/31.
 */

public class RxRetrofitWithGson {

    static Retrofit retrofit=null;

    public static synchronized Retrofit getRxRetrofitInstance(){
        if(retrofit==null){
            retrofit = new Retrofit.Builder()
                    // .baseUrl("http://kikipar.imwork.net:29296/")
                    .baseUrl(ConstantURL.URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
