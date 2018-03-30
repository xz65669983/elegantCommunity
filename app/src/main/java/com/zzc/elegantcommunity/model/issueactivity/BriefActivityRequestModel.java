package com.zzc.elegantcommunity.model.issueactivity;

/**
 * Created by zhangzhengchao on 2018/3/29.
 */

public class BriefActivityRequestModel {

    private String userAcc;
    private String token;
    private Integer startNum;
    private Integer pageSize;

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

    public Integer getStartNum() {
        return startNum;
    }

    public void setStartNum(Integer startNum) {
        this.startNum = startNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }


}
