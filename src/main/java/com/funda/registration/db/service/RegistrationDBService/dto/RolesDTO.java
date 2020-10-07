package com.funda.registration.db.service.RegistrationDBService.dto;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.stereotype.Component;

import lombok.Data;


@Data
@Component
public class RolesDTO {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String roleType;
	private UserRegistrationDTO registration;
}
