package com.einfo.userservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.einfo.userservice.Dto.BookingDetails;
import com.einfo.userservice.Dto.BookingDto;
import com.einfo.userservice.Dto.FlightDto;
import com.einfo.userservice.Dto.UserDto;
import com.einfo.userservice.Repository.UserRepository;
import com.einfo.userservice.entity.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository repository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public UserDto createUser(UserDto userDto) {

//		User user=UserMapper.mapToUser(userDto);
		User user = modelMapper.map(userDto, User.class);
		user.setPassword(encoder.encode(userDto.getPassword()));

		User saveUser = repository.save(user);

//		UserDto dto=UserMapper.mapToUserDto(saveUser);
		UserDto dto = modelMapper.map(saveUser, UserDto.class);

		return dto;
	}

	@Override
	public List<UserDto> getUsers() {
		List<User> user = repository.findAll();
//		return user.stream().map(UserMapper::mapToUserDto)
//				.collect(Collectors.toList());
		return user.stream().map((users) -> modelMapper.map(users, UserDto.class)).collect(Collectors.toList());

	}

	@Override
	public UserDto getUserById(String userid) {

		Optional<User> user = repository.findById(userid);
		UserDto userDto = modelMapper.map(user, UserDto.class);

		List<BookingDetails> bookingDetails = new ArrayList<>();

		ResponseEntity<List<BookingDto>> response = restTemplate.exchange(
				"http://localhost:8888/bookingservice/api/booking/" + userid, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<BookingDto>>() {
				});
		List<BookingDto> bookingDtoList = response.getBody();

		if (bookingDtoList != null) {

			bookingDtoList.forEach(booking -> {
				FlightDto flight = restTemplate
						.getForEntity("http://localhost:8888/flightservice/api/flight/" + booking.getFlightId(),
								FlightDto.class)
						.getBody();
				BookingDetails bookingDetail = new BookingDetails();
				bookingDetail.setFlightDetails(flight);
				bookingDetail.setBookingid(booking.getBookingId());

				bookingDetails.add(bookingDetail);
			});
			userDto.setBookingDetails(bookingDetails);
		}

		return userDto;
	}

	@Override
	public UserDto updateUser(UserDto userDto) {
		User user = repository.findById(userDto.getUserid()).get();
		user.setUserName(userDto.getUserName());
		user.setUseremail(userDto.getUseremail());
		user.setPassword(userDto.getPassword());
		user.setContact(userDto.getContact());

		User updateUser = repository.save(user);
//		return UserMapper.mapToUserDto(updateUser);
		return modelMapper.map(updateUser, UserDto.class);
	}

}
