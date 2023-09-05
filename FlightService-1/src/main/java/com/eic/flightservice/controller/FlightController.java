package com.eic.flightservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.eic.flightservice.dto.FlightDto;
import com.eic.flightservice.service.FlightService;
import com.eic.flightservice.service.FlightServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/flight")
public class FlightController {

	@Autowired
	private FlightService flightService;
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public FlightDto createFlight(@Valid @RequestBody FlightDto dto) {
		return flightService.createFlight(dto);
	}
	
	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public List<FlightDto> getAllFLightDetail() {
		return flightService.getAllFlight();
	}
	
	@GetMapping("/filter/{arrivalAirpot}")
	public List<FlightDto> getFlightByPlaceName(@PathVariable("arrivalAirpot") String arrivalAirpot) {
		return flightService.getFlightByPlaceName(arrivalAirpot);
	}
	
	@GetMapping("/{airlineName}/{arrivalAirpot}")
	public List<FlightDto> getFlightByFlightNameOrPlaceName(@PathVariable("airlineName") String airlineName,@PathVariable("arrivalAirpot") String arrivalAirpot) {
		return flightService.getFlightByAirlineNameAndPlaceName(airlineName, arrivalAirpot);
	}
	
	@GetMapping("/{flightId}")
	public FlightDto getFlightById(@PathVariable("flightId") String flightId) {
		return flightService.getFlightById(flightId);
	}
	
	
}
