package com.gstart.common.bean;

/**
 * @author yangguangye
 * @Create by gzpykj
 * @Date 2018-05-19 13:15
 */
public class Message {
    private Boolean isSuccess;
    private Object data;
    private Object message;

    public Boolean getSuccess() {
        return isSuccess;
    }

    public void setSuccess(Boolean success) {
        isSuccess = success;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }
}
