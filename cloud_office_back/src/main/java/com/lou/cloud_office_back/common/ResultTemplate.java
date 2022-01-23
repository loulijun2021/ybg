package com.lou.cloud_office_back.common;

/**
 * @Author llj
 * @Date 2022/1/19 11:19
 * @Version 1.0
 */
public class ResultTemplate<T> {

    private T data;
    private int code;
    private String message;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
