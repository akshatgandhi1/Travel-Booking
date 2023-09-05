package com.eic.flightservice.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.eic.flightservice.entity.Flight;

public interface FlightRepository extends MongoRepository<Flight, String> {

	List<Flight> findByArrivalAirpot(String arrivalAirpot);

	List<Flight> findByAirlineNameAndArrivalAirpot(String airlineName, String arrivalAirpot);

}
