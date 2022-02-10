package com.aliang.service;

import com.aliang.mapper.BillMapper;
import com.aliang.pojo.Bill;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


@Service
public class BillService {

    @Resource
    private BillMapper mapper;

    /**
     * 分页查询
     * @param bill
     * @return
     */
    public PageInfo<Bill> getPage(Bill bill, int pageNum, int pageSize){
        return PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(() -> {
            mapper.select(bill);
        });
    }

    /**
     * 新增账单数据
     * @param bill
     * @return
     */
    @Transactional
    public int add(Bill bill){
        System.out.println("Service:"+bill);
        int insert = mapper.insert(bill);
        if (insert!=1){
            return 0;
        }
        return 1;
    }

    /**
     * 修改账单数据
     * @param bill
     * @return
     */
    @Transactional
    public int update(Bill bill){
        int update = mapper.updateById(bill);
        if (update!=1){
            return 0;
        }
        return 1;
    }

    /**
     * 删除账单数据
     * @param id
     * @return
     */
    @Transactional
    public int delete(Long id){
        int delete = mapper.deleteById(id);
        if (delete!=1){
            return 0;
        }
        return 1;
    }

    /**
     * 根据id查询账单信息
     * @param id
     * @return
     */
    public Bill selectById(Long id){
        return mapper.selectById(id);
    }
}
