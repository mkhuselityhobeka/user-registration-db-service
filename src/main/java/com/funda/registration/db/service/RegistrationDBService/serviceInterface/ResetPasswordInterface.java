package com.funda.registration.db.service.RegistrationDBService.serviceInterface;

import com.funda.registration.db.service.RegistrationDBService.entity.ResetPasswordToken;
import com.funda.registration.db.service.RegistrationDBService.entity.UserRegistration;

public interface ResetPasswordInterface {
    
	
     void createResetPasswordToken(UserRegistration registration,String resetPasswordToken);
     String validateResetPassWordToken(long id,String tokken);
}
