/**
 * Copyright (C) 2015 coderyuan.com. All Rights Reserved.
 *
 * CoderyuanApiLib
 *
 * ResultModel.java created on 2015年6月17日
 *
 * @author yuanguozheng
 * @since 2015年6月17日
 * @version v1.0.0
 */
package com.coderyuan.models;

import com.google.gson.annotations.SerializedName;

public class ResultModel {

    @SerializedName("status")
    private boolean mStatus;

    @SerializedName("msg")
    private Object mMsg;

    public boolean getStatus() {
        return mStatus;
    }

    public void setStatus(boolean status) {
        this.mStatus = status;
    }

    public Object getMsg() {
        return mMsg;
    }

    public void setMsg(Object msg) {
        this.mMsg = msg;
    }

}
