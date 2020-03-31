package com.funda.registration.db.service.RegistrationDBService.events;

import java.util.Locale;
import org.springframework.context.ApplicationEvent;
import com.funda.registration.db.service.RegistrationDBService.entity.UserRegistration;
import lombok.Getter;
import lombok.Setter;


@Setter@Getter
public class RegistrationCompleteEvent extends ApplicationEvent{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String appUrl;
	private Locale locale;
	private UserRegistration registration;

	
	public RegistrationCompleteEvent(UserRegistration registration, String appUrl, Locale locale) {
		
		super(registration);
		this.registration = registration;
		this.appUrl = appUrl;
		this.locale = locale;
		
		
	}

}
