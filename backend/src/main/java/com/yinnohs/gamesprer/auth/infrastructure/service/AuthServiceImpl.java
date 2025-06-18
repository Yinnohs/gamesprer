package com.yinnohs.gamesprer.auth.infrastructure.service;

import com.yinnohs.gamesprer.auth.infrastructure.dto.LoginResponse;
import com.yinnohs.gamesprer.user.domain.port.out.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;


@Service
@Slf4j
@RequiredArgsConstructor
public class AuthServiceImpl{

    private final JwtEncoder jwtEncoder;
    @SuppressWarnings("unused")
    private final PasswordEncoder passwordEncoder;
    @SuppressWarnings("unused")
    private final UserRepository userRepository;

     

    public String generateToken(Authentication authentication, String userId){
        var now = Instant.now();
        var expiresAt = now.plus(1, ChronoUnit.DAYS);
        var claims = getClaims("gamesprer", authentication.getName(), userId, now, expiresAt);
        return  jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    public String generateRefreshToken(Authentication authentication, String userId){
        var now = Instant.now();
        var expiresAt = now.plus(7, ChronoUnit.DAYS);
        var claims = getClaims("gamesprer", authentication.getName(), userId, now, expiresAt);
        return  jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    private JwtClaimsSet getClaims(String issuer, String name, String userId, Instant issueInstant, Instant expiresAt){
        return JwtClaimsSet.builder()
                .issuer(issuer)
                .issuedAt(issueInstant)
                .subject(name)
                .expiresAt(expiresAt)
                .claim("userId", userId )
                .build();

    }

   
}
