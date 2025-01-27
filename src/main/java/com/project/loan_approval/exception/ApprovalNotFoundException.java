package com.project.loan_approval.exception;



public class ApprovalNotFoundException extends RuntimeException {



	    public ApprovalNotFoundException(String message) {
	        super(message);
	    }

	    public ApprovalNotFoundException(String message, Throwable cause) {
	        super(message, cause);
	    }
	

}

