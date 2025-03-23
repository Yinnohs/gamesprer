package com.yinnohs.gamesprer.auth.infrastructure.controller;

import com.yinnohs.gamesprer.auth.application.FindLoggedUserByEmailUseCase;
import com.yinnohs.gamesprer.auth.application.SignUpUserUseCase;
import com.yinnohs.gamesprer.auth.infrastructure.dto.CreateUserRequest;
import com.yinnohs.gamesprer.auth.infrastructure.dto.LoginResponse;
import com.yinnohs.gamesprer.auth.infrastructure.dto.LoginResquest;
import com.yinnohs.gamesprer.auth.infrastructure.service.AuthService;
import com.yinnohs.gamesprer.user.domain.model.User;
import com.yinnohs.gamesprer.user.infrastructure.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final AuthService authService;
    private final AuthenticationManager authenticationManager;
    private final SignUpUserUseCase signUpUserUseCase;
    private final FindLoggedUserByEmailUseCase findLoggedUserByEmailUseCase;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<?> createUser(@RequestBody CreateUserRequest request){
        String hashedPassword = passwordEncoder.encode(request.password());
        User userToCreate = userMapper.createRequestToUser(request);
        userToCreate.setPassword(hashedPassword);
        User user = signUpUserUseCase.apply(userToCreate);

        return ResponseEntity.ok(userMapper.userToResponseDto(user));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login (@RequestBody LoginResquest request){
        var authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()
                ));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        //UserDocument userDetails = (UserDocument) authentication.getPrincipal();

        log.info("Token requested for user {}", authentication.getAuthorities());
        User user = findLoggedUserByEmailUseCase.apply(request.email());
        //token creating and getting user (response creation)
        String token = authService.generateToken(authentication, user.getId());
        String refreshToken = authService.generateRefreshToken(authentication, user.getId());
        LoginResponse response = new LoginResponse(token, userMapper.userToResponseDto(user));

        return ResponseEntity.ok(response);
    }


}
