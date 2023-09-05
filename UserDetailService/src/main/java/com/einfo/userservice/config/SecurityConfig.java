package com.einfo.userservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.einfo.userservice.security.JwtAucthenticationFilter;
import com.einfo.userservice.security.SecurityJwtAuthenticationEntryPoint;

@Configuration
public class SecurityConfig {

	@Autowired
	private JwtAucthenticationFilter  aucthenticationFilter;
	
	@Autowired
	private SecurityJwtAuthenticationEntryPoint authenticationEntryPoint;
	
	@Autowired
	private UserDetailsService detailsService;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		http.csrf(csrf ->csrf.disable())
			.authorizeHttpRequests(auth-> auth.requestMatchers("/api/user/**")
					.authenticated()
					.requestMatchers("/auth/login").permitAll()
					.requestMatchers("api/user/create").permitAll())
			.exceptionHandling(ex->ex.authenticationEntryPoint(authenticationEntryPoint))
			.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
 		;
		
		http.addFilterBefore(aucthenticationFilter,UsernamePasswordAuthenticationFilter.class); 
		
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
