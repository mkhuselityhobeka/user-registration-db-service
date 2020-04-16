package com.funda.registration.db.service.RegistrationDBService.serviceInterface;

import com.funda.registration.db.service.RegistrationDBService.entity.UserRegistration;
import com.funda.registration.db.service.RegistrationDBService.entity.VerificationToken;

public interface UserRegistrationInterface  {
	
	UserRegistration getRegistration(String verificationToken);
	VerificationToken getVerificationToken(String verificationToken);
	
	void createVerificationToken(UserRegistration registration, String verificationToken);
	void saveRegisteredUser(UserRegistration registration);
	
	
	void saveupdatedPassword(UserRegistration registration, String password);

}
