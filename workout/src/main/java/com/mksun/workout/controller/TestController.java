package com.mksun.workout.controller;

import com.mksun.commons.entity.RtnJSON;
import com.mksun.commons.utils.RedisUtils;
import com.mksun.workout.feign.UCenterTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@RequestMapping("/test")
public class TestController {

    @Resource
    private RedisUtils redisUtils;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Resource
    private UCenterTestService ucenterService;

    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }

    @GetMapping("/helloFeign")
    public String helloFeign(@RequestParam(value = "name", defaultValue = "World") String name) {
        return ucenterService.hello(name);
    }


    @GetMapping("/helloUCenterService")
    public String helloUCenterService(@RequestParam(value = "name", defaultValue = "World") String name) {
        ServiceInstance serviceInstance = loadBalancerClient.choose("ucenter");
        RestTemplate restTemplate = new RestTemplate();
        String url = serviceInstance.getUri()+"/test/hello?name="+name;
        String result = restTemplate.getForObject(url,String.class);
        return String.format("From %s ;    Result is :《%s》", url,result);
    }

    @GetMapping("/queryRedisById")
    public RtnJSON queryRedisById(@RequestParam(value = "id", defaultValue = "") String id) {
        RtnJSON result = new RtnJSON();
        String redisResult = redisUtils.get(id);
        if(redisResult != null && !"".equalsIgnoreCase(redisResult)){
            result.setInfo(redisResult);
            return result;
        }else{
            return result.dataError("未找到数据");
        }
    }
}
