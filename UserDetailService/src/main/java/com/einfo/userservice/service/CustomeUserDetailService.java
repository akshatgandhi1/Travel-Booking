package com.einfo.userservice.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.einfo.userservice.Repository.UserRepository;
import com.einfo.userservice.entity.User;
import com.einfo.userservice.jwtmodel.JwtRequest;

@Service
public class CustomeUserDetailService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<User> user = userRepository.findByUseremail(username);
		return user.map(JwtRequest::new).orElseThrow();
	}

}