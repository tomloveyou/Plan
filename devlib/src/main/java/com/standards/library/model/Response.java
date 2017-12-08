package com.standards.library.model;

import com.google.gson.annotations.SerializedName;
import com.standards.library.app.ReturnCodeConfig;

/**
 * <网络请求返回实体类>
 *
 * @author chenml@cncn.com
 * @data: 2015/11/16 20:31
 * @version: V1.0
 */
public class Response<T> {
    @SerializedName("code")
    public String code;
    @SerializedName("method")
    public String method;
    @SerializedName("service")
    public String service;
    @SerializedName("msg")
    public String msg;
    @SerializedName("result")
    public T result;

    public Response(String code) {
        this.code = code;
    }

    public Response(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public boolean isSuccess() {
        return code.equals(ReturnCodeConfig.getInstance().successCode+"");
    }

    public boolean isEmptyCode() {
        return ReturnCodeConfig.getInstance().isEmptyCode(Integer.parseInt(code));
    }

}
