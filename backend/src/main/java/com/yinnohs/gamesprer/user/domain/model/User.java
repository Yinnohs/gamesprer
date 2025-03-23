package com.yinnohs.gamesprer.user.domain.model;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class User {
    private String id;
    private String name;
    private String surname;
    private String email;
    private String address;
    private String phoneNumber;
    private String password;
    private String refreshToken;

    private LocalDateTime createdAt;
    private LocalDateTime lastLoginAt;
    private LocalDateTime lastUpdate;
}
