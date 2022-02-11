package com.aliang.Controller;

import com.aliang.Common.CommonVo;
import com.aliang.pojo.Bill;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import jdk.jfr.Description;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.ribbon.RibbonLoadBalancerClient;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

@Controller
@DefaultProperties(defaultFallback = "defaultFallback") //指定默认服务降级方法
@Description(value = "该类中的方法不推荐使用，已使用Feign代理并自动请求，请使用BillByFeignController类")
@Deprecated
@RequestMapping("/desc")
public class BillController {
    @Resource
    private RestTemplate restTemplate;

    /*
            未引入Ribbon负载均衡时的写法：
                  @Resource
                  private DiscoveryClient discoveryClient;
     */

    // 引入Ribbon负载均衡后的写法，给出地址，调用时消费端Ribbon根据这个服务名称自动从服务端集群中选择一个去请求
    private static final String BILL_SERVICE_URL = "HTTP://Bill-Service" ;

    @GetMapping("test")
    @ResponseBody
    public CommonVo test(){
        return new CommonVo(200,"Test Success....",null);
    }

    /**
     * 根据id删除bill
     * @param id 要删除的bill id
     * @return
     * 开发与远程调用状态：OK
     */
    @DeleteMapping("/consumer/delete/{id}")
    @ResponseBody
    @HystrixCommand
    public CommonVo delete(@PathVariable("id") Long id) {
        /*
            未引入Ribbon负载均衡时的写法：
                  ServiceInstance serviceInstance = discoveryClient.getInstances("Bill-Service").get(0); //单机写法
                  ServiceInstance serviceInstance = discoveryClient.getInstances("Bill-Service");
                  String hostName = serviceInstance.getHost(); // 获取主机名称
                  int hostPort = serviceInstance.getPort(); //获取端口
                  String url = "http://" + hostName + ":" + hostPort + "/bill/delete/" + id; //拼接远程调用地址

         */
        return restTemplate.exchange(BILL_SERVICE_URL + "/bill/delete/" + id , HttpMethod.DELETE, null, CommonVo.class).getBody();
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

    /**
     * 根据id获取bill
     * @param id 需要查询的bill id
     * @return 
     * 开发与远程调用状态：OK
     */
    @GetMapping("/consumer/queryById/{id}")
    @ResponseBody
    @HystrixCommand
    public CommonVo queryById(@PathVariable("id") Long id) {
        /*
            未引入Ribbon负载均衡时的写法：
                  ServiceInstance serviceInstance = discoveryClient.getInstances("Bill-Service").get(0); //单机写法
                  ServiceInstance serviceInstance = client.choose("Bill-Service");
                  String host = serviceInstance.getHost();
                  int port = serviceInstance.getPort();
                  String url = "http://" + host + ":" + port + "/bill/queryById/{id}";

         */
        Map<String, Long> param = new HashMap<>();
        param.put("id", id);
        return restTemplate.getForObject(BILL_SERVICE_URL + "/bill/queryById/{id}", CommonVo.class, param);
    }

    /**
     * 更新bill
     * @return
     * 开发与远程调用状态：OK
     */
    @PostMapping("/consumer/update")
    @ResponseBody
    @HystrixCommand
    public CommonVo update(Bill bill) {
        System.out.println(bill);
        /*
            未引入Ribbon负载均衡时的写法：
                  ServiceInstance serviceInstance = discoveryClient.getInstances("Bill-Service").get(0); //单机写法
                  ServiceInstance serviceInstance = client.choose("Bill-Service");
                  String hostName = serviceInstance.getHost(); // 获取主机名称
                  int hostPort = serviceInstance.getPort(); //获取端口
                  String url = "http://" + hostName + ":" + hostPort + "/bill/update"; //拼接远程调用地址
         */
        HttpEntity<MultiValueMap<String, Object>> httpEntity = setRestParam(bill);
        return restTemplate.exchange(BILL_SERVICE_URL + "/bill/update", HttpMethod.POST, httpEntity, CommonVo.class).getBody();
    }

    /**
     * 用于封装RestTemplate POST类型的请求参数
     * @param bill bill添加、bill更新
     */
    public HttpEntity<MultiValueMap<String, Object>> setRestParam(Bill bill) {
        MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
        if (bill.getId() > 0) {
            param.add("id", bill.getId());
        }
        if (bill.getTitle() != null && !Objects.equals(bill.getTitle(), "")) {
            param.add("title", bill.getTitle());
        }
        if (bill.getBillTime() != null) {
            param.add("billTime", bill.getBillTime() != null ? new SimpleDateFormat("yyyy-MM-dd").format(bill.getBillTime()) : null); //时间格式转换
            System.out.println("时间：" + bill.getBillTime().toString());
        }
        if (bill.getTypeId() != null) {
            param.add("typeId", bill.getTypeId() <= 0 ? null : bill.getTypeId());
        }
        if (bill.getPrice() != null) {
            param.add("price", bill.getPrice());
        }
        if (bill.getExplain() != null) {
            param.add("explain", bill.getExplain());
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED); //设置请求头为表单提交
        return new HttpEntity<>(param, headers);
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
     * 获取类型列表
     * @return
     * 开发与远程调用状态：OK
     */
    @GetMapping("/consumer/getTypes")
    @ResponseBody
    @HystrixCommand
    public CommonVo getTypes() {
        System.out.println("获取类型列表方法被访问了....");
        /*
            未引入Ribbon负载均衡时的写法：
                  ServiceInstance serviceInstance = discoveryClient.getInstances("Bill-Service").get(0);//单机写法
                  ServiceInstance serviceInstance = client.choose("Bill-Service");
                  String hostName = serviceInstance.getHost(); // 获取主机名称
                  int hostPort = serviceInstance.getPort(); //获取端口
                  String url = "http://" + hostName + ":" + hostPort; //拼接远程调用地址
         */
        return restTemplate.getForObject(BILL_SERVICE_URL + "/bill/getTypes", CommonVo.class);
    }

    /**
     * 添加bill
     */
    @PutMapping("/consumer/add")
    @ResponseBody
    @HystrixCommand
    public CommonVo add(Bill bill) {
        System.out.println("添加方法被访问了....");
        /*
            未引入Ribbon负载均衡时的写法：
              ServiceInstance serviceInstance = discoveryClient.getInstances("Bill-Service").get(0);//单机写法
              ServiceInstance serviceInstance = client.choose("Bill-Service");
              String hostName = serviceInstance.getHost(); // 获取主机名称
              int hostPort = serviceInstance.getPort(); //获取端口
              String url = "http://" + hostName + ":" + hostPort + "/bill/add"; //拼接远程调用地址

         */
        HttpEntity<MultiValueMap<String, Object>> httpEntity = setRestParam(bill);
        return restTemplate.exchange(BILL_SERVICE_URL + "/bill/add", HttpMethod.PUT, httpEntity, CommonVo.class).getBody();
    }

    /**
     * 分页+模糊查
     * @return
     * 开发与远程调用状态：OK
     */
    @GetMapping("/consumer/page")
    @ResponseBody
    @HystrixCommand
    public CommonVo page(@RequestParam(value = "pageNum",defaultValue = "1") int pageNum,
                         @RequestParam(value = "pageSize",defaultValue = "2") int pageSize,
                         Bill bill) {
        System.out.println("分页方法被访问了....");
        /*
            未引入Ribbon负载均衡时的写法：
               ServiceInstance serviceInstance = discoveryClient.getInstances("Bill-Service").get(0); //单机写法
               String hostName = serviceInstance.getHost(); // 获取主机名称
               int hostPort = serviceInstance.getPort(); //获取端口
               String url = "http://" + hostName + ":" + hostPort + "/bill/page"; //拼接远程调用地址
        */
        //拼接参数
        String urlParam = "?id={id}&title={title}&billTime={billTime}&typeId={typeId}&price={price}&explain={explain}&typeName={typeName}&beginTime={beginTime}&endTime={endTime}&pageNum={pageNum}&pageSize={pageSize}";
        Map<String, Object> param = new LinkedHashMap<>();
        param.put("id", bill.getId());
        param.put("title", bill.getTitle());
        param.put("billTime", bill.getBillTime() == null ? null : bill.getBillTime());
        param.put("typeId", bill.getTypeId());
        param.put("price", bill.getPrice());
        param.put("explain", bill.getExplain());
        param.put("typeName", bill.getTypeName());
        param.put("beginTime", bill.getBeginTime() == null ? null : bill.getBeginTime());
        param.put("endTime", bill.getEndTime() == null ? null : bill.getEndTime());
        param.put("pageNum", pageNum);
        param.put("pageSize", pageSize);
        return restTemplate.getForObject(BILL_SERVICE_URL + "/bill/page" + urlParam, CommonVo.class, param);
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
