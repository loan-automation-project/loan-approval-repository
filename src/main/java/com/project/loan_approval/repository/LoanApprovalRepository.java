package com.project.loan_approval.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.loan_approval.entity.LoanApprovalEntity;

@Repository
public interface LoanApprovalRepository extends JpaRepository<LoanApprovalEntity, Long> {
}

