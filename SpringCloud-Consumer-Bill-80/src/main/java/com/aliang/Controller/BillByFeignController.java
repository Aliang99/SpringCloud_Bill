package com.aliang.Controller;

import com.aliang.Common.CommonVo;
import com.aliang.client.BillFeignClient;
import com.aliang.pojo.Bill;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.ParseException;

@Controller
@ResponseBody
@RequestMapping("/consumer")
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
     * 更新页面的跳转
     * @return
     * 开发与远程调用状态：OK
     */
    @GetMapping("/updatePage")
    public String updatePage() {
        System.out.println("跳转到更新页面被访问了....");
        return "/bill/update";
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
     * @param bill
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
     * 跳转到添加页面
     * @return
     * 开发与远程调用状态：OK
     */
    @GetMapping("/toAddPage")
    public String toAddPage() {
        System.out.println("跳转到添加页面被访问了....");
        //页面跳转
        return "/bill/add";
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
     * @param bill
     * @return
     * @throws ParseException
     * 开发与远程调用状态：OK
     */
    @PutMapping("/add")
    @ResponseBody
    @HystrixCommand
    public CommonVo add(Bill bill) throws ParseException {
       return client.add(bill);
    }


    /**
     * 分页+模糊查
     * @param pageNum
     * @param pageSize
     * @param bill
     * @return
     * 开发与远程调用状态：OK
     */
    @GetMapping("/page")
    @ResponseBody
    @HystrixCommand
    public CommonVo page(@RequestParam(value = "pageNum",defaultValue = "1") int pageNum,
                         @RequestParam(value = "pageSize",defaultValue = "2") int pageSize,
                         Bill bill) {
        return client.page(pageNum, pageSize, bill);
    }


    /**
     * 跳转到主页
     * @return
     * 开发与远程调用状态：OK
     */
    @GetMapping("/")
    public String index() {
        System.out.println("主页被访问了....");
        return "/bill/list-page";
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
