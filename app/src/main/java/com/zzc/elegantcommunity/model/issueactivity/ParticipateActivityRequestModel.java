package com.zzc.elegantcommunity.model.issueactivity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by zhangzhengchao on 2018/4/17.
 */

public class ParticipateActivityRequestModel {
     private String userAcc;
     private String  token;
     @SerializedName("participator")
     private Participator participator;

     public String getUserAcc() {
          return userAcc;
     }

     public void setUserAcc(String userAcc) {
          this.userAcc = userAcc;
     }

     public String getToken() {
          return token;
     }

     public void setToken(String token) {
          this.token = token;
     }

     public Participator getParticipator() {
          return participator;
     }

     public void setParticipator(Participator participator) {
          this.participator = participator;
     }

     static public class  Participator{
         Integer activityId;
         Integer userId;

          public Integer getActivityId() {
               return activityId;
          }

          public void setActivityId(Integer activityId) {
               this.activityId = activityId;
          }

          public Integer getUserId() {
               return userId;
          }

          public void setUserId(Integer userId) {
               this.userId = userId;
          }
     }
}
