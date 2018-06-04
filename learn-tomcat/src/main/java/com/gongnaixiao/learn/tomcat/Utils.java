package com.gongnaixiao.learn.tomcat;

import java.text.SimpleDateFormat;

/**
 * Created by xg on 2018/4/16.
 */
public class Utils {

    private static String now() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
        String now = df.format(System.currentTimeMillis());
        return now;
    }

}
