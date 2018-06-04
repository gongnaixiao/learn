package com.gongnaixiao.learn.jdk;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by xg on 2018/5/31.
 */
public class Cal {
    public static void main(String[] args) {
        Calendar cal = Calendar.getInstance();
        //cal.set(Calendar.MONTH, Calendar.JUNE);
        cal.add(Calendar.MONTH, 1);
        print(cal);

/*        if (cal.get(Calendar.DATE) > 20) {
            cal.add(Calendar.MONTH, 3);
        }
        cal.set(Calendar.DATE, 20);
        print(cal);*/
    }

    private static void print(Calendar cal) {
        Date date = cal.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(sdf.format(date));
    }
}
