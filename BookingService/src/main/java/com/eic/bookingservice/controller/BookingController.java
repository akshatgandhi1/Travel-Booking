package com.eic.bookingservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.eic.bookingservice.dto.BookingDto;
import com.eic.bookingservice.service.BookingService;


@RestController
@RequestMapping("/api/booking")
public class BookingController {

	@Autowired
	private BookingService bookingService;
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public BookingDto createnewBooking(@RequestBody BookingDto bookingDto) {
		return bookingService.createBooking(bookingDto);
	}
	
	@GetMapping("/{userid}")
	public ResponseEntity<List<BookingDto>> findbyuserid(@PathVariable("userid") String userid) {
		return ResponseEntity.ok( bookingService.getByUserId(userid));
	}
}
