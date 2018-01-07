package com.zzc.elegantcommunity.util;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.zzc.elegantcommunity.InitApp;


/**
 * Created by zhangzhengchao on 2017/12/26.
 */

public class UserInfoUtil {

    private UserInfoUtil(){}

    private static UserInfoUtil userInfoUtil;

    private SharedPreferences tokentils = PreferenceManager.getDefaultSharedPreferences(InitApp.AppContext);

    public static UserInfoUtil getInstance(){
        if(userInfoUtil ==null){
            return  new UserInfoUtil();
        }else{
            return userInfoUtil;
        }

    }

    public void saveToken(String token){
        SharedPreferences.Editor edit = tokentils.edit();
        edit.putString("token",token);
        edit.commit();
    }

    public String getToken(){
       return tokentils.getString("token", "");
    }

    public void saveUserName(String userName){
        SharedPreferences.Editor edit = tokentils.edit();
        edit.putString("userName",userName);
        edit.commit();

    }
    public String getUserName(){
        return tokentils.getString("userName", "");
    }

    public void saveVerifyType(Integer verifyType){
        SharedPreferences.Editor edit = tokentils.edit();
        edit.putInt("verifyType",verifyType);
        edit.commit();

    }
    public Integer getVerifyType(){
       return tokentils.getInt("verifyType", 0);
    }

    public void savepPortraitFilename(String portraitFilename){
        SharedPreferences.Editor edit = tokentils.edit();
        edit.putString("portraitFilename",portraitFilename);
        edit.commit();

    }
    public String getPortraitFilename(){
        return tokentils.getString("portraitFilename", "");
    }

    public void saveEmail(String email){
        SharedPreferences.Editor edit = tokentils.edit();
        edit.putString("email",email);
        edit.commit();
    }
    public String getEmail(){
        return tokentils.getString("email", "");
    }

    public void saveMale(Integer male){
        SharedPreferences.Editor edit = tokentils.edit();
        edit.putInt("male",male);
        edit.commit();
    }
    public Integer getMale(){
        return tokentils.getInt("male", 0);
    }


    public void saveNickName(String nickName){
        SharedPreferences.Editor edit = tokentils.edit();
        edit.putString("nickName",nickName);
        edit.commit();

    }
    public String getNickName(){
        return tokentils.getString("nickName", "");
    }

    public void savePhoneNumber(String phoneNumber){
        SharedPreferences.Editor edit = tokentils.edit();
        edit.putString("phoneNumber",phoneNumber);
        edit.commit();

    }
    public String getPhoneNumber(){
        return tokentils.getString("phoneNumber", "");
    }

    public void savePwd(String pwd){
        SharedPreferences.Editor edit = tokentils.edit();
        edit.putString("pwd",pwd);
        edit.commit();
    }
    public String getPwd(){
        return tokentils.getString("pwd", "");
    }

    public void saveUserAcc(String userAcc){
        SharedPreferences.Editor edit = tokentils.edit();
        edit.putString("userAcc",userAcc);
        edit.commit();
    }
    public String getUserAcc(){
        return tokentils.getString("userAcc", "");
    }

    public void saveUserGrade(Integer userGrade){
        SharedPreferences.Editor edit = tokentils.edit();
        edit.putInt("userGrade",userGrade);
        edit.commit();

    }
    public Integer geUserGrade(){
        return tokentils.getInt("userGrade", 0);
    }

}
