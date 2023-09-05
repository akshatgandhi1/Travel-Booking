package com.eic.bookingservice.service;

import java.util.List;

import com.eic.bookingservice.dto.BookingDto;

public interface BookingService {

	BookingDto createBooking(BookingDto bookingDto);
	
	List<BookingDto> getByUserId(String userid);
}
