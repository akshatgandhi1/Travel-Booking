package com.einfo.userservice.service;

import java.util.List;

import com.einfo.userservice.Dto.UserDto;

public interface UserService {
	
	UserDto createUser(UserDto user);
	
	List<UserDto> getUsers();
	
	UserDto getUserById(String userid);
	
	UserDto updateUser(UserDto userDto);
	
}