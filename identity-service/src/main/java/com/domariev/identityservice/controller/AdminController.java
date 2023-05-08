package com.domariev.identityservice.controller;

import com.domariev.identityservice.api.AdminApi;
import com.domariev.identityservice.dto.UserDto;
import com.domariev.identityservice.model.RoleAuthority;
import com.domariev.identityservice.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/management/admin")
@RequiredArgsConstructor
public class AdminController implements AdminApi {

    private final AdminService adminService;

    @Override
    public ResponseEntity<UserDto> grantUserRole(Long userId, RoleAuthority authority) {
        return ResponseEntity.ok(adminService.addRole(userId, authority));
    }

    @Override
    public ResponseEntity<List<UserDto>> retrieveAllUsers() {
        return ResponseEntity.ok(adminService.getAllUsers());
    }

    @Override
    public ResponseEntity<UserDto> retrieveUserById(Long userId) {
        return ResponseEntity.ok(adminService.getById(userId));
    }

}
