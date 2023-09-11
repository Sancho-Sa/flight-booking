package edu.booking.flightbooking.services;

import edu.booking.flightbooking.dto.LoginDto;
import edu.booking.flightbooking.dto.RegisterDto;

public interface AuthService {

    String login(LoginDto loginDto);

    String register(RegisterDto registerDto);
}
