package com.zzc.elegantcommunity;

import android.content.Context;

import com.facebook.stetho.Stetho;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;


public class SdkManager {
    public static void initStetho(Context context) {
        Stetho.initializeWithDefaults(context);
    }

    public static OkHttpClient.Builder initInterceptor(OkHttpClient.Builder builder) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(interceptor);
        return builder;
    }
}
