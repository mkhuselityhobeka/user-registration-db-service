package com.funda.registration.db.service.RegistrationDBService.entity;


import java.lang.annotation.Annotation;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import lombok.Data;


@Data
@Entity
public class VerificationToken {
	
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String token;
	
	@OneToOne(targetEntity = UserRegistration.class, fetch= FetchType.EAGER)
	@JoinColumn(nullable = false, name="ID")
	private UserRegistration registration;
	private Date expiryDate;
	
	private static int epirationdata = 60*24;
	/*
	 * calculate expiration date in minutes
	 */
	private Date getExpirationDate(int expireationTime) {
		
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MINUTE, expireationTime);
		return new Date(calendar.getTime().getTime());
	}
}
