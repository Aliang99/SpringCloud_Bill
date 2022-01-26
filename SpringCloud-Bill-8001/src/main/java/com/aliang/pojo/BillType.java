package com.aliang.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;


import java.io.Serializable;

@TableName("bill_type_")
public class BillType  implements Serializable {

    @TableId(value = "id_",type=IdType.AUTO)
    private Long id;

    @TableField("name_")
    private String name;

    public BillType(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public BillType() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "BillType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
