/**
 * Copyright (C) 2015 coderyuan.com. All Rights Reserved.
 *
 * CoderyuanMvcLib
 *
 * JsonUtil.java created on 2015年6月17日
 *
 * @author yuanguozheng
 * @since 2015年6月17日
 * @version v1.0.0
 */
package com.coderyuan.utils;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonUtil {

    public static void writeJson(HttpServletResponse res, Object obj) throws IOException {
        PrintWriter writer = res.getWriter();
        writer.write(toJson(obj));
    }

    public static String toJson(Object obj) {
        try {
            Gson gson = new GsonBuilder().serializeNulls().create();
            String result = gson.toJson(obj);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
