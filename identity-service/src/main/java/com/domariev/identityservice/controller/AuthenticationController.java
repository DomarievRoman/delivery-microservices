package com.domariev.identityservice.controller;

import com.domariev.identityservice.api.AuthenticationApi;
import com.domariev.identityservice.dto.AuthenticationRequestDto;
import com.domariev.identityservice.dto.AuthenticationResponseDto;
import com.domariev.identityservice.dto.RegistrationRequestDto;
import com.domariev.identityservice.service.AuthenticationService;
import com.domariev.identityservice.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/auth/user")
@RequiredArgsConstructor
public class AuthenticationController implements AuthenticationApi {

    private final AuthenticationService authenticationService;
    private final JwtService jwtService;

    @Override
    public ResponseEntity<String> registerUser(RegistrationRequestDto registrationRequest) {
        authenticationService.register(registrationRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<AuthenticationResponseDto> authenticateUser(AuthenticationRequestDto authRequest) {
        return ResponseEntity.ok(authenticationService.authenticate(authRequest));
    }

    @Override
    public ResponseEntity<Boolean> validateToken(String token) {
        return ResponseEntity.ok(jwtService.isTokenValid(token));
    }

    @Override
    public ResponseEntity<List<String>> retrieveUserAuthorities(String token) {
        return ResponseEntity.ok(jwtService.extractAuthorities(token));
    }

}
