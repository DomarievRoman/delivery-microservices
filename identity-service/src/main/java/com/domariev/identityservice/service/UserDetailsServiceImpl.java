package com.domariev.identityservice.service;


import com.domariev.identityservice.dto.PaymentMethodDto;
import com.domariev.identityservice.dto.PersonalInformationDto;
import com.domariev.identityservice.dto.UserDto;
import com.domariev.identityservice.dto.UserPaymentInfoDto;
import com.domariev.identityservice.exception.InvalidUserIdException;
import com.domariev.identityservice.mapper.PaymentMethodMapper;
import com.domariev.identityservice.mapper.UserMapper;
import com.domariev.identityservice.model.PaymentMethod;
import com.domariev.identityservice.model.User;
import com.domariev.identityservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final PaymentMethodMapper paymentMethodMapper;

    @Override
    public UserDetails loadUserByUsername(String username) {
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User with email '%s' not found", username)));
    }

    public UserDto setPersonalInformation(Long userId, PersonalInformationDto personalInformationDto) {
        User user = findUserEntityById(userId);
        user.setName(personalInformationDto.getName());
        user.setSurname(personalInformationDto.getSurname());
        userRepository.save(user);
        return userMapper.modelToDto(user);
    }

    public UserDto getUserInformation(Long userId) {
        User user = findUserEntityById(userId);
        return userMapper.modelToDto(user);
    }

    public User findUserEntityById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new InvalidUserIdException(String.format("User with provided id - %s not found", userId)));
    }

    public void addPaymentMethod(Long userId, PaymentMethod paymentMethod) {
        User user = findUserEntityById(userId);
        user.getPaymentMethods().add(paymentMethod);
        userRepository.save(user);
    }

    public UserPaymentInfoDto getUserWithPaymentMethodsInformation(Long userId) {
        User user = findUserEntityById(userId);
        UserDto userDto = userMapper.modelToDto(user);
        List<PaymentMethodDto> paymentMethodDtoList = user.getPaymentMethods().stream()
                .map(paymentMethodMapper::modelToDto)
                .toList();
        return UserPaymentInfoDto.builder()
                .userDto(userDto)
                .paymentMethodDtoList(paymentMethodDtoList)
                .build();
    }
}
