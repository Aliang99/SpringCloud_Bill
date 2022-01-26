package com.aliang.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Long id;
    //用户名
    private String userName;
    //密码
    private String password;
    //姓名
    private String name;
    //年龄
    private Integer age;
    //性别    取值:  1男 2女
    private Integer sex;
    //生日
    private Date birthday;
    //注册时间
    private Date created;
    //更新时间
    private Date updated;
    //备注
    private String note;
}


