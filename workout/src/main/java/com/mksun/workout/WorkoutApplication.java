package com.mksun.workout;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = {"com.mksun.*"})
@EnableDiscoveryClient
@EnableFeignClients
public class WorkoutApplication {

	public static void main(String[] args) {
		SpringApplication.run(WorkoutApplication.class, args);
	}

}
