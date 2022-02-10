package com.aliang.client;

import com.aliang.Common.CommonVo;
import com.aliang.config.FeignConfiguration;
import com.aliang.pojo.Bill;
import feign.Headers;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


/**
 * 使用Feign远程调用接口时，传递的参数为对象或者RquestBody类型参数，必须要加上@SpringQueryMap注解标注才可以传参
 */
@FeignClient(value = "Bill-Service",configuration = FeignConfiguration.class)
public interface BillFeignClient {


    @GetMapping("/bill/test")
    @ResponseBody
    String test();

    /**
     * 分页+条件查
     * @return 状态： OK
     */
    @PostMapping(value = "/bill/page",consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    CommonVo page(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                  @RequestParam(value = "pageSize", defaultValue = "2") int pageSize,
                  @RequestBody Bill bill) ;


    /**
     * 添加账单信息
     *
     * @return 状态： OK
     */
    @PutMapping("/bill/add")
    @ResponseBody
    CommonVo add(@RequestBody Bill bill) ;


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
    CommonVo update(@RequestBody Bill bill) ;

    /**
     * 根据id删除bill
     *
     * @return 状态： NO
     */


    @DeleteMapping("/bill/delete/{id}")
    @ResponseBody
    CommonVo delete(@PathVariable("id") Long id) ;

}
