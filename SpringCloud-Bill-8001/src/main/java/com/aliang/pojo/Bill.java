package com.aliang.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;


import java.io.Serializable;
import java.util.Date;


@TableName("bill_")
public class Bill  implements Serializable {
    @TableId(type= IdType.AUTO,value = "id_")
    private long id;

    @TableField("title_")
    private String title;

    @TableField("bill_time_")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GTM+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date billTime;

    @TableField("type_id_")
    private Long typeId;

    @TableField("price_")
    private Double price;

    @TableField("explain_")
    private String explain;

    /**
     * 查询条件封装
     */
    @TableField(exist = false)
    private String typeName;

    //@Transient
    @TableField(exist = false)
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GTM+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date beginTime;

    //@Transient
    @TableField(exist = false)
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GTM+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endTime;

    public Bill() {
    }

    public Bill(long id, String title, Date billTime, Long typeId, Double price, String explain, String typeName, Date beginTime, Date endTime) {
        this.id = id;
        this.title = title;
        this.billTime = billTime;
        this.typeId = typeId;
        this.price = price;
        this.explain = explain;
        this.typeName = typeName;
        this.beginTime = beginTime;
        this.endTime = endTime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getBillTime() {
        return billTime;
    }

    public void setBillTime(Date billTime) {
        this.billTime = billTime;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", billTime=" + billTime +
                ", typeId=" + typeId +
                ", price=" + price +
                ", explain='" + explain + '\'' +
                ", typeName='" + typeName + '\'' +
                ", beginTime=" + beginTime +
                ", endTime=" + endTime +
                '}';
    }
}
