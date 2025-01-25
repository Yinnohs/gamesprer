package com.yinnohs.gamesprer.auth.infrastructure.service;

import com.yinnohs.gamesprer.user.domain.port.out.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthService {

    private final JwtEncoder jwtEncoder;
    @SuppressWarnings("unused")
    private final PasswordEncoder passwordEncoder;
    @SuppressWarnings("unused")
    private final UserRepository userRepository;

     

    public String generateToken(Authentication authentication){
        String scope = getScopeFromAuthentication(authentication);
        var claims = getClaims("gamesprer", authentication.getName(), scope);
        return  jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    private String getScopeFromAuthentication(Authentication authentication){
        return  authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));
    }

    private JwtClaimsSet getClaims(String issuer, String name, String scope){
        var now = Instant.now();
        return JwtClaimsSet.builder()
                .issuer(issuer)
                .issuedAt(now)
                .subject(name)
                .expiresAt(now.plus(1, ChronoUnit.DAYS))
                .claim("scope", scope )
                .build();

    }
}
