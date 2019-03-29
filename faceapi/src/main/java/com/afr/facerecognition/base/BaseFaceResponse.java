package com.afr.facerecognition.base;

import com.afr.utils.stringhelper.StringHelper;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Yan liang
 * @create 2019/3/28
 * @since 1.0.0
 */
public abstract class BaseFaceResponse {
    // 用于区分每一次请求的唯一的字符串。
    @JsonProperty("request_id")
    private String requestId;

    // 整个请求所花费的时间，单位为毫秒。
    @JsonProperty("timeUsed")
    private int timeUsed;

    // 当请求失败时才会返回此字符串，具体返回内容见后续错误信息章节。否则此字段不存在。
    @JsonProperty("error_message")
    private String errorMessage;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public int getTimeUsed() {
        return timeUsed;
    }

    public void setTimeUsed(int timeUsed) {
        this.timeUsed = timeUsed;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public boolean IsSuccess() {
        return StringHelper.IsNullOrEmpty(getErrorMessage()) ? true : false;
    }
}