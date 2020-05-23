package com.mksun.workout.feign;

import com.mksun.workout.feign.common.FeignServiceFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 查找ucenter提供商，并且做容错监听
 */
@FeignClient(value = "ucenter",fallbackFactory = FeignServiceFallbackFactory.class)
public interface UCenterTestService {
    @GetMapping(value = "/test/hello")
    String hello(@RequestParam(value = "name", defaultValue = "World") String name);

    @GetMapping(value = "/test/queryTests")
    String queryTests();

    @GetMapping(value = "/test/queryTestById")
    String queryTestById(@RequestParam(value = "id", defaultValue = "") String id);

}
