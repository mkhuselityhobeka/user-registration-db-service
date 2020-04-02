package com.funda.registration.db.service.RegistrationDBService.service;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.funda.registration.db.service.RegistrationDBService.entity.UserRegistration;
import com.funda.registration.db.service.RegistrationDBService.entity.VerificationToken;
import com.funda.registration.db.service.RegistrationDBService.repository.UserRegistrationRepo;
import com.funda.registration.db.service.RegistrationDBService.repository.UserVerificationRepo;
import com.funda.registration.db.service.RegistrationDBService.service.exceptions.UsernameExistsException;
import com.funda.registration.db.service.RegistrationDBService.serviceInterface.UserRegistrationInterface;

@Service
@Transactional
public class UserRegistrtionService implements UserRegistrationInterface{

	@Autowired
	UserRegistrationRepo registrationRepo;
	
	@Autowired
	UserRegistrationInterface registrationInterface;
	
	@Autowired
	UserVerificationRepo userVerificationRepo;
	
	@Autowired
	VerificationToken verification;
	
	/*
	 register user
	 */
	public UserRegistration enrollUser(UserRegistration registration) throws UsernameExistsException{
		
		if(loadByUsername(registration.getUsername())) {
			
			throw new UsernameExistsException("this username exists " + registration.getUsername());
		
		}else {
			
			return registrationRepo.save(registration);
		}
		
	
		
	}
	
	/*
	 get all registered users
	 */
	public List <UserRegistration> getAllRegisteredUsers(){
		
		List <UserRegistration> listUserRegistration = registrationRepo.findAll();
		
		return listUserRegistration;
	}
	
	/*
	 * find if username does not exist
	 */
	public boolean loadByUsername(String username) {
		
		UserRegistration userReg = registrationRepo.findByUsername(username);
	
		if(userReg != null) {
		
			return true;
		
		}else {
			
			return false;
		}
		
	}

	
	
	@Override
	public UserRegistration getRegistration(String verificationToken) {
		
		UserRegistration registration = userVerificationRepo.findByToken(verificationToken).getRegistration();
		
		return registration;
	}

	
	@Override
	public VerificationToken getVerificationToken(String verificationToken) {
		
		VerificationToken token = userVerificationRepo.findByToken(verificationToken);
		
		return token;
	}

	
	@Override
	public void createVerificationToken(UserRegistration registration, String verificationToken) {
		
		verification = new VerificationToken(registration, verificationToken);
		
		userVerificationRepo.save(verification);
		
	}

	@Override
	public void saveRegisteredUser(UserRegistration registration) {
	
		registrationRepo.save(registration);
	}
	
	
	
}
