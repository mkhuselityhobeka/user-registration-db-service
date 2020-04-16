package com.funda.registration.db.service.RegistrationDBService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.funda.registration.db.service.RegistrationDBService.entity.ResetPasswordToken;

public interface ResetPasswordTokenRepo extends JpaRepository<ResetPasswordToken, Long>{
	
	ResetPasswordToken findByToken(String resetPasswordToken);

}
