package com.yinnohs.gamesprer.auth.infrastructure.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class Argon2Configuration {

    @Value("${argon2.memory}")
    private int memory;

    @Value("${argon2.iterations}")
    private int iterations;

    @Value("${argon2.parallelism}")
    private int parallelism;

    @Value("${argon2.saltLength}")
    private int saltLength;


    @Bean
    public PasswordEncoder passwordEncoder(){
        int hashLength = 128;

        return new Argon2PasswordEncoder(saltLength, hashLength, parallelism, memory, iterations);
    }
}
