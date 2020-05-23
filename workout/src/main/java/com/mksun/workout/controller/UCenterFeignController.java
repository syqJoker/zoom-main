package com.mksun.workout.controller;

import com.mksun.workout.feign.UCenterTestService;
import com.mksun.workout.feign.common.FeignServiceFallbackFactory;
import com.netflix.ribbon.proxy.annotation.Hystrix;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/feign")
public class UCenterFeignController {

    @Resource
    private UCenterTestService uCenterTestService;

    @GetMapping("/queryTests")
    String queryFeignTestList(){
        return uCenterTestService.queryTests();
    }

    @GetMapping("/queryTestById")
    String queryFeignTestById(@RequestParam(value = "id", defaultValue = "") String id){
        return uCenterTestService.queryTestById(id);
    }

    @GetMapping("/test")
    String testFeign(){
        return "Feign Test is ready";
    }

}
