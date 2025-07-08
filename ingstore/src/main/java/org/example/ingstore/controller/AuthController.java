package org.example.ingstore.controller;


import org.example.ingstore.dto.ApiResponseDTO;
import org.example.ingstore.dto.JwtResponseDTO;
import org.example.ingstore.dto.LoginRequestDTO;
import org.example.ingstore.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.example.ingstore.utils.AppConstants.*;


@RestController
@RequestMapping(AUTH_BASE_PATH)
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }

    // POST: /api/auth/login
    @PostMapping(LOGIN_PATH)
    public ResponseEntity<ApiResponseDTO<JwtResponseDTO>> authenticateUser(@RequestBody LoginRequestDTO loginRequestDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDTO.getUsername(), loginRequestDTO.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Generate JWT token
        String jwt = jwtUtils.generateJwtToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        // Build response with JWT and user details
        JwtResponseDTO jwtResponse = new JwtResponseDTO(jwt, userDetails.getUsername(), userDetails.getAuthorities());

        ApiResponseDTO<JwtResponseDTO> response = new ApiResponseDTO<>(jwtResponse, LOGIN_SUCCESS_MESSAGE);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(LOGOUT_PATH)
    public ResponseEntity<ApiResponseDTO<String>> logoutUser() {
        // Clear the security context to log out
        SecurityContextHolder.clearContext();

        ApiResponseDTO<String> response = new ApiResponseDTO<>(null, LOGOUT_SUCCESS_MESSAGE);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
