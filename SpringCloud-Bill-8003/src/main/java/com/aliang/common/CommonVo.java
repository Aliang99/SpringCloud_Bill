package com.aliang.common;
import java.io.Serializable;


/**
 * 后端响应前端的封装对象
 */
public class CommonVo  implements Serializable {
    private int code;
    private String msg;
    private Object data;

    public CommonVo() {
    }

    public CommonVo(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public CommonVo(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "CommonVo{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
