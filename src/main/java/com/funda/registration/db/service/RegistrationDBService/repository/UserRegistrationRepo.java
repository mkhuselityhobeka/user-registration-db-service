package com.funda.registration.db.service.RegistrationDBService.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.funda.registration.db.service.RegistrationDBService.entity.UserRegistration;

public interface UserRegistrationRepo extends JpaRepository<UserRegistration, Long> {
	
	 UserRegistration findByUsername(String registration);

}
