package com.yinnohs.gamesprer.user.infrastructure.configuration;

import com.yinnohs.gamesprer.user.application.*;
import com.yinnohs.gamesprer.user.domain.port.in.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserDomainBeansConfiguration {

    @Bean
    public DeleteUserUseCase deleteUserUseCase(UserService userService){
        return new DeleteUserUseCase(userService);
    }

    @Bean
    public FindAllUsersUseCase findAllUsersUseCase(UserService userService){
        return new FindAllUsersUseCase(userService);
    }

    @Bean
    public FindSpecificUserUseCase findSpecificUserUseCase(UserService userService){
        return new FindSpecificUserUseCase(userService);
    }

    @Bean
    public UserChangeAddressUseCase userChangeAddressUseCase(UserService userService){
        return new UserChangeAddressUseCase(userService);
    }

    @Bean
    public UserChangeEmailUseCase userChangeEmailUseCase(UserService userService){
        return new UserChangeEmailUseCase(userService);
    }

    @Bean
    public UserChangePhoneNumberUseCase userChangePhoneNumberUseCase(UserService userService){
        return new UserChangePhoneNumberUseCase(userService);
    }

}
