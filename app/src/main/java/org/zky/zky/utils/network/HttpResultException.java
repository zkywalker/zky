package org.zky.zky.utils.network;

/**
 *
 * Created by zhangkun on 2017/3/16.
 */

public class HttpResultException extends RuntimeException {
    private int statusCode;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public HttpResultException(int code, String detailMessage) {
        super(detailMessage);
        statusCode = code;
    }

}
