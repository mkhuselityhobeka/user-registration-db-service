package com.funda.registration.db.service.RegistrationDBService.service;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.funda.registration.db.service.RegistrationDBService.entity.ResetPasswordToken;
import com.funda.registration.db.service.RegistrationDBService.entity.UserRegistration;
import com.funda.registration.db.service.RegistrationDBService.entity.VerificationToken;
import com.funda.registration.db.service.RegistrationDBService.repository.UserRegistrationRepo;
import com.funda.registration.db.service.RegistrationDBService.repository.UserVerificationRepo;
import com.funda.registration.db.service.RegistrationDBService.service.exceptions.UsernameDoesNotExistException;
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
	
    @Autowired
    ResetPasswordToken passwordToken;
    
    BCryptPasswordEncoder bCryptPasswordEncoder = new  BCryptPasswordEncoder();
	
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
	
	
	public UserRegistration findByUserName(String username) {


		
		return registrationRepo.findByUsername(username);
	}

	/*
	 reset password
	 */
	public UserRegistration resetPassword(UserRegistration registration) throws UsernameDoesNotExistException {
		
		if(loadByUsername(registration.getUsername())) {
			
		    return registrationRepo.saveAndFlush(registration);
		
			
		}else {
			
			throw new UsernameDoesNotExistException(registration.getUsername() + "The username is not registered");
		}
		
		
	}
	
	/*
	 * get user registration by token
	 */
	
	
	@Override
	public UserRegistration getRegistration(String verificationToken) {
		
		UserRegistration registration = userVerificationRepo.findByToken(verificationToken).getRegistration();
		
		return registration;
	}

	/*
	 * find the verification token
	 */
	@Override
	public VerificationToken getVerificationToken(String verificationToken) {
		
		VerificationToken token = userVerificationRepo.findByToken(verificationToken);
		
		return token;
	}

	/*
	 * create the verification token
	 */
	@Override
	public void createVerificationToken(UserRegistration registration, String verificationToken) {
		
		verification = new VerificationToken(registration, verificationToken);
		
		userVerificationRepo.save(verification);
		
	}
	
	/*
	 * save the verified user after user clicks on confirmation email link
	 */

	@Override
	public void saveRegisteredUser(UserRegistration registration) {
	
		registrationRepo.save(registration);
	}

	/*
	 * save updated password 
	 */
	@Override
	public void saveupdatedPassword(UserRegistration registration, String password) {
		
		registration.setPassword(bCryptPasswordEncoder.encode(password));
		registrationRepo.save(registration);
	}

 
	
	
	
}
