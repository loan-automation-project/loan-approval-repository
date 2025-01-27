package com.project.loan_approval.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class LoanApplicationPojo {

	private Long LoanApplicationId;
	private String fullName;
	private String loanType;
	private String contact_info;
	private String address;
	
	
	
}
