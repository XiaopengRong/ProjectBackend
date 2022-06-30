package com.example.customer.exception;

public class DuplicateEmailException extends IllegalStateException {
	private String Email;
	
	 public DuplicateEmailException(String Email) {
	        super("An account type with the Email '" + Email + "' already exists.");
	        this.Email = Email;
	    }

	    public String getEmail(){
	        return Email;
	    }

}
