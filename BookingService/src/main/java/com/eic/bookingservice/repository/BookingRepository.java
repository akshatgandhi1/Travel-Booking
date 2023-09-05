package com.eic.bookingservice.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.eic.bookingservice.entity.Booking;

public interface BookingRepository extends MongoRepository<Booking, String> {

	List<Booking> findByUserid(String userid);

}
