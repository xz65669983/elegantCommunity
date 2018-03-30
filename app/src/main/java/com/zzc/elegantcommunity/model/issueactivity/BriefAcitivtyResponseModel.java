package com.zzc.elegantcommunity.model.issueactivity;

import java.util.List;

/**
 * Created by zhangzhengchao on 2018/3/29.
 */

public class BriefAcitivtyResponseModel {

    private String resultCode;
    private String resultMessage;
    private List<BriefActivityModel> BriefActivityModelList;

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public List<BriefActivityModel> getBriefActivityModelList() {
        return BriefActivityModelList;
    }

    public void setBriefActivityModelList(List<BriefActivityModel> briefActivityModelList) {
        BriefActivityModelList = briefActivityModelList;
    }
}
