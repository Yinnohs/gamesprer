package com.yinnohs.gamesprer.auth.infrastructure.configuration;

import com.yinnohs.gamesprer.auth.application.FindLoggedUserByEmailUseCase;
import com.yinnohs.gamesprer.auth.application.SignUpUserUseCase;
import com.yinnohs.gamesprer.user.domain.port.in.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthBeanConfiguration {

    @Bean
    public FindLoggedUserByEmailUseCase getUserByEmailUseCase(UserService userService){
        return new FindLoggedUserByEmailUseCase(userService);
    }

    @Bean
    public SignUpUserUseCase singUpUserUseCase(UserService userService){
        return new SignUpUserUseCase(userService);
    }
}
