package com.aliang.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BillPagesController {


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


    /**
     * 跳转到添加页面
     * @return
     * 开发与远程调用状态：OK
     */
    @GetMapping("/consumer/toAddPage")
    public String toAddPage() {
        System.out.println("跳转到添加页面被访问了....");
        //页面跳转
        return "/bill/add";
    }

    /**
     * 更新页面的跳转
     * @return
     * 开发与远程调用状态：OK
     */
    @GetMapping("/consumer/updatePage")
    public String updatePage() {
        System.out.println("跳转到更新页面被访问了....");
        return "/bill/update";
    }
}
