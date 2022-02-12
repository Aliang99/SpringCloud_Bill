# Bill-SpringCloud-Demo
## SpringCloud练手项目

### 目前进度：

🚩 整合Eureka(各个服务的注册与协作)

🚩 Eureka集群

🚩 服务端集群

🚩 整合Hystrix(服务端未响应时，将服务降级为本机处理，对本次请求快速做出响应)

🚩 整合Ribbon(集群模式下的负载均衡处理)

🚩 整合OpenFeign(注意项：feign对于get、post、put、delete等请求方式的处理)

🚩 整合Gateway(注意项：跨域请求的处理)

🚩 整合Config(将配置文件放置在远程仓库，与Bus组件协作时能动态更新配置文件信息，该配置文件所在的服务不需要重启)

🚩 html页面使用Ajax作跨域请求与数据回显，将请求发送到Gateway由Gateway处理。

本项目由Bill项目重构

原来的版本可以查看 

**[SpringBoot记账Demo](https://github.com/Aliang99/billProject)**

### 未完善：整合Bus(需要搭配消息队列使用，后续学习了消息队列后再考虑将Bus加入本项目)

...

## 后续计划：

### 使用SpringCloud Alibaba重构

目的：用于学习SpringCloud Alibaba

✏️使用Nacos替换Eureka、Config、Bus

✏️使用Sentinel替换Hystrix


**计划待定**
### 组件替换参考：

**组件替换参考图1：**

![image](https://user-images.githubusercontent.com/38972334/151257484-4daa6c34-f0dd-4233-bbd7-b58e6355ef13.png)

**组件替换参考图2：**

![image](https://user-images.githubusercontent.com/38972334/151257600-fbaa7e51-fa0d-4af9-8703-6dfb5d9e3730.png)

