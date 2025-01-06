package com.yinnohs.gamesprer.user.domain.port.in;

import com.yinnohs.gamesprer.user.domain.model.User;

import java.util.List;

public interface UserService {
    public User create(User user);
    public List<User> findAll();
    public User findBy(String findType , String value);
    public void deleteUserById(String userid);
    public User updateUserEmail(String userid, String newEmail);
    public User updateUserAddress(String userid, String address);
    public User updateUserPhoneNumber(String userid, String phoneNumber);
}
