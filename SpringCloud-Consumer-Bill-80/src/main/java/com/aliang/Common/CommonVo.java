package com.aliang.Common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 后端响应前端的封装对象
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonVo implements Serializable {

    private int code;
    private String msg;
    private Object Data;
}
