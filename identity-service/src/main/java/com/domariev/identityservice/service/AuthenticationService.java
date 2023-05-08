package com.domariev.identityservice.service;

import com.domariev.identityservice.dto.AuthenticationRequestDto;
import com.domariev.identityservice.dto.AuthenticationResponseDto;
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

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public void register(AuthenticationRequestDto authRequest) {
        User user = new User();
        Role customerRole = roleRepository.findByAuthority(RoleAuthority.CUSTOMER);
        user.setEmail(authRequest.getEmail());
        user.setPassword(passwordEncoder.encode(authRequest.getPassword()));
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
        String token = jwtService.generateToken(Map.of("authorities", user.getAuthorities()), user);
        return AuthenticationResponseDto.builder()
                .token(token)
                .build();
    }
}
