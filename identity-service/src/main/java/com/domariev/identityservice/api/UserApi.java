package com.domariev.identityservice.api;

import com.domariev.identityservice.dto.PersonalInformationDto;
import com.domariev.identityservice.dto.UserDto;
import com.domariev.identityservice.dto.UserPaymentInfoDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface UserApi {

    @PostMapping(value = "/settings/profile", produces = {"application/json"})
    ResponseEntity<UserDto> providePersonalInformation(@RequestBody PersonalInformationDto personalInformationDto);

    @GetMapping(value = "/settings/profile/general", produces = {"application/json"})
    ResponseEntity<UserDto> getUser();

    @GetMapping(produces = {"application/json"})
    ResponseEntity<UserPaymentInfoDto> getUserPaymentMethodsInformation();

}
