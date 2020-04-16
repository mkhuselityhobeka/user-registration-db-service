package com.funda.registration.db.service.RegistrationDBService.serviceInterface;

import com.funda.registration.db.service.RegistrationDBService.entity.UserRegistration;

public interface SendResetPasswordTokenInterface {
	 void resetPasswordEmailMessage(UserRegistration registration);
}
