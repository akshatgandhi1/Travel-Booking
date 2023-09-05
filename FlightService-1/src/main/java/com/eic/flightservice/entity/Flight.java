package com.eic.flightservice.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Flight {

	@Id
	private String flightId;
	@NotNull(message = "NameMust not be empty")
	private String airlineName;
	@NotNull(message = "Depature Airpot must be Given properly")
	private String depatureAirpot;
	@NotNull(message = "Arrival Airpot must be Given properly")
	private String arrivalAirpot;
	@NotNull(message = "Depature Time must be Given properly")
	private String depatureTime;
	@NotNull(message = "Arrival Time must be Given properly")
	private String arrivalTime;
	@NotNull(message = "Price must be present")
	private String price;
	private String seatsAvailable;
	
}
