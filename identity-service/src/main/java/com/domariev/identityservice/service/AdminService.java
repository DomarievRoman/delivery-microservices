package com.domariev.identityservice.service;

import com.domariev.identityservice.dto.UserDto;
import com.domariev.identityservice.exception.InvalidUserIdException;
import com.domariev.identityservice.exception.RoleAlreadyGrantedException;
import com.domariev.identityservice.mapper.UserMapper;
import com.domariev.identityservice.model.Role;
import com.domariev.identityservice.model.RoleAuthority;
import com.domariev.identityservice.model.User;
import com.domariev.identityservice.repository.RoleRepository;
import com.domariev.identityservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;

    public UserDto addRole(Long userId, RoleAuthority roleAuthority) {
        User user = findUserEntityById(userId);
        if (user.getRoles().stream().anyMatch(role -> role.getAuthority().equals(roleAuthority))) {
            throw new RoleAlreadyGrantedException(String.format("Role '%s' was already granted to user with id %s", roleAuthority, userId));
        }
        Role authority = roleRepository.findByAuthority(roleAuthority);
        user.getRoles().add(authority);
        userRepository.save(user);
        return userMapper.modelToDto(user);
    }

    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(userMapper::modelToDto)
                .collect(Collectors.toList());
    }

    public UserDto getById(Long userId) {
        User user = findUserEntityById(userId);
        return userMapper.modelToDto(user);
    }

    private User findUserEntityById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new InvalidUserIdException(String.format("User with provided id - %s not found", userId)));
    }
}
