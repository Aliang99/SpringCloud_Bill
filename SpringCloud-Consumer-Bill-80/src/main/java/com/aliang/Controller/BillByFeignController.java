package com.aliang.Controller;

import com.aliang.Common.CommonVo;
import com.aliang.client.BillFeignClient;
import com.aliang.pojo.Bill;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/bill/consumer")
@DefaultProperties(defaultFallback = "defaultFallback") //指定默认服务降级方法
public class BillByFeignController {

    @Resource
    private BillFeignClient client;

    @GetMapping("test")
    @ResponseBody
    public String test(){
        return client.test();
    }

    /**
     * 根据id删除bill
     * @param id 要删除的bill id
     * @return
     * 开发与远程调用状态：OK
     */
    @DeleteMapping("/delete/{id}")
    @ResponseBody
    @HystrixCommand
    public CommonVo delete(@PathVariable("id") Long id) {
        return client.delete(id);
    }

    /**
     * 根据id获取bill
     * @param id 需要查询的bill id
     * @return
     * 开发与远程调用状态：OK
     */
    @GetMapping("/queryById/{id}")
    @ResponseBody
    @HystrixCommand
    public CommonVo queryById(@PathVariable("id") Long id) {
       return client.queryById(id);
    }

    /**
     * 更新bill
     * @return
     * 开发与远程调用状态：OK
     */
    @PostMapping("/update")
    @ResponseBody
    @HystrixCommand
    public CommonVo update(Bill bill) {
        return client.update(bill);
    }

    /**
     * 获取类型列表
     * @return
     * 开发与远程调用状态：OK
     */
    @GetMapping("/getTypes")
    @ResponseBody
    @HystrixCommand
    public CommonVo getTypes() {
       return client.getTypes();
    }

    /**
     * 添加bill
     * @throws ParseException
     * 开发与远程调用状态：OK
     */
    @PutMapping("/add")
    @ResponseBody
    @HystrixCommand
    public CommonVo add(Bill bill){
        System.out.println("Bill:"+bill);
       return client.add(bill);
    }

    /**
     * 分页+模糊查
     * @return
     * 开发与远程调用状态：OK
     */
    @GetMapping("/page")
    @ResponseBody
    @HystrixCommand
    public CommonVo page(@RequestParam(value = "pageNum",defaultValue = "1") int pageNum,
                         @RequestParam(value = "pageSize",defaultValue = "2") int pageSize,
                         @SpringQueryMap Bill bill) {
        return client.page(pageNum, pageSize, bill);
    }

    //服务降级的方法，返回提示信息
    public CommonVo queryByIdFallback(Long id) {
        return new CommonVo(400, "抱歉，网络拥堵，请稍后再试！！！", null);
    }

    //默认服务降级方法
    public CommonVo defaultFallback() {
        return new CommonVo(400, "中国移动提醒您，你请求的服务太拥堵，请稍后再试", null);
    }

    //服务降级的方法，返回提示信息
    public CommonVo AdviceFallback(){
        return new CommonVo(400,"抱歉，网络拥堵，请稍后再试！！！",null);
    }
}
