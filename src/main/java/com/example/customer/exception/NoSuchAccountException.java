package com.example.customer.exception;

import java.util.NoSuchElementException;

public class NoSuchAccountException extends NoSuchElementException{

	private final Long AccountId;

    public NoSuchAccountException(Long AccountId) {
        super("No Account record for id = " + AccountId);
        this.AccountId = AccountId;
    }
}
