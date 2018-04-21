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

    public void saveOldestId(Integer oldestId){
        SharedPreferences.Editor edit = tokentils.edit();
        edit.putInt("oldestId",oldestId);
        edit.commit();

    }
    public Integer getOldestId(){
        return tokentils.getInt("oldestId", 0);
    }

    public void saveLatestId(Integer latestId){
        SharedPreferences.Editor edit = tokentils.edit();
        edit.putInt("latestId",latestId);
        edit.commit();

    }
    public Integer getLatestId(){
        return tokentils.getInt("latestId", 0);
    }



}
