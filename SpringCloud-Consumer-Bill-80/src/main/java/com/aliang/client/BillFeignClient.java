package com.aliang.client;

import com.aliang.Common.CommonVo;
import com.aliang.pojo.Bill;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;



@FeignClient("Bill-Service")
public interface BillFeignClient {


    @GetMapping("/bill/test")
    @ResponseBody
    public String test();

    /**
     * 分页+条件查
     *
     * @param pageNum
     * @param pageSize
     * @return 状态： OK
     */
    @GetMapping("/bill/page")
    @ResponseBody
    public CommonVo page(@RequestParam(value = "pageNum",defaultValue = "1") int pageNum,
                            @RequestParam(value = "pageSize",defaultValue = "2") int pageSize,
                            Bill bill) ;


    /**
     * 添加账单信息
     *
     * @param bill
     * @return 状态： OK
     */
    @PutMapping("/bill/add")
    @ResponseBody
    public CommonVo add(Bill bill) ;


    /**
     * 获取分类列表
     *
     * @return 状态：OK
     */
    @GetMapping("/bill/getTypes")
    @ResponseBody
    public CommonVo getTypes() ;


    /**
     * 根据id查询
     *
     * @param id
     * @return 状态： NO
     */
    @GetMapping("/bill/queryById/{id}")
    @ResponseBody
    public CommonVo queryById(@PathVariable("id") Long id) ;

    /**
     * 提交修改信息
     *
     * @param bill
     * @return 状态： NO
     */
    @PostMapping("/bill/update")
    @ResponseBody
    public CommonVo update(Bill bill) ;

    /**
     * 根据id删除bill
     *
     * @param id
     * @return 状态： NO
     */
    @DeleteMapping("/bill/delete/{id}")
    @ResponseBody
    public CommonVo delete(@PathVariable("id") Long id) ;

}
