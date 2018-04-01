package com.zzc.elegantcommunity.util;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.zzc.elegantcommunity.InitApp;

/**
 * Created by Administrator on 2018/3/31.
 */

public class ActivityCacheCountUtils {

    private ActivityCacheCountUtils(){}

    private static ActivityCacheCountUtils activityCacheCountUtils;

    private SharedPreferences tokentils = PreferenceManager.getDefaultSharedPreferences(InitApp.AppContext);

    public static ActivityCacheCountUtils getInstance(){
        if(activityCacheCountUtils ==null){
            return  new ActivityCacheCountUtils();
        }else{
            return activityCacheCountUtils;
        }

    }

    public void saveOldestId(Long oldestId){
        SharedPreferences.Editor edit = tokentils.edit();
        edit.putLong("oldestId",oldestId);
        edit.commit();

    }
    public Long getOldestId(){
        return tokentils.getLong("oldestId", 0);
    }

    public void saveLatestId(Long latestId){
        SharedPreferences.Editor edit = tokentils.edit();
        edit.putLong("latestId",latestId);
        edit.commit();

    }
    public Long getLatestId(){
        return tokentils.getLong("latestId", 0);
    }



}
