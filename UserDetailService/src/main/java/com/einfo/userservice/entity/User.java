package com.einfo.userservice.entity;

import java.util.Collection;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document
public class User {

	@Id
	private String userid;
	@NotNull(message = "user name can not be empty")
	private String userName;
	@NotNull(message = "Email can not be empty")
	@Email
	private String useremail;
	@NotNull
	private String password;
	@NotNull
    @Size(min = 10, max = 10, message = "Phone number must be of 10 numbers")
	private String contact;
	
		
}
