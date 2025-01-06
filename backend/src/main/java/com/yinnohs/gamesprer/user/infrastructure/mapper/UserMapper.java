package com.yinnohs.gamesprer.user.infrastructure.mapper;

import com.yinnohs.gamesprer.auth.infrastructure.dto.CreateUserRequest;
import com.yinnohs.gamesprer.user.domain.model.User;
import com.yinnohs.gamesprer.user.infrastructure.dto.UserResponseDto;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {

    public  User createRequestToUser(CreateUserRequest createUserDto){
        return User.builder()
                .name(createUserDto.name())
                .surname(createUserDto.surname())
                .address(createUserDto.address())
                .email(createUserDto.email())
                .phoneNumber(createUserDto.phoneNumber())
                .build();
    }

    public UserResponseDto userToResponseDto(User user){
        return new UserResponseDto(
                user.getId(),
                user.getName(),
                user.getSurname(),
                user.getEmail(),
                user.getAddress(),
                user.getPhoneNumber()
        );
    }
}
