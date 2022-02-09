package com.aliang.pojo;

import lombok.Data;

import java.io.Serializable;


public class BillType  implements Serializable {


    private Long id;


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
