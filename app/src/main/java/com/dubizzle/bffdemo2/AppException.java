package com.dubizzle.bffdemo2;

/**
 * Created by mac on 7/1/15.
 */
public class AppException extends Exception {
    private int code;

    public AppException(int code, String detailMessage) {
        super(detailMessage);
        this.code = code;
    }
    public AppException(int code) {
        super("");
        this.code = code;
    }
    /*public AppException(int code) {
        super();
        this.code = code;
    }*/

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
