# Ribbon负载均衡 + Feign声明式接口调用

> ### 首先定义了一个远程Service接口
```java
/**
 * 查找ucenter服务，并且做容错监听，自定义负载
 */
@RibbonClient(name="ucenter",configuration = RibbonConfigBeans.class)
@FeignClient(value = "ucenter",fallbackFactory = FeignServiceFallbackFactory.class)
public interface UCenterTestService {
    @GetMapping(value = "/test/hello")
    String hello(@RequestParam(value = "name", defaultValue = "World") String name);

    @GetMapping(value = "/test/queryTests")
    String queryTests();

    @GetMapping(value = "/test/queryTestById")
    String queryTestById(@RequestParam(value = "id", defaultValue = "") String id);

}
```



> ### Ribbon负载均衡
1.  自定义负载均衡规则，查看源码可以看到一些信息，主要还是通过实现IRule接口，实现```choose()```方法
2.  定义```RibbonConfigBeans```类，通过```@Configuration```构造，代码如下
    ```java
    @Configuration
    public class RibbonConfigBeans {
        @Bean
        @LoadBalanced
        RestTemplate restTemplate(){
            return new RestTemplate();
        }
        @Bean
        public IRule myRule(){
            System.out.println("----------------------       myRule is running");
            return new MyRule();
        }
    }
    ```
3.  在需要远程调用的service接口，增加注释
    ```java
    @RibbonClient(name="ucenter",configuration = RibbonConfigBeans.class)
    ```
4.  在启动application后，会看到日志里打印出
    ```
    ----------------------       myRule is running
    ```

###### 疑问点还是有的，暂时没有想明白
> 在Debug模式的时候，如果我在```MyRule.choose(object key)```里的参数```key```是```null```，
当Debug模式的时候问题就出现了，会直接跳转到我自定义的```FeignServiceFallbackFactory```抛出了异常。。。。。。
但是如果不打断点，直接跳转，却会输入正确结果。
这个原因可能需要研究debug的工作模式，再去下结论了。


> ### Feign声明式接口调用
1.  简单的做了一下容错，通过fallbackFactory来实现的
    ```java
    @Component
    public class FeignServiceFallbackFactory implements FallbackFactory<UCenterTestService> {
    
        @Override
        public UCenterTestService create(Throwable throwable) {
            System.out.println("balabala...... FeignServiceFallbackFactory   "+throwable.getMessage());
            String errorCode = "404";
            return new UCenterTestService(){
                @Override
                public String hello(String name) {
                    return errorCode;
                }
    
                @Override
                public String queryTests() {
                    return errorCode;
                }
    
                @Override
                public String queryTestById(String id) {
                    return id+"   "+errorCode;
                }
            };
        }
    }
    ```
2.  在远程service接口注入
    ```java
    @FeignClient(value = "ucenter",fallbackFactory = FeignServiceFallbackFactory.class)
    ```
