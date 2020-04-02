package com.funda.registration.db.service.RegistrationDBService.eventlistner;



import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

/*
 * event listner class, listening to the events published by the RegistrationCompleteEvent class
 */

import org.springframework.context.ApplicationListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.funda.registration.db.service.RegistrationDBService.entity.UserRegistration;
import com.funda.registration.db.service.RegistrationDBService.events.RegistrationCompleteEvent;
import com.funda.registration.db.service.RegistrationDBService.service.UserRegistrtionService;


@Component
public class RegistrationListner  implements ApplicationListener<RegistrationCompleteEvent> {

	
	private JavaMailSender javaMailSender;
	private UserRegistration registration;

	@Autowired 
	UserRegistrtionService registrtionService;
	String verificationToken ="";
    URL url = null;
	
	RegistrationListner(UserRegistration registration, JavaMailSender javaMailSender){
		
		this.javaMailSender = javaMailSender;
		this.registration = registration;
		
	}
	
	
	@Override
	public void onApplicationEvent(RegistrationCompleteEvent event) {
	
	
		try {
			
			registration = event.getRegistration();
			verificationToken = UUID.randomUUID().toString();
			registrtionService.createVerificationToken(registration, verificationToken);
			url = new URL("http://localhost:8082/funda/user/conform-registration?token="+verificationToken );
			
		
		} catch (MalformedURLException e) {
		
			e.printStackTrace();
		}
		
		String recipientAddress = registration.getUsername();
		String subject = "REGISTRATION CONFORMATION";
		
		String message = "Hi " + registration.getSName() + "\n" + "\n" + "Thank you for registering with us. Please click on the link " 
		                              + url + " to activate your account." + '\n' +'\n'+ "Kind regards" + '\n' +'\n' +"Mkhuseli Tyhobeka";
		
		SimpleMailMessage mailMessage  = new SimpleMailMessage();
		mailMessage.setTo(recipientAddress);
		mailMessage.setSubject(subject);
		mailMessage.setText(message);
				
		javaMailSender.send(mailMessage);
	}

}
