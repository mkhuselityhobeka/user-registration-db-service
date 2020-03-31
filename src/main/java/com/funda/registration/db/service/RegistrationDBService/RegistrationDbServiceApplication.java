package com.funda.registration.db.service.RegistrationDBService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
public class RegistrationDbServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RegistrationDbServiceApplication.class, args);
	}

}
