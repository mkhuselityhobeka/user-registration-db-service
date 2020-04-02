package com.funda.registration.db.service.RegistrationDBService.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.funda.registration.db.service.RegistrationDBService.entity.UserRegistration;
import com.funda.registration.db.service.RegistrationDBService.entity.VerificationToken;

public interface UserVerificationRepo extends JpaRepository<VerificationToken, Long> {
	
	VerificationToken findByToken(String token);
	
	VerificationToken findByRegistration(UserRegistration registration);
}
