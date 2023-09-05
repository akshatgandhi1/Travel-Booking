package com.einfo.userservice.mapper;

import com.einfo.userservice.Dto.UserDto;
import com.einfo.userservice.entity.User;

public class UserMapper {

	//map user to userDto
//	public static UserDto mapToUserDto(User user) {
//		UserDto dto=new UserDto(
//				user.getUserid(),
//				user.getUsername(),
//				user.getUseremail(),
//				user.getPassword(),
//				user.getContact()
//				);
//		return dto;		
//	}
	
	//map userDto to user
	public static User mapToUser(UserDto userDto) {
		User user=new User(
				userDto.getUserid(),
				userDto.getUsername(),
				userDto.getUseremail(),
				userDto.getPassword(),
				userDto.getContact()
				);
		return user;
	}
	
}
