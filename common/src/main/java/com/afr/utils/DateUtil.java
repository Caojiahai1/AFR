package com.afr.utils;

import java.text.SimpleDateFormat;

/**
 * @author Yan liang
 * @create 2019/4/11
 * @since 1.0.0
 */
public class DateUtil {
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    private static ThreadLocal<SimpleDateFormat> threadLocal = new ThreadLocal();

    public static SimpleDateFormat getDateFormat() {
        SimpleDateFormat simpleDateFormat = threadLocal.get();
        if (simpleDateFormat == null) {
            simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);
            threadLocal.set(simpleDateFormat);
        }
        return simpleDateFormat;
    }
}