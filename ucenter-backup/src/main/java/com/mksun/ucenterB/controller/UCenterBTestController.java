package com.mksun.ucenterB.controller;

import com.mksun.commons.entity.RtnJSON;
import com.mksun.commons.entity.Test;
import com.mksun.ucenterB.service.UCenterBTestService;
import com.mksun.commons.utils.RedisUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/test")
public class UCenterBTestController {

    @Resource
    private UCenterBTestService UCenterBTestService;
    @Resource
    private RedisUtils redisUtils;

    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        System.out.println("hello "+name);
        return String.format("Hello %s!", name);
    }

    @GetMapping("/queryTests")
    public RtnJSON queryTests(@RequestParam(value = "id", defaultValue = "") String id) {
        RtnJSON result = new RtnJSON();
        result.setInfo(UCenterBTestService.queryTestList());
        return result;
    }

    @GetMapping("/queryTestById")
    public RtnJSON queryTestById(@RequestParam(value = "id", defaultValue = "") String id) {
        RtnJSON result = new RtnJSON();
        Test target = UCenterBTestService.queryTestById(id);
        if(target != null && target.getId() != null && !"".equalsIgnoreCase(target.getId())){
            Map<String,Object> resultInfo = new HashMap<>();
            String redisResult = redisUtils.get(target.getId());
            if(redisResult != null && !"".equalsIgnoreCase(redisResult)){

            }else{
                redisResult = target.getName();
                redisUtils.set(target.getId(),redisResult);
            }
            resultInfo.put("target",target);
            resultInfo.put("redis",redisResult);
            result.setInfo(resultInfo);
            return result;
        }else{
            return result.dataError("未找到数据");
        }
    }


    @PostMapping("/registerTest")
    public RtnJSON registerTest(Test test) {
        RtnJSON result = new RtnJSON();
        if(test.getCreateId() == null || "".equalsIgnoreCase(test.getCreateId())){
            test.setCreateId(test.getId());
        }
        if(test.getCreateTime() == null){
            test.setCreateTime(new Date());
        }
        int saveResult = UCenterBTestService.registerTest(test);
        if(saveResult>0){
            redisUtils.set(test.getId(),test.toString());
        }
        return result;
    }
}
