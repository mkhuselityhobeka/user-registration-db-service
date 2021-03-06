package com.funda.registration.db.service.RegistrationDBService.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name="User_Registration")
@Component
public class UserRegistration implements Serializable {
	private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long id;
	

//	@NotBlank(message = "name cannot be empty")
//	@NotNull
	private String sName;
	
	@NotBlank(message = "password cannot be empty")
	@NotNull
	private String password;
	
	@NotBlank(message = "slastName cannot be emty")
	@NotNull
	private String slastName;
	
	@NotBlank(message = "username cannot be empty")
	@NotNull
	@Column(unique = true)
	private String username;
	
	private boolean enabled;

	@OneToMany(
			    cascade = CascadeType.ALL,
			    fetch = FetchType.LAZY,
			    mappedBy = "registration")
	private Collection<Roles> roles;
}
