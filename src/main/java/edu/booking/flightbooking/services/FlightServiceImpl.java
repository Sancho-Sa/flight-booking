package edu.booking.flightbooking.services;

import edu.booking.flightbooking.dto.FlightDto;
import edu.booking.flightbooking.mappers.FlightMapper;
import edu.booking.flightbooking.repositories.FlightRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FlightServiceImpl implements FlightService {

    private final FlightRepository flightRepository;

    private final FlightMapper flightMapper;

    @Override
    public List<FlightDto> flightList() {
        return flightRepository.findAll()
                .stream()
                .map(flightMapper::flightToFlightDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<FlightDto> getFlightById(Long id) {
        return Optional.ofNullable(flightMapper.flightToFlightDto(flightRepository.findById(id)
                .orElse(null)));
    }

    @Override
    public FlightDto createNewFlight(FlightDto flight) {
        return flightMapper.flightToFlightDto(flightRepository.save(flightMapper.flightDtoToFlight(flight)));
    }

    @Override
    public Optional<FlightDto> updateFlight(Long flightId, FlightDto flight) {
        AtomicReference<Optional<FlightDto>> atomicReference = new AtomicReference<>();

        flightRepository.findById(flightId).ifPresentOrElse(foundFlight -> {
            foundFlight.setDepartureAirport(flight.getDepartureAirport());
            foundFlight.setArrivalAirport(flight.getArrivalAirport());
            foundFlight.setDepartureDate(flight.getDepartureDate());
            foundFlight.setCost(flight.getCost());
            atomicReference.set(Optional.of(flightMapper.flightToFlightDto(flightRepository.save(foundFlight))));
        }, () -> atomicReference.set(Optional.empty()));

        return atomicReference.get();
    }

    @Override
    public Boolean deleteFlight(Long flightId) {
        if (flightRepository.existsById(flightId)){
            flightRepository.deleteById(flightId);
            return true;
        }
        return false;
    }
}
