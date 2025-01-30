package com.project.loan_approval.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.project.loan_approval.pojo.LoanApplicationPojo;


@FeignClient(name = "loan-application-service", url = "http://localhost:1093")
public interface ApiClient {
	
    @GetMapping("/applications/{id}")
    LoanApplicationPojo getLoanApplication(@PathVariable Long id);
    
    
}