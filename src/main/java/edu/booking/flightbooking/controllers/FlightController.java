package edu.booking.flightbooking.controllers;

import edu.booking.flightbooking.dto.FlightDto;
import edu.booking.flightbooking.exceptions.ApiException;
import edu.booking.flightbooking.services.FlightService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@AllArgsConstructor
public class FlightController {

    private static final String FLIGHT_PATH = "/flight";
    private static final String FLIGHT_PATH_ID = FLIGHT_PATH + "/{flightId}";

    private final FlightService flightService;

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping(FLIGHT_PATH)
    public List<FlightDto> flightList(){
        return flightService.flightList();
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping(FLIGHT_PATH_ID)
    public FlightDto getFlightById(@PathVariable("flightId") Long flightId){
        return flightService.getFlightById(flightId)
                .orElseThrow(() -> new ApiException(HttpStatus.BAD_REQUEST, "There is no flight with that ID!"));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(FLIGHT_PATH)
    public ResponseEntity handlePost(@RequestBody FlightDto flight){
        FlightDto savedFlight = flightService.createNewFlight(flight);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", FLIGHT_PATH + "/" + savedFlight.getId().toString());

        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(FLIGHT_PATH_ID)
    public ResponseEntity updateFlightById(@PathVariable("flightId") Long flightId, @RequestBody FlightDto flight){

        if (flightService.updateFlight(flightId, flight).isEmpty()){
            throw new ApiException(HttpStatus.BAD_REQUEST, "There is no flight with that ID!");
        };
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(FLIGHT_PATH_ID)
    public ResponseEntity deleteById(@PathVariable("flightId") Long flightId){

        if (!flightService.deleteFlight(flightId)){
            throw new ApiException(HttpStatus.BAD_REQUEST, "There is no flight with that ID!");
        };

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
