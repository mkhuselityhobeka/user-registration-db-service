package com.funda.registration.db.service.RegistrationDBService.service.exceptions;


@SuppressWarnings("serial")
public class UsernameDoesNotExistException  extends Throwable{

	public  UsernameDoesNotExistException(String username) {
		
		super(username);
	}
}
