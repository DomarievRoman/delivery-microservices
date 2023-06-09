package com.domariev.identityservice.service;

import com.domariev.identityservice.dto.AuthenticationRequestDto;
import com.domariev.identityservice.dto.AuthenticationResponseDto;
import com.domariev.identityservice.dto.RegistrationRequestDto;
import com.domariev.identityservice.exception.UserAlreadyExistsException;
import com.domariev.identityservice.model.Role;
import com.domariev.identityservice.model.RoleAuthority;
import com.domariev.identityservice.model.User;
import com.domariev.identityservice.repository.RoleRepository;
import com.domariev.identityservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public void register(RegistrationRequestDto registrationRequest) {
        String email = registrationRequest.getEmail();
        Optional<User> userDetails = userRepository.findByEmail(email);
        if (userDetails.isPresent()) {
            throw new UserAlreadyExistsException(String.format("User with email %s is already exists", email));
        }
        User user = new User();
        Role customerRole = roleRepository.findByAuthority(RoleAuthority.CUSTOMER);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
        user.getRoles().add(customerRole);
        userRepository.save(user);
    }

    public AuthenticationResponseDto authenticate(AuthenticationRequestDto authRequest) {
        UserDetails user = userDetailsService.loadUserByUsername(authRequest.getEmail());
        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequest.getEmail(),
                        authRequest.getPassword(),
                        authorities)
        );
        return createAuthenticationResponse(user);
    }

    private AuthenticationResponseDto createAuthenticationResponse(UserDetails user) {
        Collection<String> authorities = user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();
        String token = jwtService.generateToken(Map.of("authorities", authorities), user);
        return AuthenticationResponseDto.builder()
                .token(token)
                .build();
    }
}
