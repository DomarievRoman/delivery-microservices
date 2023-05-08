package com.domariev.identityservice.controller;

import com.domariev.identityservice.dto.AuthenticationRequestDto;
import com.domariev.identityservice.dto.AuthenticationResponseDto;
import com.domariev.identityservice.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth/user")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/new")
    public ResponseEntity<String> registerUser(@RequestBody AuthenticationRequestDto authRequest) {
        authenticationService.register(authRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping()
    public ResponseEntity<AuthenticationResponseDto> authenticateUser(@RequestBody AuthenticationRequestDto authRequest) {
        return ResponseEntity.ok(authenticationService.authenticate(authRequest));
    }

}
