package com.zzc.elegantcommunity.util;

import java.util.UUID;

/**
 * Created by zzc on 2017/10/29.
 */

public class Uuid {
    public static String getUUID(){
        UUID uuid= UUID.randomUUID();
        String str = uuid.toString();
        String uuidStr=str.replace("-", "");
        return uuidStr;
    }
}
