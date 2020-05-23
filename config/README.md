#Spring cloud Config
### 依赖
> starter-config-server
> 
> starter-web

主要通过
```@EnableConfigServer```
注入在Application上

配置Git地址
```xml
spring.cloud.config.server.git.uri=https://github.com/syqJoker/spring-cloud-config.git
#自动获取更新
spring.cloud.config.server.git.force-pull=true
```
再配置个端口
```server.port=8804```
，直接通过URL地址访问文件
>http://localhost:8084/application-dev.yml

如果访问成功，搞定了

===

在其他调用的方法上，首先依赖
> starter-config

然后再在yml文件中引用
```xml 
spring:
 cloud:
   config:
     name: config
     profile: dev
     uri: http://127.0.0.1:8084/
```
启动对应的服务，查看日志文件，里面出现
```
Fetching config from server at : http://127.0.0.1:8084/
```
应该就可以了