package com.funda.registration.db.service.RegistrationDBService.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

    @Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
		    .withUser("")
		    .password("")
		    .roles("");
	}

	@Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
       
    	httpSecurity.authorizeRequests()
    	            .antMatchers("/,/save-password*")
    	            .permitAll()
    	            .and()
                    .authorizeRequests()
                    .antMatchers("/console/**")
                    .permitAll();
                     httpSecurity.csrf().disable();
                    httpSecurity.headers().frameOptions().disable();
     }
}