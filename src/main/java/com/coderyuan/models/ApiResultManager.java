/**
 * Copyright (C) 2015 coderyuan.com. All Rights Reserved.
 * <p>
 * CoderyuanApiLib
 * <p>
 * ApiResultManager.java created on 2015年6月17日
 *
 * @author yuanguozheng
 * @version v1.0.0
 * @since 2015年6月17日
 */
package com.coderyuan.models;

public class ApiResultManager {

    public enum ErrorTypes {
        NOT_FOUND, SERVER_ERROR, PARAM_ERROR, DB_ERROR, METHOD_NOT_ALLOW, USER_NOT_EXISTED, USER_EXISTED,
        USER_NOT_AVAILABLE, NOT_LOGIN, VERIFIED_FAILED, ITEM_ADDED, UNKNOWN_ERROR
    }

    public enum SuccessTypes {
        NEED_VERIFY, SUCCESS
    }

    public static ResultModel getResult(boolean isSuccess, Object data) {
        ResultModel result = new ResultModel();
        result.setStatus(isSuccess);
        result.setMsg(data);
        return result;
    }

    public static ResultModel getErrorResult(String error) {
        return getResult(false, error);
    }

    public static ResultModel getErrorResult(ErrorTypes error) {
        return getErrorResult(error.toString());
    }

    public static ResultModel getSuccesResult(Object msg) {
        return getResult(true, msg);
    }

    public static ResultModel getSuccesResult(SuccessTypes success) {
        return getSuccesResult(success.toString());
    }

    public static ResultModel getDefaultSuccesResult() {
        return getSuccesResult(SuccessTypes.SUCCESS.toString());
    }

    public static ResultModel getRawResult(String result) {
        ResultModel raw = new ResultModel();
        raw.setRawOutput(true);
        raw.setMsg(result);
        return raw;
    }
}
