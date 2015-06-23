/**
 * Copyright (C) 2015 coderyuan.com. All Rights Reserved.
 *
 * CoderyuanApiLib
 *
 * BaseWebApiServlet.java created on 2015年6月17日
 *
 * @author yuanguozheng
 * @since 2015年6月17日
 * @version v1.0.0
 */
package com.coderyuan.api;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.coderyuan.models.ResultModel;
import com.coderyuan.utils.JsonUtil;

/**
 * Api基类
 * 
 * @author yuanguozheng
 */
@SuppressWarnings("serial")
public abstract class BaseWebApiServlet extends HttpServlet {

    private boolean mAllowGet = true;
    private String mRestParam = null;
    private Map<String, String[]> mParams;

    public BaseWebApiServlet() {
        super();
    }

    public BaseWebApiServlet(boolean isAllowGet) {
        super();
        mAllowGet = isAllowGet;
    }

    public abstract ResultModel doOperation();

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        initParams(req);
        initRestParam(req);
        initResProperty(res);
        if (!mAllowGet) {
            return;
        }
        JsonUtil.writeJson(res, doOperation());
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        initParams(req);
        initResProperty(res);
        JsonUtil.writeJson(res, doOperation());
    }

    public String getParam(String key) {
        if (mParams.containsKey(key)) {
            String param = null;
            try {
                param = new String(mParams.get(key)[0].getBytes("ISO-8859-1"), "utf-8");
            } catch (UnsupportedEncodingException e) {
                log(getServletName(), e.fillInStackTrace());
            }
            return param;
        }
        return null;
    }

    public String getRestParam() {
        return StringUtils.isBlank(mRestParam) ? null : mRestParam;
    }

    private void initRestParam(HttpServletRequest req) {
        mRestParam = req.getPathInfo();
    }

    private void initParams(HttpServletRequest req) throws UnsupportedEncodingException {
        req.setCharacterEncoding("utf-8");
        mParams = req.getParameterMap();
    }

    private void initResProperty(HttpServletResponse res) {
        res.setContentType("application/json;\tcharset=utf-8");
        res.setCharacterEncoding("utf-8");
    }
}