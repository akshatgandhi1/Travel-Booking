package com.eic.flightservice.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eic.flightservice.dto.FlightDto;
import com.eic.flightservice.entity.Flight;
import com.eic.flightservice.repository.FlightRepository;

@Service
public class FlightServiceImpl implements FlightService{

	@Autowired
	private FlightRepository flightRepository;
	
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public FlightDto createFlight(FlightDto dto) {
		
		Flight flight=mapper.map(dto, Flight.class); 
		Flight flight2=flightRepository.save(flight);
		
		FlightDto flightDto=mapper.map(flight2, FlightDto.class);
		return flightDto;
	}

	@Override
	public List<FlightDto> getAllFlight() {
		List<Flight> flight=flightRepository.findAll();
		return flight.stream().map((flights)->mapper.map(flights, FlightDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public List<FlightDto> getFlightByPlaceName(String arrivalAirpot) {
		List<Flight> flight=flightRepository.findByArrivalAirpot(arrivalAirpot);
		return flight.stream().map( (flights) -> mapper.map(flights, FlightDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public List<FlightDto> getFlightByAirlineNameAndPlaceName(String airlineName, String arrivalAirpot) {
		List<Flight> flight=flightRepository.findByAirlineNameAndArrivalAirpot(airlineName,arrivalAirpot);
		return flight.stream().map((flights)->mapper.map(flights, FlightDto.class))
				.collect(Collectors.toList());
	}

	public FlightDto getFlightById(String flightId) {
		Flight flight=flightRepository.findById(flightId).get();
		return mapper.map(flight, FlightDto.class);
	}

	
}
