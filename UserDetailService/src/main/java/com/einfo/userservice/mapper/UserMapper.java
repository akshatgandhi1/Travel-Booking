package com.einfo.userservice.mapper;

import com.einfo.userservice.Dto.UserDto;
import com.einfo.userservice.entity.User;

public class UserMapper {

	//map user to userDto
	public static UserDto mapToUserDto(User user) {
		UserDto dto=new UserDto(
				user.getUserid(),
				user.getUserName(),
				user.getUseremail(),
				user.getPassword(),
				user.getContact(),
				null
				);
		return dto;		
	}
	
	//map userDto to user
	public static User mapToUser(UserDto userDto) {
		User user=new User(
				userDto.getUserid(),
				userDto.getUserName(),
				userDto.getUseremail(),
				userDto.getPassword(),
				userDto.getContact()
				);
		return user;
	}
	
}
