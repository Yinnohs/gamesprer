package com.yinnohs.gamesprer.user.infrastructure.controller;

import com.yinnohs.gamesprer.user.application.*;
import com.yinnohs.gamesprer.user.infrastructure.dto.UpdateUserAddressRequest;
import com.yinnohs.gamesprer.user.infrastructure.dto.UpdateUserEmailRequest;
import com.yinnohs.gamesprer.user.infrastructure.dto.UpdateUserPhoneNumberRequest;
import com.yinnohs.gamesprer.user.infrastructure.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class UserWebController {

    private final FindAllUsersUseCase findAllUsersUseCase;
    private final FindSpecificUserUseCase findSpecificUserUseCase;
    private final DeleteUserUseCase deleteUserUseCase;
    private final UserChangeEmailUseCase userChangeEmailUseCase;
    private final UserChangePhoneNumberUseCase userChangePhoneNumberUseCase;
    private final UserChangeAddressUseCase userChangeAddressUseCase;
    private final UserMapper userMapper;

    @GetMapping()
    public ResponseEntity<?> findAllUsers(){
        return ResponseEntity.ok(findAllUsersUseCase.apply(null)
                .stream()
                .map(userMapper::userToResponseDto)
                .toList());
    }

    @GetMapping("/find")
    public ResponseEntity<?> findBy(
            @RequestParam(name = "type") String findType,
            @RequestParam(name = "value") String value
    ){
        var user = findSpecificUserUseCase.apply(findType, value);
        return ResponseEntity.ok(userMapper.userToResponseDto(user));
    }

    @DeleteMapping("/{userid}")
    public ResponseEntity<?> deleteUserById(@PathVariable("userid") String userid){
        deleteUserUseCase.apply(userid);
        return ResponseEntity.ok("User Deleted successfully");
    }

    @PutMapping("/phone")
    public ResponseEntity<?> updateUserPhoneNumber(@RequestBody UpdateUserPhoneNumberRequest request){
        var user = userChangePhoneNumberUseCase
                .apply(request.userId(), request.phoneNumber());

        return ResponseEntity.ok(userMapper.userToResponseDto(user));
    }

    @PutMapping("/email")
    public ResponseEntity<?> updateUserEmail(@RequestBody UpdateUserEmailRequest request){
        var user = userChangeEmailUseCase.
        apply(request.userId(), request.email());

        return ResponseEntity.ok(userMapper.userToResponseDto(user));
    }

    @PutMapping("/address")
    public ResponseEntity<?> updateUserAddress(@RequestBody UpdateUserAddressRequest request) {
        var user = userChangeAddressUseCase.apply
                (request.userId(), request.userAddress());

        return ResponseEntity.ok(userMapper.userToResponseDto(user));
    }
}
