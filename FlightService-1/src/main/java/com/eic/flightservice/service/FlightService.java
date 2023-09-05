package com.eic.flightservice.service;

import java.util.List;

import com.eic.flightservice.dto.FlightDto;

public interface FlightService {

	FlightDto createFlight(FlightDto dto);
	
	List<FlightDto> getAllFlight();
	
	List<FlightDto> getFlightByPlaceName(String arrivalAirpot);
	
	List<FlightDto> getFlightByAirlineNameAndPlaceName(String airlineName,String arrivalAirpot);
	
	FlightDto  getFlightById(String flightId);
}
