package com.funda.registration.db.service.RegistrationDBService.entity;


import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import org.springframework.stereotype.Component;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;





@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Component
public class ResetPasswordToken implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="reset_token_id")
	private Long id;
	
	private String token;
	
	@OneToOne(targetEntity = UserRegistration.class , fetch = FetchType.EAGER)
	@JoinColumn(name= "user_id")
	private UserRegistration registration;
	
	
	public ResetPasswordToken(UserRegistration registration, String token){
		this.registration = registration;
		this.token = token;
	}
	
}
