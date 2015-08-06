/**
 * Copyright (C) 2015 coderyuan.com. All Rights Reserved.
 * <p>
 * CoderyuanApiLib
 * <p>
 * JsonUtil.java created on 2015年6月17日
 *
 * @author yuanguozheng
 * @version v1.0.0
 * @since 2015年6月17日
 */
package com.coderyuan.utils;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import com.coderyuan.models.ResultModel;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonUtil {

    public static void writeJson(HttpServletResponse res, Object obj) throws IOException {
        res.setContentType("application/json;\tcharset=utf-8");
        res.setCharacterEncoding("utf-8");
        PrintWriter writer = res.getWriter();
        writer.write(toJson(obj));
    }

    public static void writeJson(HttpServletResponse res, ResultModel obj) throws IOException {
        res.setContentType("application/json;\tcharset=utf-8");
        res.setCharacterEncoding("utf-8");
        PrintWriter writer = res.getWriter();
        if (obj.getRawOutput()) {
            writer.write(obj.getMsg().toString());
        } else {
            writer.write(toJson(obj));
        }
    }

    public static String toJson(Object obj) {
        try {
            Gson gson = new GsonBuilder().setExclusionStrategies(new JsonKit("mRawOutput")).serializeNulls().create();
            String result = gson.toJson(obj);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    static class JsonKit implements ExclusionStrategy {

        static String[] keys;

        public JsonKit(String... keys) {
            this.keys = keys;
        }

        @Override
        public boolean shouldSkipClass(Class<?> arg0) {
            return false;
        }

        @Override
        public boolean shouldSkipField(FieldAttributes arg0) {
            for (String key : keys) {
                if (key.equals(arg0.getName())) {
                    return true;
                }
            }
            return false;
        }
    }
}
