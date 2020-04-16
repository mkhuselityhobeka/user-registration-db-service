package com.funda.registration.db.service.RegistrationDBService.dto;

import java.io.Serializable;
import org.springframework.stereotype.Component;
import com.funda.registration.db.service.RegistrationDBService.entity.UserRegistration;

import lombok.Data;


@Component
@Data
public class PasswordDTO implements Serializable {


	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String password;
	private UserRegistration registration;
}
