package com.mksun.workout.controller;

import com.mksun.commons.entity.RtnJSON;
import com.mksun.commons.entity.Test;
import com.mksun.commons.utils.RedisUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/test")
public class TestController {

    @Resource
    private RedisUtils redisUtils;

    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
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
