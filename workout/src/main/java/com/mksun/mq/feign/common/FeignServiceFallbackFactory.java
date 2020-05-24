package com.mksun.mq.feign.common;

import com.mksun.mq.feign.UCenterTestService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * 简单的容错
 * 当feign调用注册的服务后，停掉服务
 * 会返回404
 */
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
