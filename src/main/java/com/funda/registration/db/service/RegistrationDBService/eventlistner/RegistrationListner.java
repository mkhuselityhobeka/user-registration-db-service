package com.funda.registration.db.service.RegistrationDBService.eventlistner;



import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import com.funda.registration.db.service.RegistrationDBService.entity.UserRegistration;
import com.funda.registration.db.service.RegistrationDBService.events.RegistrationCompleteEvent;
import com.funda.registration.db.service.RegistrationDBService.service.UserRegistrtionService;


/*
 * event listner class, listening to the events published by the RegistrationCompleteEvent class
 */

@Component
public class RegistrationListner  implements ApplicationListener<RegistrationCompleteEvent> {

	
	private JavaMailSender javaMailSender;
	private UserRegistration registration;

	@Autowired 
	UserRegistrtionService registrtionService;
	String verificationToken ="";
	String resetPasswordToken= "";
    URL url = null;
	
	RegistrationListner(UserRegistration registration, JavaMailSender javaMailSender){
		
		this.javaMailSender = javaMailSender;
		this.registration = registration;
		
	}
	
	
	@Override
	public void onApplicationEvent(RegistrationCompleteEvent event) {
		
			registration = event.getRegistration();
			verificationToken = UUID.randomUUID().toString();
			registrtionService.createVerificationToken(registration, verificationToken);
			sendRegistrationConformationEmail(registration,verificationToken);
	
}

/*
 *send registration conformation email	
 */
	
	public void sendRegistrationConformationEmail(UserRegistration registration, String verificationToken) {
		
		String recipientAddress = registration.getUsername();
		String subject = "REGISTRATION CONFORMATION";
		String message = "";
		
		try {
			
			url = new URL("http://localhost:8082/funda/user/conform-registration?token="+verificationToken);
		
		}catch (MalformedURLException  malformedURLException) {
			malformedURLException.printStackTrace();
		}
	
	   message = "Hi " + registration.getSName() + "\n" + "\n" + "Thank you for registering with us. Please click on the link " 
                + url + " to activate your account." + '\n' +'\n'+ "Kind regards" + '\n' +'\n' +"Mkhuseli Tyhobeka";

	   
	    SimpleMailMessage mailMessage  = new SimpleMailMessage();
		mailMessage.setTo(recipientAddress);
		mailMessage.setSubject(subject);
		mailMessage.setText(message);
		javaMailSender.send(mailMessage);
		
	}
	
	
	/*
	 * 
	 * creating reset Token mail
	 */
	public void resetPasswordEmailMessage(UserRegistration registration, String resetPasswordToken ) {
		
		String recipientAddress = registration.getUsername();
		String subject = "RESET PASSWORD ! ";
		String message = "";
		
		try {
			
			url = new URL("http://localhost:8082/funda/user/reset-reset-password?token="+resetPasswordToken );
		
		}catch (MalformedURLException  malformedURLException) {
			malformedURLException.printStackTrace();
		}
	
	   message = "Hi " + registration.getSName() + "\n" + "\n" + "Thank you for registering with us. Please click on the link " 
                + url + " to activate your account." + '\n' +'\n'+ "Kind regards" + '\n' +'\n' +"Mkhuseli Tyhobeka";

	   
	    SimpleMailMessage mailMessage  = new SimpleMailMessage();
		mailMessage.setTo(recipientAddress);
		mailMessage.setSubject(subject);
		mailMessage.setText(message);
		javaMailSender.send(mailMessage);
		
	}
}
