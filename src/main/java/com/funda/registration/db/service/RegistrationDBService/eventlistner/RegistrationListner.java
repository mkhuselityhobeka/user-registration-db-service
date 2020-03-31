package com.funda.registration.db.service.RegistrationDBService.eventlistner;



import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;

/*
 * event listner class, listening to the events published by the RegistrationCompleteEvent class
 */

import org.springframework.context.ApplicationListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.funda.registration.db.service.RegistrationDBService.entity.UserRegistration;
import com.funda.registration.db.service.RegistrationDBService.events.RegistrationCompleteEvent;


@Component
public class RegistrationListner  implements ApplicationListener<RegistrationCompleteEvent> {

	
	private JavaMailSender javaMailSender;
	private UserRegistration registration;
	
	
	RegistrationListner(UserRegistration registration, JavaMailSender javaMailSender){
		
		this.javaMailSender = javaMailSender;
		this.registration = registration;
		
	}
	
	
	@Override
	public void onApplicationEvent(RegistrationCompleteEvent event) {
	
		registration = event.getRegistration();
		String verificationToken = UUID.randomUUID().toString();
	    URL url = null;
		try {
			url = new URL("http://localhost:4200/registration");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String recipientAddress = registration.getUsername();
		String subject = "REGISTRATION CONFORMATION";
		String message = "Hi " + registration.getSName() + "\n" + "\n" + "Thank you registering with us." + "Please click on the link "  + url + "to activate your account" + "\n" + "Kind regards" + "\n" + "Mkhuseli";
		
		SimpleMailMessage mailMessage  = new SimpleMailMessage();
		mailMessage.setTo(recipientAddress);
		mailMessage.setSubject(subject);
		mailMessage.setText(message);
				
		javaMailSender.send(mailMessage);
	}

}
