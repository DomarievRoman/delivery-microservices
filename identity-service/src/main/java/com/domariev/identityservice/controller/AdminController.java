package com.domariev.identityservice.controller;

import com.domariev.identityservice.dto.UserDto;
import com.domariev.identityservice.model.RoleAuthority;
import com.domariev.identityservice.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/management/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @PostMapping("/grant/role")
    public ResponseEntity<UserDto> grantUserRole(@RequestParam Long userId, @RequestParam RoleAuthority authority) {
        return ResponseEntity.ok(adminService.addRole(userId, authority));
    }

    @GetMapping("/users/all")
    public ResponseEntity<List<UserDto>> retrieveAllUsers() {
        return ResponseEntity.ok(adminService.getAllUsers());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<UserDto> retrieveUserById(@PathVariable Long userId) {
        return ResponseEntity.ok(adminService.getById(userId));
    }

}
