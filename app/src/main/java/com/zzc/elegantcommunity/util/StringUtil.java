package com.zzc.elegantcommunity.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zzc on 2017/7/6.
 */

public class StringUtil {

    public static String getStringNum(String s) {
        String regex = "[^0-9]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(s);
        return matcher.replaceAll("").trim();
    }

    public static String cleanFilePathRaw(String filepath){
        return filepath.substring(4);

    }
}
