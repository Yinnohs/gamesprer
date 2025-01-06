package com.yinnohs.gamesprer.user.domain.port.out;

import com.yinnohs.gamesprer.user.domain.model.User;

import java.util.List;

public interface UserRepository {
    List<User> findAll();
    User findById(final String userId);
    User save(final User user);
    Boolean deleteById(String userId);
    User findByEmail(String email);
    User findByPhoneNumber(String phoneNumber);
}
