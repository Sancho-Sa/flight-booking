package edu.booking.flightbooking.services;

import edu.booking.flightbooking.dto.FlightDto;

import java.util.List;
import java.util.Optional;

public interface FlightService {

    List<FlightDto> flightList();

    Optional<FlightDto> getFlightById(Long id);

    FlightDto createNewFlight(FlightDto flight);

    Optional<FlightDto> updateFlight(Long flightId, FlightDto flight);

    Boolean deleteFlight(Long flightId);
}
