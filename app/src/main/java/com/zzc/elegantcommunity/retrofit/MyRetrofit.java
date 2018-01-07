package com.zzc.elegantcommunity.retrofit;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by zzc on 2017/10/15.
 */

public class MyRetrofit {
    static Retrofit retrofit=null;

    public static synchronized Retrofit getGsonRetrofitInstance(){
        if(retrofit==null){
             retrofit = new Retrofit.Builder()
                   // .baseUrl("http://kikipar.imwork.net:29296/")
                     .baseUrl(ConstantURL.URL)
                     .addConverterFactory(GsonConverterFactory.create())
                     .build();
        }
        return retrofit;
    }
}
