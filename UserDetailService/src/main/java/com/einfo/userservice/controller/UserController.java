package com.einfo.userservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.einfo.userservice.Dto.UserDto;
import com.einfo.userservice.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/create")
	@ResponseStatus(code = HttpStatus.CREATED)
	public UserDto createUser(@Valid @RequestBody UserDto user) {
		return userService.createUser(user);
	}
	
	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public List<UserDto> getAllUser() {
		return userService.getUsers();
	}
	
	@GetMapping("/{userid}")
	public UserDto getUserById(@PathVariable("userid") String userid) {
		return userService.getUserById(userid);
	}
	
	@PutMapping("/{userid}")
	public UserDto UpdateUserById(@PathVariable("userid") String userid,@RequestBody UserDto userDto) {
		userDto.setUserid(userid);
		return userService.updateUser(userDto);
	}
}
