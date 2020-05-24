package com.mksun.workout.config;

import com.mksun.workout.role.MyRule;
import com.netflix.loadbalancer.BestAvailableRule;
import com.netflix.loadbalancer.IRule;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RibbonConfigBeans {
    @Bean
    @LoadBalanced
    RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Bean
    public IRule myRule(){
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>            ----------------------       myRule is running");
//        return new BestAvailableRule();
        return new MyRule();
    }

}
