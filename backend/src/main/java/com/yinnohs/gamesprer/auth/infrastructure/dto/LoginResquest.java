package com.yinnohs.gamesprer.auth.infrastructure.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

@Valid
public record LoginResquest(
        @NotEmpty
        @Email
        String email,
        @NotEmpty
        String password

) {
}
