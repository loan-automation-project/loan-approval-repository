package com.project.loan_approval.controller;


import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.loan_approval.entity.LoanApprovalEntity;
import com.project.loan_approval.service.LoanApprovalService;

@RestController
@RequestMapping("/loan/approval")
public class LoanApprovalController {

    @Autowired
    private LoanApprovalService loanApprovalService;

    @PostMapping("/add")
    public ResponseEntity<LoanApprovalEntity> addApproval(@RequestBody LoanApprovalEntity loanApproval  ){
    	
    	loanApproval.setInstallationAmount(loanApprovalService.calculateMonthlyLoanAmount(
    											loanApproval.getApprovalAmount(),
    											loanApproval.getLoanTenure(),
    											loanApproval.getInterestRate()
    											));
    	loanApproval.setStatus("Approved");
    	loanApproval.setDueDate(loanApprovalService.calculateDueDates(0));
    	return new ResponseEntity<LoanApprovalEntity>(loanApprovalService.addLoanApproval(loanApproval) , HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<LoanApprovalEntity> getALoanApproval(@PathVariable Long id){
    	return new ResponseEntity<LoanApprovalEntity>(loanApprovalService.getALoanApproval(id) , HttpStatus.OK);
    }
    
    @GetMapping("")
    public ResponseEntity<List<LoanApprovalEntity>> getAllLoanApproval(@PathVariable Long id){
    	return new ResponseEntity<List<LoanApprovalEntity>>(loanApprovalService.getAllApprovals() , HttpStatus.OK); 
    }
    
  
    
    @GetMapping("/dues")
    public ResponseEntity<LocalDate[]> getDueDates(@RequestParam int loanTenure) {
        LocalDate[] dueDates = loanApprovalService.calculateDueDates(loanTenure);
        return ResponseEntity.ok(dueDates); // Return with 200 OK status
    }
}

