package com.domariev.identityservice.api;

import com.domariev.identityservice.dto.AuthenticationRequestDto;
import com.domariev.identityservice.dto.AuthenticationResponseDto;
import com.domariev.identityservice.dto.RegistrationRequestDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface AuthenticationApi {

    @PostMapping(value = "/new", produces = {"application/json"})
    ResponseEntity<String> registerUser(@RequestBody @Valid RegistrationRequestDto registrationRequest);

    @PostMapping(produces = {"application/json"})
    ResponseEntity<AuthenticationResponseDto> authenticateUser(@RequestBody AuthenticationRequestDto authRequest);

}
