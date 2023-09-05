package com.einfo.userservice.Dto;


import java.util.Collection;
import java.util.List;

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
public class UserDto implements UserDetails{

	private String userid;
	@NotNull(message = "user name can not be empty")
	private String userName;
	@NotNull(message = "Email can not be empty")
	@Email(message = "invalid email type")
	private String useremail;
	@NotNull
	private String password;
	@NotNull
    @Size(min = 10, max = 10, message = "Phone number must be of 10 numbers")
	private String contact;
	
	private List<BookingDetails> bookingDetails;
		
	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}
	@Override
	public String getUsername() {
		return this.useremail;
	}
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	@Override
	public boolean isEnabled() {
		return true;
	}
}
