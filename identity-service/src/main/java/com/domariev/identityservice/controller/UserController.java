package com.domariev.identityservice.controller;

import com.domariev.identityservice.api.UserApi;
import com.domariev.identityservice.dto.PersonalInformationDto;
import com.domariev.identityservice.dto.UserDto;
import com.domariev.identityservice.dto.UserPaymentInfoDto;
import com.domariev.identityservice.model.User;
import com.domariev.identityservice.service.UserDetailsServiceImpl;
import com.domariev.identityservice.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/management/user")
@RequiredArgsConstructor
public class UserController implements UserApi {

    private final UserDetailsServiceImpl userDetailsService;

    @Override
    public ResponseEntity<UserDto> providePersonalInformation(PersonalInformationDto personalInformationDto) {
        User userDetails = SecurityUtils.getAuthenticatedUserDetails();
        return ResponseEntity.ok(userDetailsService.setPersonalInformation(userDetails.getId(), personalInformationDto));
    }

    @Override
    public ResponseEntity<UserDto> getUser() {
        User userDetails = SecurityUtils.getAuthenticatedUserDetails();
        return ResponseEntity.ok(userDetailsService.getUserInformation(userDetails.getId()));
    }

    @Override
    public ResponseEntity<UserPaymentInfoDto> getUserPaymentMethodsInformation() {
        User userDetails = SecurityUtils.getAuthenticatedUserDetails();
        return ResponseEntity.ok(userDetailsService.getUserWithPaymentMethodsInformation(userDetails.getId()));
    }

}
