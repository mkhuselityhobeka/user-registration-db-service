package com.funda.registration.db.service.RegistrationDBService.entity;



import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import org.springframework.stereotype.Component;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Component
public class VerificationToken implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long token_id;
	
	private String token;
	
	@OneToOne(targetEntity = UserRegistration.class, fetch= FetchType.EAGER)
	@JoinColumn(nullable = false, name="user_id")
	private UserRegistration registration;
	private Date expiryDate;
	
	private static int epirationdata = 60*24;
	
	public VerificationToken(UserRegistration registration, String verificationToken) {
		this.registration = registration;
		this.token = verificationToken;
	}
	
	
	
	
	/*
	 * calculate expiration date in minutes
	 */
	private Date getExpirationDate() {
		
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MINUTE, epirationdata);
		return new Date(calendar.getTime().getTime());
	}
	
}
