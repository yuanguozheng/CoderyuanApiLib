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
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import com.coderyuan.models.ApiResultManager;
import com.coderyuan.models.ApiResultManager.ErrorTypes;
import com.coderyuan.models.ResultModel;
import com.coderyuan.utils.JsonUtil;

/**
 * Api基类
 * 
 * @author yuanguozheng
 */
@SuppressWarnings("serial")
public class BaseWebApiServlet extends HttpServlet {

    /**
     * Charsets
     */
    private static final String DES_CHARSET = "utf-8";

    /**
     * Code Names
     */
    private static final String API_EXT = "Api";
    private static final String ENTER_METHOD_NAME = "handleRequest";
    private static final String GET_FLAG_NAME = "mAllowGet";

    /**
     * Formats
     */
    private static final String FULL_FORMAT = "%s.%s";
    private static final String CLASS_FORMAT = "%s%s";

    private Class<?> mApiClass = null;
    private Object mApiNewInstance = null;
    private Method mOperationMethod = null;

    private boolean mAllowGet = true;
    private String mRestParam = null;
    private Map<String, String[]> mParams;

    private HttpServletRequest mRequest;
    private HttpServletResponse mResponse;

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

    public ResultModel doOperation() {
        String packageName = this.getClass().getPackage().getName();
        String className = String.format(CLASS_FORMAT, StringUtils.capitalize(getRestParam()), API_EXT);
        String apiClassName = String.format(FULL_FORMAT, packageName, className);
        try {
            mApiClass = Class.forName(apiClassName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return ApiResultManager.getErrorResult(ErrorTypes.NOT_FOUND);
        }
        try {
            Constructor<?> constructor = mApiClass.getConstructor(BaseWebApiServlet.class);
            mApiNewInstance = constructor.newInstance(this);
            Field allowGetField = mApiClass.getSuperclass().getDeclaredField(GET_FLAG_NAME);
            allowGetField.setAccessible(true);
            boolean isAllowGet = (boolean) allowGetField.get(mApiNewInstance);
            if (!isAllowGet && mRequest.getMethod() == "GET") {
                return ApiResultManager.getErrorResult(ErrorTypes.METHOD_NOT_ALLOW);
            }
            mOperationMethod = mApiClass.getMethod(ENTER_METHOD_NAME);
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResultManager.getErrorResult(ErrorTypes.NOT_FOUND);
        }
        try {
            return (ResultModel) mOperationMethod.invoke(mApiNewInstance);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        if (!mAllowGet) {
            JsonUtil.writeJson(res, ApiResultManager.getErrorResult(ErrorTypes.METHOD_NOT_ALLOW));
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
            param = mParams.get(key)[0];
            return param;
        }
        return null;
    }

    public String getRestParam() {
        return StringUtils.isBlank(mRestParam) ? null : mRestParam;
    }

    public HttpSession getSession() {
        return getRequest() != null ? getRequest().getSession() : null;
    }

    public HttpServletRequest getRequest() {
        return mRequest;
    }

    public HttpServletResponse getResponse() {
        return mResponse;
    }

    private void initRestParam(HttpServletRequest req) {
        mRestParam = req.getPathInfo().replaceAll("/", "");
    }

    private void procRequest(HttpServletRequest req, HttpServletResponse res) throws UnsupportedEncodingException,
            IOException {
        mRequest = req;
        mResponse = res;
        initParams(req);
        JsonUtil.writeJson(res, doOperation());
    }

    private void initParams(HttpServletRequest req) throws UnsupportedEncodingException {
        req.setCharacterEncoding(DES_CHARSET);
        mParams = req.getParameterMap();
        initRestParam(req);
    }
}