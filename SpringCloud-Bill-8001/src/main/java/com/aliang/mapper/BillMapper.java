package com.aliang.mapper;


import com.aliang.pojo.Bill;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

public interface BillMapper extends BaseMapper<Bill> {
    //使用配置文件，执行复杂sql
    public List<Bill> select(Bill bill);
}
