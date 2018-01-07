package com.zzc.elegantcommunity.model;

/**
 * Created by Administrator on 2018/1/1.
 */

public class MyResponseBody {
   private  String resultCode;
   private String resultMessage;

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
}
