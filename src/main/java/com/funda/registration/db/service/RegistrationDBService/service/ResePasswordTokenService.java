package com.funda.registration.db.service.RegistrationDBService.service;

import java.util.Arrays;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.funda.registration.db.service.RegistrationDBService.entity.ResetPasswordToken;
import com.funda.registration.db.service.RegistrationDBService.entity.UserRegistration;
import com.funda.registration.db.service.RegistrationDBService.repository.ResetPasswordTokenRepo;
import com.funda.registration.db.service.RegistrationDBService.serviceInterface.ResetPasswordInterface;

import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;

@Service
@Transactional
@Slf4j
public class ResePasswordTokenService implements ResetPasswordInterface {

   @Autowired
   ResetPasswordTokenRepo  resetPasswordTokenRepo;

   /*
    reset token
    */
	@Override
	public void createResetPasswordToken(UserRegistration registration, String resetPasswordToken) {
	
		     ResetPasswordToken passwordToken = new ResetPasswordToken(registration, resetPasswordToken);
		     resetPasswordTokenRepo.save(passwordToken);
		     
	}

	@Override
	public String validateResetPassWordToken(long id, String token) {

          ResetPasswordToken passwordToken = resetPasswordTokenRepo.findByToken(token);
          log.info("reset user id is " + passwordToken.getRegistration().getId());
         // log.info("reset user token is " + passwordToken.getToken());
          log.info("reset user idddddd is " + id);
		  if((passwordToken == null) || (passwordToken.getRegistration().getId()!= id)){
			 
			  log.info("reset user token is " + passwordToken.getToken());
			  return " passwordToken: invalid token";
		  
		  }
			  
			  UserRegistration registration = passwordToken.getRegistration();
				 
			  Authentication authentication = new UsernamePasswordAuthenticationToken(registration, null, 
					                          Arrays.asList(new SimpleGrantedAuthority("CHANGE_PASSWORD_PRIVILEGE"))); // giving user to privilege to change their own password 
			  SecurityContextHolder.getContext().setAuthentication(authentication);
			  
			  return passwordToken.toString();
		 
		  
		
	}

		

}
