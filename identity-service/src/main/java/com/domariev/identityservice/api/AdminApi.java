package com.domariev.identityservice.api;

import com.domariev.identityservice.dto.UserDto;
import com.domariev.identityservice.model.RoleAuthority;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface AdminApi {

    @PostMapping(value = "/grant/role", produces = {"application/json"})
    ResponseEntity<UserDto> grantUserRole(@RequestParam Long userId, @RequestParam RoleAuthority authority);

    @GetMapping(value = "/users/all", produces = {"application/json"})
    ResponseEntity<List<UserDto>> retrieveAllUsers();


    @GetMapping(value = "/user/{userId}", produces = {"application/json"})
    ResponseEntity<UserDto> retrieveUserById(@PathVariable Long userId);
}
