package com.aihuishou.service.client;

/**
 * Created by james on 2017/5/31.
 */
public class UniformInvokeException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private int responseCode;

    private String responseInfo;

    private Exception innerException;

    public UniformInvokeException(Exception innerException) {
        this(0, null, innerException);
    }

    public UniformInvokeException(int responseCode, String responseInfo) {
        this(responseCode, responseInfo, null);
    }

    public UniformInvokeException(int responseCode, String responseInfo, Exception innerException) {
        this.responseCode = responseCode;
        this.responseInfo = responseInfo;
        this.innerException = innerException;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseInfo() {
        return responseInfo;
    }

    public void setResponseInfo(String responseInfo) {
        this.responseInfo = responseInfo;
    }

    public Exception getInnerException() {
        return innerException;
    }

    public void setInnerException(Exception innerException) {
        this.innerException = innerException;
    }
}
