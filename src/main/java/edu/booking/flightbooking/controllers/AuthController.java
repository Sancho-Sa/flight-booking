package edu.booking.flightbooking.controllers;

import edu.booking.flightbooking.dto.JwtAuthResponseDto;
import edu.booking.flightbooking.dto.LoginDto;
import edu.booking.flightbooking.dto.RegisterDto;
import edu.booking.flightbooking.services.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@AllArgsConstructor
public class AuthController {

    private AuthService authService;

    @PostMapping("/")
    public ResponseEntity<JwtAuthResponseDto> login(@RequestBody LoginDto loginDto){

        String token = authService.login(loginDto);

        JwtAuthResponseDto authResponseDto = new JwtAuthResponseDto();
        authResponseDto.setAccessToken(token);

        return new ResponseEntity<>(authResponseDto, HttpStatus.OK);
    }

    @PostMapping("/registration")
    public ResponseEntity<String> registration(@RequestBody RegisterDto registerDto){

        String response = authService.register(registerDto);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
