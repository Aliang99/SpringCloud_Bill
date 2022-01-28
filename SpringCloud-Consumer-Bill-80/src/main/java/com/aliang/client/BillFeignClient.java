package com.aliang.client;

import com.aliang.Common.CommonVo;
import com.aliang.config.FeignConfiguration;
import com.aliang.pojo.Bill;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;



@FeignClient(value = "Bill-Service",configuration = FeignConfiguration.class)
public interface BillFeignClient {


    @GetMapping("/bill/test")
    @ResponseBody
    String test();

    /**
     * 分页+条件查
     *
     * @return 状态： OK
     */
    @GetMapping("/bill/page")
    @ResponseBody
    CommonVo page(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                  @RequestParam(value = "pageSize", defaultValue = "2") int pageSize,
                  Bill bill) ;


    /**
     * 添加账单信息
     *
     * @return 状态： OK
     */
    @PutMapping("/bill/add")
    @ResponseBody
    CommonVo add(Bill bill) ;


    /**
     * 获取分类列表
     *
     * @return 状态：OK
     */
    @GetMapping("/bill/getTypes")
    @ResponseBody
    CommonVo getTypes() ;


    /**
     * 根据id查询
     *
     * @return 状态： NO
     */
    @GetMapping("/bill/queryById/{id}")
    @ResponseBody
    CommonVo queryById(@PathVariable("id") Long id) ;

    /**
     * 提交修改信息
     *
     * @return 状态： NO
     */
    @PostMapping("/bill/update")
    @ResponseBody
    CommonVo update(Bill bill) ;

    /**
     * 根据id删除bill
     *
     * @return 状态： NO
     */
    @DeleteMapping("/bill/delete/{id}")
    @ResponseBody
    CommonVo delete(@PathVariable("id") Long id) ;

}
