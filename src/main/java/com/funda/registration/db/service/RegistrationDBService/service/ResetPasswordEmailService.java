package com.funda.registration.db.service.RegistrationDBService.service;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import com.funda.registration.db.service.RegistrationDBService.entity.ResetPasswordToken;
import com.funda.registration.db.service.RegistrationDBService.entity.UserRegistration;
import com.funda.registration.db.service.RegistrationDBService.serviceInterface.SendResetPasswordTokenInterface;

@Service
public class ResetPasswordEmailService implements SendResetPasswordTokenInterface{

	 @Autowired
     JavaMailSender javaMailSender;
	 
	 @Autowired
	 ResetPasswordToken passwordToken;
	 
	 @Autowired
	 ResePasswordTokenService passwordTokenService;
	
	 String resetPasswordToken = "";
	 URL url = null;
	
	
	 
	 
	 /*

	 send reset password email
	 */
	 @Override
	public void resetPasswordEmailMessage(UserRegistration registration) {
		
		String recipientAddress = registration.getUsername();
		String subject = "RESET PASSWORD ! ";
		String message = "";
		resetPasswordToken = UUID.randomUUID().toString();
		passwordTokenService.createResetPasswordToken(registration, resetPasswordToken);
		
	
		try {
			
			url = new URL("http://localhost:8082/funda/user/change-password?id="+registration.getId() + "&token="+resetPasswordToken);
			
		
		}catch (MalformedURLException  malformedURLException) {
			malformedURLException.printStackTrace();
		}
	
	   message = "Hi " + registration.getSName() + "\n" + "\n" + "Forgot password. Please click on the link " 
                + url + " to reset password." + '\n' +'\n'+ "Kind regards" + '\n' +'\n' +"Mkhuseli Tyhobeka";

	   
	    SimpleMailMessage mailMessage  = new SimpleMailMessage();
		mailMessage.setTo(recipientAddress);
		mailMessage.setSubject(subject);
		mailMessage.setText(message);
		javaMailSender.send(mailMessage);
		
	}

	
}
	
	


