package com.funda.registration.db.service.RegistrationDBService.service;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.funda.registration.db.service.RegistrationDBService.entity.UserRegistration;
import com.funda.registration.db.service.RegistrationDBService.repository.UserRegistrationRepo;
import com.funda.registration.db.service.RegistrationDBService.service.exceptions.UsernameExistsException;

@Service
@Transactional
public class UserRegistrtionService {

	@Autowired
	UserRegistrationRepo registrationRepo;
	
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
	
}
