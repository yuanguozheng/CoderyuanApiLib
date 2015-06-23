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
        setAllowGet(isAllowGet);
    }

    public void setAllowGet(boolean allowGet) {
        mAllowGet = allowGet;
    }

    public boolean isAllowGet() {
        return mAllowGet;
    }

    public abstract ResultModel doOperation();

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        if (!mAllowGet) {
            res.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return;
        }
        procRequest(req, res);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        procRequest(req, res);
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

    private void procRequest(HttpServletRequest req, HttpServletResponse res) throws UnsupportedEncodingException,
            IOException {
        initParams(req);
        JsonUtil.writeJson(res, doOperation());
    }

    private void initParams(HttpServletRequest req) throws UnsupportedEncodingException {
        req.setCharacterEncoding("utf-8");
        mParams = req.getParameterMap();
        initRestParam(req);
    }

}