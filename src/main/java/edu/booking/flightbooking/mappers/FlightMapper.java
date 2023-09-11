package edu.booking.flightbooking.mappers;

import edu.booking.flightbooking.dto.FlightDto;
import edu.booking.flightbooking.entities.Flight;
import org.mapstruct.Mapper;

@Mapper
public interface FlightMapper {
    Flight flightDtoToFlight(FlightDto flightDto);

    FlightDto flightToFlightDto(Flight flight);
}
