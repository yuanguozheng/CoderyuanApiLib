/**
 * Copyright (C) 2015 coderyuan.com. All Rights Reserved.
 * <p>
 * CoderyuanApiLib
 * <p>
 * ResultModel.java created on 2015年6月17日
 *
 * @author yuanguozheng
 * @version v1.0.0
 * @since 2015年6月17日
 */
package com.coderyuan.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResultModel {

    @Expose
    @SerializedName("status")
    private boolean mStatus;

    @Expose
    @SerializedName("msg")
    private Object mMsg;

    private boolean mRawOutput;

    public boolean getStatus() {
        return mStatus;
    }

    public void setStatus(boolean status) {
        mStatus = status;
    }

    public Object getMsg() {
        return mMsg;
    }

    public void setMsg(Object msg) {
        mMsg = msg;
    }

    public boolean isRawOutput() {
        return mRawOutput;
    }

    public boolean getRawOutput() {
        return mRawOutput;
    }

    public void setRawOutput(boolean rawOutput) {
        mRawOutput = rawOutput;
    }
}
