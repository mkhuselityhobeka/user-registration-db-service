package com.funda.registration.db.service.RegistrationDBService.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.servlet.AsyncContext;
import javax.servlet.DispatcherType;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpUpgradeHandler;
import javax.servlet.http.Part;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.funda.registration.db.service.RegistrationDBService.dto.PasswordDTO;
import com.funda.registration.db.service.RegistrationDBService.dto.RolesDTO;
import com.funda.registration.db.service.RegistrationDBService.dto.UserRegistrationDTO;
import com.funda.registration.db.service.RegistrationDBService.entity.Roles;
import com.funda.registration.db.service.RegistrationDBService.entity.UserRegistration;
import com.funda.registration.db.service.RegistrationDBService.entity.VerificationToken;
import com.funda.registration.db.service.RegistrationDBService.events.RegistrationCompleteEvent;
import com.funda.registration.db.service.RegistrationDBService.service.ResePasswordTokenService;
import com.funda.registration.db.service.RegistrationDBService.service.ResetPasswordEmailService;
import com.funda.registration.db.service.RegistrationDBService.service.UserRegistrtionService;
import com.funda.registration.db.service.RegistrationDBService.service.exceptions.UsernameDoesNotExistException;
import com.funda.registration.db.service.RegistrationDBService.service.exceptions.UsernameExistsException;
import lombok.extern.slf4j.Slf4j;



@RestController
@RequestMapping("/funda")
@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
public class UserRegistrationController {
	
	  private ObjectMapper mapper = new ObjectMapper(); 
	  private DozerBeanMapper beanMapper = new DozerBeanMapper();
	
  	  UserRegistrationDTO userRegistrationDTO;
      RolesDTO rolesDTO ;
	  UserRegistration registration ;
	  Roles roles;
	  UserRegistrtionService registrtionService;
		
	  
	  BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(); // password encode
      
	  @Autowired 
	  ResetPasswordEmailService emailService;
     
      HttpServletRequest request = new HttpServletRequest() {
		
		@Override
		public AsyncContext startAsync(ServletRequest servletRequest, ServletResponse servletResponse)
				throws IllegalStateException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public AsyncContext startAsync() throws IllegalStateException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public void setCharacterEncoding(String env) throws UnsupportedEncodingException {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void setAttribute(String name, Object o) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void removeAttribute(String name) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public boolean isSecure() {
			// TODO Auto-generated method stub
			return false;
		}
		
		@Override
		public boolean isAsyncSupported() {
			// TODO Auto-generated method stub
			return false;
		}
		
		@Override
		public boolean isAsyncStarted() {
			// TODO Auto-generated method stub
			return false;
		}
		
		@Override
		public ServletContext getServletContext() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public int getServerPort() {
			// TODO Auto-generated method stub
			return 0;
		}
		
		@Override
		public String getServerName() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public String getScheme() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public RequestDispatcher getRequestDispatcher(String path) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public int getRemotePort() {
			// TODO Auto-generated method stub
			return 0;
		}
		
		@Override
		public String getRemoteHost() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public String getRemoteAddr() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public String getRealPath(String path) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public BufferedReader getReader() throws IOException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public String getProtocol() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public String[] getParameterValues(String name) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public Enumeration<String> getParameterNames() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public Map<String, String[]> getParameterMap() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public String getParameter(String name) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public Enumeration<Locale> getLocales() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public Locale getLocale() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public int getLocalPort() {
			// TODO Auto-generated method stub
			return 0;
		}
		
		@Override
		public String getLocalName() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public String getLocalAddr() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public ServletInputStream getInputStream() throws IOException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public DispatcherType getDispatcherType() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public String getContentType() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public long getContentLengthLong() {
			// TODO Auto-generated method stub
			return 0;
		}
		
		@Override
		public int getContentLength() {
			// TODO Auto-generated method stub
			return 0;
		}
		
		@Override
		public String getCharacterEncoding() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public Enumeration<String> getAttributeNames() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public Object getAttribute(String name) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public AsyncContext getAsyncContext() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public <T extends HttpUpgradeHandler> T upgrade(Class<T> httpUpgradeHandlerClass)
				throws IOException, ServletException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public void logout() throws ServletException {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void login(String username, String password) throws ServletException {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public boolean isUserInRole(String role) {
			// TODO Auto-generated method stub
			return false;
		}
		
		@Override
		public boolean isRequestedSessionIdValid() {
			// TODO Auto-generated method stub
			return false;
		}
		
		@Override
		public boolean isRequestedSessionIdFromUrl() {
			// TODO Auto-generated method stub
			return false;
		}
		
		@Override
		public boolean isRequestedSessionIdFromURL() {
			// TODO Auto-generated method stub
			return false;
		}
		
		@Override
		public boolean isRequestedSessionIdFromCookie() {
			// TODO Auto-generated method stub
			return false;
		}
		
		@Override
		public Principal getUserPrincipal() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public HttpSession getSession(boolean create) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public HttpSession getSession() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public String getServletPath() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public String getRequestedSessionId() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public StringBuffer getRequestURL() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public String getRequestURI() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public String getRemoteUser() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public String getQueryString() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public String getPathTranslated() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public String getPathInfo() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public Collection<Part> getParts() throws IOException, ServletException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public Part getPart(String name) throws IOException, ServletException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public String getMethod() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public int getIntHeader(String name) {
			// TODO Auto-generated method stub
			return 0;
		}
		
		@Override
		public Enumeration<String> getHeaders(String name) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public Enumeration<String> getHeaderNames() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public String getHeader(String name) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public long getDateHeader(String name) {
			// TODO Auto-generated method stub
			return 0;
		}
		
		@Override
		public Cookie[] getCookies() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public String getContextPath() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public String getAuthType() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public String changeSessionId() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public boolean authenticate(HttpServletResponse response) throws IOException, ServletException {
			// TODO Auto-generated method stub
			return false;
		}
	};
      
	  @Autowired  
	 
	  ApplicationEventPublisher applicationEventPublisher;
      
	  @Autowired
	  ResePasswordTokenService resePasswordTokenService;
	  
	  public  UserRegistrationController( UserRegistration registration, UserRegistrationDTO userRegistrationDTO,
			                         UserRegistrtionService registrtionService){
		  this.registration = registration;
		  this.userRegistrationDTO = userRegistrationDTO;
		  this.registrtionService = registrtionService;
	  }
  
	  /**
	 * 
	    read data from USER_REGISTRATION queue , store it database and publish event
	 * @throws UsernameExistsException 
	 * @throws JsonProcessingException 
	 * @throws JsonMappingException 
	 */
	
	@JmsListener(destination = "USER_REGISTRATION")
	 public ResponseEntity<String> saveUser(@RequestBody String message) throws JsonMappingException, JsonProcessingException {
					
			userRegistrationDTO = mapper.readValue(message, UserRegistrationDTO.class);
			Collection<RolesDTO> rolesDTOCollection = userRegistrationDTO.getRoles();

			for(RolesDTO rolesDTO : rolesDTOCollection) {
				rolesDTO.getId();
				rolesDTO.getRoleType();
				rolesDTO.getRegistration();
				roles = beanMapper.map(rolesDTO, Roles.class);
			}
			
			registration = beanMapper.map(userRegistrationDTO, UserRegistration.class);
		    Collection<Roles> rolesList = new ArrayList<Roles>();
		    rolesList.add(roles);
			log.info("read data from user regitration queue " + registration);
          
			try {
				
				registration.setPassword(bCryptPasswordEncoder.encode(registration.getPassword()));
				registration.setRoles(rolesList);
				registration = registrtionService.enrollUser(registration);
				
				if(registration ==  null) {
				
				   log.info("old registration is " + registration);
				
				}
				
				
				
				String appUrl = request.getContextPath();
				applicationEventPublisher.publishEvent(new RegistrationCompleteEvent(registration, appUrl, request.getLocale())); // pulbish registrtion event
				log.info("new registration is " + registration);
			    }catch (UsernameExistsException usernameExistsException) {
            	
            	usernameExistsException.printStackTrace();
			    }
						
			return ResponseEntity.ok("succesfully registered user " + registration);
		}
		
	
	/*
	 get all users
	 */
	@GetMapping("user/registration")
	public ResponseEntity<List<UserRegistration>> getAllUsers(){
		
		return ResponseEntity.ok(registrtionService.getAllRegisteredUsers());
	}

	/*
	 * confirm registration
	 */
	
	@GetMapping("user/conform-registration")
	public RedirectView confirmRegistration(@RequestParam("token") String token,RedirectView redirectView) {
		
		String url = "http://localhost:4200/confirm";
		redirectView.setUrl(url);
		
		VerificationToken verificationtoken = registrtionService.getVerificationToken(token);
		
		if(verificationtoken == null){
			//redirectView.setUrl(url);	
		
			return redirectView;
		
		}else {
			
			UserRegistration registration = verificationtoken.getRegistration();// get registered user from verification token
			registration.setEnabled(true); // user is verified
			registrtionService.saveRegisteredUser(registration);		
		
			
			 return redirectView; 
		}
		
		
	}
	
	/*
	 send password reset link
	 */
	@PostMapping("user/reset-password")
	public ResponseEntity<UserRegistration> sendpasswordResetlink(@RequestBody String username)  throws UsernameDoesNotExistException{
		
		try {
			
			registration = registrtionService.findByUserName(username);
			
			if(registration == null) {
				
				throw new UsernameDoesNotExistException(registration.getUsername() + "THIS USERNAME IS NOT REGISTERED");
			}
		
		}catch (UsernameDoesNotExistException usernameDoesNotExistException) {
			
			usernameDoesNotExistException.printStackTrace();
		}
		
		//registration.setPassword(registration.getPassword());
		emailService.resetPasswordEmailMessage(registration);
		
		
		return ResponseEntity.ok(registration);
		
	}
	
	/*
	 display change password page
	 */
	@GetMapping("user/change-password")
	public RedirectView displayResetPasswordPage(@RequestParam ("token") String token,@RequestParam("id") long id, RedirectView redirectView){
	            
		  String validatepasswordToken = resePasswordTokenService.validateResetPassWordToken(id, token);
		  String url = "http://localhost:4200/reset-password-page";
		  redirectView.setUrl(url);
	
		  log.info("validatepasswordToken is " + validatepasswordToken);
	     
		  if(validatepasswordToken == null) {

			  	log.info("This token is invalid or broken");
	    	  return redirectView;
	      }else {
	    	 
	    	  	log.info("This token is valid");
	    	  return redirectView;
	      }
		  
			
	}
	
	/*
	 *save updated password
	 */
	@PostMapping("user/save-password")
	public String savePassword(@RequestBody PasswordDTO passwordDTO){
		 
		UserRegistration registration = (UserRegistration) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		if(registration == null) {
			
			return "password could not be changed ...Please try again";
		}
		
		 registrtionService.saveupdatedPassword(registration, passwordDTO.getPassword());
		 
		 return "your password has been changes!!!";
	}
}
