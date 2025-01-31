package com.project.loan_approval.service;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
//import com.project.loan_approval.client.LoanClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.project.loan_approval.entity.LoanApprovalEntity;
import com.project.loan_approval.entity.Notification;
import com.project.loan_approval.exception.ApprovalNotFoundException;
import com.project.loan_approval.repository.LoanApprovalRepository;
import com.project.loan_approval.repository.NotificationRepository;

@Service
public class LoanApprovalService {

    @Autowired
    private LoanApprovalRepository loanApprovalRepository;
    @Autowired
    private NotificationRepository notificationRepository;
    
    int applicationCount = 0;
    
    public LoanApprovalEntity addLoanApproval(LoanApprovalEntity loanApproval) {
    	
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

    public LocalDate[] calculateDueDates(int loanTenure) {
//        LocalDate[] dueDates = new LocalDate[loanTenure];
//        for (int i = 0; i < loanTenure; i++) {
//            dueDates[i] = LocalDate.now().plusMonths(i + 1);  
//        }
//        return dueDates;
    	
    //}
    	
        LoanApprovalEntity loan = loanApprovalRepository.findFirstByOrderByLoanApprovalIdDesc();
        LocalDate startDate = loan != null ? loan.getApprovalDate() : LocalDate.now();
        
        LocalDate[] dueDates = new LocalDate[loanTenure];
        for (int i = 0; i < loanTenure; i++) {
            dueDates[i] = startDate.plusMonths(i + 1);
        }
        return dueDates;
    }
    
    
    public double calculateMonthlyLoanAmount(double totalAmount , int tenureInMonths , double annualInterestRate)
    {
    	double monthlyInterestRate = (annualInterestRate / 12) / 100;
    	double emi = (totalAmount * monthlyInterestRate * Math.pow(1 + monthlyInterestRate , tenureInMonths))
    					/ (Math.pow(1 + monthlyInterestRate , tenureInMonths) - 1);
    	
    	emi = Math.round(emi * 100) / 100.0;
    	
    	return emi;
    	
    }
    
    @Scheduled(cron = "0 0 8 * * *") // Daily at 8 AM
    public void checkDueDateReminders() {
        List<LoanApprovalEntity> allLoans = loanApprovalRepository.findAll();
        LocalDate today = LocalDate.now();
        
        allLoans.forEach(loan -> {
            LocalDate[] dueDates = calculateDueDates(loan.getLoanTenure());
            
            Arrays.stream(dueDates)
                .filter(dueDate -> dueDate.minusDays(2).isEqual(today))
                .forEach(dueDate -> {
                    if (!notificationRepository.existsByUserIdAndDueDate(loan.getLoanId(), dueDate)) { // need to fetch customer id
                        Notification notification = new Notification();
                        notification.setUserId(loan.getLoanId());  // need to fetch customer id
                        notification.setMessage(String.format(
                            "Reminder: ₹%.2f payment due on %s",
                            loan.getInstallationAmount(),
                            dueDate.format(DateTimeFormatter.ofPattern("MMM dd"))
                        ));
                        notification.setDueDate(dueDate);
                        notification.setCreatedAt(LocalDateTime.now());
                        notificationRepository.save(notification);
                    }
                });
        });
    }
}
