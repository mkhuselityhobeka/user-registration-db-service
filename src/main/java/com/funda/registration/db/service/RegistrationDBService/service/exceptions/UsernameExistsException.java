package com.funda.registration.db.service.RegistrationDBService.service.exceptions;

@SuppressWarnings("serial")
public class UsernameExistsException extends Throwable{
	
	public UsernameExistsException(String username) {
		super(username);
	}

}
