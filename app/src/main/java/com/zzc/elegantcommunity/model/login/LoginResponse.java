package com.zzc.elegantcommunity.model.login;


import com.zzc.elegantcommunity.model.User;

/**
 * Created by Administrator on 2018/1/1.
 */

public class LoginResponse {
    private String token;
    private User user;
    private String resultCode;
    private String resultMessage;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

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
