package edu.booking.flightbooking.repositories;

import edu.booking.flightbooking.entities.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<Flight, Long> {
}
