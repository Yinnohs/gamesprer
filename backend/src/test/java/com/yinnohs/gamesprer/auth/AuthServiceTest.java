package com.yinnohs.gamesprer.auth;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;

import com.yinnohs.gamesprer.auth.infrastructure.service.AuthService;
import com.yinnohs.gamesprer.user.domain.model.User;
import com.yinnohs.gamesprer.user.domain.port.out.UserRepository;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @Mock
    private JwtEncoder jwtEncoder;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AuthService authService;

    private Authentication authentication;
    @SuppressWarnings("unused")
    private User testUser;
    private Jwt mockJwt;

    @BeforeEach
    void setUp() {
        authentication = mock(Authentication.class);
        testUser = User.builder()
            .id("1")
            .name("Test")
            .surname("User")
            .email("test@example.com")
            .build();

        mockJwt = mock(Jwt.class);
    }

    @Test
    void when_generating_token_should_success() {
        // Given
        String userId = "1";
        when(authentication.getName()).thenReturn("testuser");
        when(mockJwt.getTokenValue()).thenReturn("mock.jwt.token");
        when(jwtEncoder.encode(any(JwtEncoderParameters.class))).thenReturn(mockJwt);

        // When
        String token = authService.generateToken(authentication, userId);

        // Then
        assertNotNull(token);
        assertEquals("mock.jwt.token", token);
        verify(jwtEncoder).encode(any(JwtEncoderParameters.class));
    }

    @Test
    void when_generating_refresh_token_should_success() {
        // Given
        String userId = "1";
        when(authentication.getName()).thenReturn("testuser");
        when(mockJwt.getTokenValue()).thenReturn("mock.jwt.token");
        when(jwtEncoder.encode(any(JwtEncoderParameters.class))).thenReturn(mockJwt);

        // When
        String refreshToken = authService.generateRefreshToken(authentication, userId);

        // Then
        assertNotNull(refreshToken);
        assertEquals("mock.jwt.token", refreshToken);
        verify(jwtEncoder).encode(any(JwtEncoderParameters.class));
    }

    @Test
    void when_generating_token_with_different_expiration_times() {
        // Given
        String userId = "1";
        when(authentication.getName()).thenReturn("testuser");
        when(mockJwt.getTokenValue()).thenReturn("mock.jwt.token");
        when(jwtEncoder.encode(any(JwtEncoderParameters.class))).thenReturn(mockJwt);

        // When
        String accessToken = authService.generateToken(authentication, userId);
        String refreshToken = authService.generateRefreshToken(authentication, userId);

        // Then
        assertNotNull(accessToken);
        assertNotNull(refreshToken);
        verify(jwtEncoder, times(2)).encode(any(JwtEncoderParameters.class));
    }

    @Test
    void when_generating_token_with_null_authentication_throws_exception() {
        // When & Then
        assertThrows(NullPointerException.class, () -> 
            authService.generateToken(null, "1")
        );
    }

    @Test
    void when_generating_token_with_null_user_id_throws_exception() {
        // When & Then
        assertThrows(IllegalArgumentException.class, () -> 
            authService.generateToken(authentication, null)
        );
    }
} 