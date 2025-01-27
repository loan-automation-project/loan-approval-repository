package com.project.loan_approval.service;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
//import com.project.loan_approval.client.LoanClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.project.loan_approval.entity.LoanApprovalEntity;
import com.project.loan_approval.exception.ApprovalNotFoundException;
import com.project.loan_approval.repository.LoanApprovalRepository;

@Service
public class LoanApprovalService {

    @Autowired
    private LoanApprovalRepository loanApprovalRepository;
    
    @Autowired
	private WebClient.Builder webClientBuilder;
    
    
    
    int applicationCount = 0;
    
    public LoanApprovalEntity addLoanApproval(LoanApprovalEntity loanApproval) {
    	
    	
	     int temp = webClientBuilder.build()
        .get()
        .uri("http://localhost:1091/loan/application" ) 
        .retrieve()
        .bodyToMono(Integer.class)
        .block();
	     
	     return loanApprovalRepository.saveAndFlush(loanApproval);
    	
    }
    
    public List<LoanApprovalEntity> getAllApprovals() {
    	return loanApprovalRepository.findAll();
    	
    }
    
    public LoanApprovalEntity getALoanApproval(Long id) {
    	Optional<LoanApprovalEntity> opt = loanApprovalRepository.findById(id);
    	if(opt.get()==null) {
    		throw new ApprovalNotFoundException("Approval form with id" + id + "do not exist");
    	}
    	return opt.get();
    }
    public LoanApprovalEntity updateApproval(LoanApprovalEntity loanApproval) {
    	Long id = loanApproval.getLoanApprovalId();
    	LoanApprovalEntity approval = getALoanApproval(id);
    	BeanUtils.copyProperties(loanApproval, approval);
    	return loanApprovalRepository.save(approval);
    }
    
    public String deleteApproval(Long id) {
    	loanApprovalRepository.deleteById(id);
    	return "Deleted Successfully";
    }



    // Helper method to calculate due dates based on loan tenure
    public LocalDate[] calculateDueDates(int loanTenure) {
        LocalDate[] dueDates = new LocalDate[loanTenure];
        for (int i = 0; i < loanTenure; i++) {
            dueDates[i] = LocalDate.now().plusMonths(i + 1);  // Monthly due dates
        }
        return dueDates;
    }
    
//    public 
}
