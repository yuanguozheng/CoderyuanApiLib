/**
 * Copyright (C) 2015 coderyuan.com. All Rights Reserved.
 *
 * CoderyuanApiLib
 *
 * BaseApiClass.java created on 2015年6月24日
 *
 * @author yuanguozheng
 * @since 2015年6月24日
 * @version v1.0.0
 */
package com.coderyuan.api;

import com.coderyuan.models.ResultModel;

/**
 * @author yuanguozheng
 * 
 */
public abstract class BaseApiClass {

    protected boolean mAllowGet = true;
    protected BaseWebApiServlet mBase;

    public BaseApiClass(BaseWebApiServlet base) {
        mBase = base;
    }

    public BaseApiClass(BaseWebApiServlet base, boolean allowGet) {
        mBase = base;
        mAllowGet = allowGet;
    }

    public abstract ResultModel handleRequest();

    protected String getParam(String name) {
        return mBase.getParam(name);
    }
}
