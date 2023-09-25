 package com.einfo.userservice.config;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

//import com.einfo.userservice.security.JwtAucthenticationFilter;
import com.einfo.userservice.security.SecurityJwtAuthenticationEntryPoint;

@Configuration
public class SecurityConfig {
	
	Logger logger =LoggerFactory.getLogger(SecurityConfig.class);


//	@Autowired
//	private JwtAucthenticationFilter  aucthenticationFilter;
	
	@Autowired
	private SecurityJwtAuthenticationEntryPoint authenticationEntryPoint;
	
	@Autowired
	public UserDetailsService detailsService;
	
	@Autowired
	private PasswordEncoder encoder;
	
//	@Bean
//	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
//		return configuration.getAuthenticationManager();
//	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		logger.info("inside security filter chain");
		http.csrf(csrf ->csrf.disable())
			.authorizeHttpRequests(auth-> auth
					.anyRequest().permitAll())
//			.exceptionHandling(ex->ex.authenticationEntryPoint(authenticationEntryPoint))
	.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
 		;
				
		return http.build();
	}
	
	
	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider= new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(detailsService);
		daoAuthenticationProvider.setPasswordEncoder(encoder);
		return daoAuthenticationProvider;
	}
}
