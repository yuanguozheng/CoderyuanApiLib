/**
 * Copyright (C) 2015 coderyuan.com. All Rights Reserved.
 *
 * CoderyuanApiLib
 *
 * TimeUtils.java created on 2015年6月24日
 *
 * @author yuanguozheng
 * @since 2015年6月24日
 * @version v1.0.0
 */
package com.coderyuan.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @author yuanguozheng
 * 
 */
public class TimeUtils {

    private static final String DETAIL_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final String TIME_FORMAT = "HH:mm:ss";

    private static SimpleDateFormat sFormat;

    public static String getDetailTime(Date date) {
        sFormat = new SimpleDateFormat(DETAIL_TIME_FORMAT, Locale.CHINA);
        return sFormat.format(date);
    }

    public static String getDate(Date date) {
        sFormat = new SimpleDateFormat(DATE_FORMAT, Locale.CHINA);
        return sFormat.format(date);
    }

    public static String getTime(Date date) {
        sFormat = new SimpleDateFormat(TIME_FORMAT, Locale.CHINA);
        return sFormat.format(date);
    }
}
