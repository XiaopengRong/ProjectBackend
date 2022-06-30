package com.example.customer.exception;

public class DuplicateuserNameException extends IllegalStateException{
	private String userName;
	
	 public DuplicateuserNameException(String name) {
	        super("An account type with the userName '" + name + "' already exists.");
	        this.userName = userName;
	    }

	    public String getName(){
	        return userName;
	    }

}
