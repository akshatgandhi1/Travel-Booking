package com.einfo.userservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.einfo.userservice.Dto.UserDto;
import com.einfo.userservice.service.UserService;

@RestController
public class XyzController {
	
	@Autowired
	UserService userService;
	
	@GetMapping("/getAll")
	@ResponseStatus(code = HttpStatus.OK)
	public List<UserDto> getAllUser() {
		return userService.getUsers();
	}

}
