package com.yinnohs.gamesprer.user.infrastructure.document;



import com.yinnohs.gamesprer.user.domain.model.User;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Valid
@Document("user")
public class UserDocument implements UserDetails {

    @Id
    private String id;
    private String name;
    private String surname;
    @NotNull
    @Email
    @Indexed(unique = true)
    private String email;
    private String address;
    @NotNull
    @Indexed(unique = true)
    private String phoneNumber;
    @NotNull
    private String password;

    private LocalDateTime createdAt;
    private LocalDateTime lastLoginAt;
    private LocalDateTime lastUpdate;

    public static UserDocument fromUserModel(User user){
        return UserDocument.builder()
                .id(user.getId())
                .email(user.getEmail())
                .address(user.getAddress())
                .name(user.getName())
                .surname(user.getSurname())
                .phoneNumber(user.getPhoneNumber())
                .createdAt(user.getCreatedAt())
                .lastUpdate(user.getLastUpdate())
                .lastLoginAt(user.getLastLoginAt())
                .password(user.getPassword())
                .build();
    }

    public static User toUserModel(UserDocument user){
        return User.builder()
                .id(user.getId())
                .email(user.getEmail())
                .address(user.getAddress())
                .name(user.getName())
                .surname(user.getSurname())
                .phoneNumber(user.getPhoneNumber())
                .createdAt(user.getCreatedAt())
                .lastUpdate(user.getLastUpdate())
                .lastLoginAt(user.getLastLoginAt())
                .password(user.getPassword())
                .build();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
