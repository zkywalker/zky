package org.zky.zky.utils.network;

import android.support.annotation.Keep;

import com.google.gson.annotations.SerializedName;

/**
 * Created by zkywalker on 2017/2/2.
 * package:org.zky.zky.utils
 */

@Keep
public class BaseResponse {
    public static final int CODE_SUCCESS = 0;

    public String msg;
    public int code;

    @SerializedName("error_response")
    public ErrorResponse errorResponse;

    public static final class ErrorResponse {
        public String msg;
        public int code;
    }
}
