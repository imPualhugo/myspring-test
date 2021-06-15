package com.banyuan.message;

public enum MessageData {
    NOT_LOGIN(0, "未登录"),
    REQUEST_SUCCESS(1, "请求成功"),
    REQUEST_FAILED(-1, "请求失败");

    private int code;
    private String message;

    MessageData() {
    }

    MessageData(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }


    public static ResponseData success() {
        return success(null);
    }

    public static ResponseData success(Object data) {
        ResponseData rd = new ResponseData();
        rd.setState(MessageData.REQUEST_SUCCESS.getCode());
        rd.setMessage(MessageData.REQUEST_SUCCESS.getMessage());
        rd.setData(data);
        return rd;
    }

    public static ResponseData failed(Object data) {
        ResponseData rd = new ResponseData();
        rd.setState(MessageData.REQUEST_FAILED.getCode());
        rd.setMessage(MessageData.REQUEST_FAILED.getMessage());
        rd.setData(data);
        return rd;
    }

    public static ResponseData failed() {
        return failed(null);
    }
}
