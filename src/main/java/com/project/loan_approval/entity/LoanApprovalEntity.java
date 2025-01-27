package com.project.loan_approval.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class LoanApprovalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long loanApprovalId;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "loan_id")
//    private LoanApplication loan; // Foreign Key to Loan
    private Long loanId;

    private Double interestRate;
    private Integer loanTenure;
    private String repaymentSchedule;
    private Double installationAmount;
    private LocalDate approvalDate;
    private Double approvalAmount;
    private String status;
    private LocalDate[] dueDate;

    public Long getLoanApprovalId() {
        return loanApprovalId;
    }

    public void setLoanApprovalId(Long loanApprovalId) {
        this.loanApprovalId = loanApprovalId;
    }

//    public Loan getLoan() {
//        return loan;
//    }
//
//    public void setLoan(Loan loan) {
//        this.loan = loan;
//    }
    
    public Long getLoanId() {
    	
    	return loanId;
    }
    public void setLoanId(Long loanId) {
    	this.loanId = loanId;
    }

    public Double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(Double interestRate) {
        this.interestRate = interestRate;
    }

    public Integer getLoanTenure() {
        return loanTenure;
    }

    public void setLoanTenure(Integer loanTenure) {
        this.loanTenure = loanTenure;
    }

    public String getRepaymentSchedule() {
        return repaymentSchedule;
    }

    public void setRepaymentSchedule(String repaymentSchedule) {
        this.repaymentSchedule = repaymentSchedule;
    }

    public Double getInstallationAmount() {
        return installationAmount;
    }

    public void setInstallationAmount(Double installationAmount) {
        this.installationAmount = installationAmount;
    }

    public LocalDate getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(LocalDate approvalDate) {
        this.approvalDate = approvalDate;
    }

    public Double getApprovalAmount() {
        return approvalAmount;
    }

    public void setApprovalAmount(Double approvalAmount) {
        this.approvalAmount = approvalAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate[] getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate[] dueDate) {
        this.dueDate = dueDate;
    }
}
