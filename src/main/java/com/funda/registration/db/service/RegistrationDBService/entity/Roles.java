package com.funda.registration.db.service.RegistrationDBService.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.springframework.stereotype.Component;
import lombok.Data;


@Data
@Entity
@Component
public class Roles {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String roleType;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserRegistration registration;
}
