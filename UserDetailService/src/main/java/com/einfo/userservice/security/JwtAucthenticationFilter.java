package com.einfo.userservice.security;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.einfo.userservice.exception.InvalidHeaderException;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAucthenticationFilter extends OncePerRequestFilter{

	private Logger logger= LoggerFactory.getLogger(OncePerRequestFilter.class); 

	@Autowired
	private JwtHelper helper;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String requestHeader=request.getHeader("Authorization");
		logger.info("Header :{} ",requestHeader);
		
		String username=null;
		String token=null;
		
		if(requestHeader!=null && requestHeader.startsWith("Bearer")) {
			token=requestHeader.substring(7);
			try {
				username=this.helper.getUsernameFromToken(token);
				
			} catch (IllegalArgumentException e) {
				logger.info("Illegal Argument While featching the username");
				e.printStackTrace();
			}catch (ExpiredJwtException e) {
				logger.info("Token is expired");
				e.printStackTrace();
			}catch (MalformedJwtException e) {
				logger.info("sme changed has done in token");
				e.printStackTrace();
			}catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		else {
			logger.info("invalid Header value !!");
		}
		
		if (username!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
			
			UserDetails details=this.userDetailsService.loadUserByUsername(username);
			Boolean validateToken = this.helper.validateToken(token, details);
			if (validateToken) {
				UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(details,null, details.getAuthorities());
				authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			}
			else {
				logger.info("Validation Failed ");
				
			}		
		}	
		
		filterChain.doFilter(request, response);
	} 

}
