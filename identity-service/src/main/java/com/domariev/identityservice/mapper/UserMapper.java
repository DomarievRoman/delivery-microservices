package com.domariev.identityservice.mapper;

import com.domariev.identityservice.dto.UserDto;
import com.domariev.identityservice.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDto modelToDto(User user) {
        return new ModelMapper().map(user, UserDto.class);
    }

}
