package com.mksun.travel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(scanBasePackages = {"com.mksun.*"})
@EnableDiscoveryClient
public class UCenterApplication {

	public static void main(String[] args) {
		SpringApplication.run(UCenterApplication.class, args);
	}

}
