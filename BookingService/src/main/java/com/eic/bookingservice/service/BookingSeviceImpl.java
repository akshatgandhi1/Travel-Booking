package com.eic.bookingservice.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eic.bookingservice.dto.BookingDto;
import com.eic.bookingservice.entity.Booking;
import com.eic.bookingservice.repository.BookingRepository;

@Service
public class BookingSeviceImpl implements BookingService{

	
	@Autowired
	private BookingRepository bookingRepository;

	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public BookingDto createBooking(BookingDto bookingDto) {

		Booking newbooking=modelMapper.map(bookingDto, Booking.class);
		
		Booking saveBooking=bookingRepository.save(newbooking);
		
		BookingDto dto=modelMapper.map(saveBooking, BookingDto.class);
		
		return dto;
	}

	@Override
	public List<BookingDto> getByUserId(String userid) {
		List<Booking> bookings=bookingRepository.findByUserid(userid);
		return bookings.stream().map(booking -> modelMapper.map(booking,BookingDto.class)).toList();
	}
}
