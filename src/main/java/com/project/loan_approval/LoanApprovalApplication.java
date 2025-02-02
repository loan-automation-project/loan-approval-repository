package com.project.loan_approval;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
public class LoanApprovalApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoanApprovalApplication.class, args);
	}

}
