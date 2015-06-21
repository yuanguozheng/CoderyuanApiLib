/**
 * Copyright (C) 2015 coderyuan.com. All Rights Reserved.
 *
 * CoderyuanMvcLib
 *
 * TextUtils.java created on 2015年6月21日
 *
 * @author yuanguozheng
 * @since 2015年6月21日
 * @version v1.0.0
 */
package com.coderyuan.utils;

/**
 * 文本工具类
 * 
 * @author yuanguozheng
 */
public class TextUtils {

    public static boolean isEmpty(CharSequence str) {
        if (str == null || str.length() == 0) {
            return true;
        } else {
            return false;
        }
    }
}
