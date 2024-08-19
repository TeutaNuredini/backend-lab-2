package com.weppapp_be.teuta_qendresa.mapper;

import com.weppapp_be.teuta_qendresa.dto.UserDto;
import com.weppapp_be.teuta_qendresa.dto.request.UserRequest;
import com.weppapp_be.teuta_qendresa.entity.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper implements GenericMapper<User, UserDto, UserRequest>{

    private final ModelMapper modelMapper;

    @Override
    public UserDto toDto(User entity) {
        return modelMapper.map(entity, UserDto.class);
    }

    @Override
    public User toEntity(UserRequest request) {
        return modelMapper.map(request, User.class);
    }
}
