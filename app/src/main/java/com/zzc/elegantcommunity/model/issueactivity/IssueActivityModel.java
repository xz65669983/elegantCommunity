package com.zzc.elegantcommunity.model.issueactivity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2017/12/31.
 */

public class IssueActivityModel {
    private String token;
    @SerializedName("activity")
    private ActivityDetialsModel activityDetialsModel;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public ActivityDetialsModel getActivityDetialsModel() {
        return activityDetialsModel;
    }

    public void setActivityDetialsModel(ActivityDetialsModel activityDetialsModel) {
        this.activityDetialsModel = activityDetialsModel;
    }
}
