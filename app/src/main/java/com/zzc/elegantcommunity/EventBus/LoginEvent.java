package com.zzc.elegantcommunity.EventBus;


import com.zzc.elegantcommunity.model.User;

/**
 * Created by Administrator on 2018/1/1.
 */

public class LoginEvent {
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
