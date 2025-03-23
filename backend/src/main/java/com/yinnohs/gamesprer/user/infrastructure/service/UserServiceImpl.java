package com.yinnohs.gamesprer.user.infrastructure.service;

import com.yinnohs.gamesprer.shared.exception.NotSupportedFindType;
import com.yinnohs.gamesprer.user.domain.model.User;
import com.yinnohs.gamesprer.user.domain.port.in.UserService;
import com.yinnohs.gamesprer.user.domain.port.out.UserRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final String TYPE_FIND_BY_ID = "id";
    private final String TYPE_FIND_BY_EMAIL = "email";
    private final String TYPE_FIND_BY_PHONE_NUMBER = "phone";

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User create(User user){
        return userRepository.save(user);
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User findBy(String findType , String value){

        if (findType.equals(TYPE_FIND_BY_ID)) return userRepository.findById(value);
        if (findType.equals(TYPE_FIND_BY_EMAIL)) return userRepository.findByEmail(value);
        if (findType.equals(TYPE_FIND_BY_PHONE_NUMBER)) return userRepository.findByPhoneNumber(value);
        throw new NotSupportedFindType("Find type not supported please try to find by id, email or phone");

    }

    public void deleteUserById(String userid){
        userRepository.deleteById(userid);
    }

    public User updateUserEmail(String userid, String newEmail){
        User userToUpdate = userRepository.findById(userid);
        userToUpdate.setEmail(newEmail);
        return userRepository.save(userToUpdate);
    }

    public User updateUserAddress(String userid, String address){
        User userToUpdate = userRepository.findById(userid);
        userToUpdate.setAddress(address);
        return userRepository.save(userToUpdate);
    }

    public User updateUserPhoneNumber(String userid, String phoneNumber){
        User userToUpdate = userRepository.findById(userid);
        userToUpdate.setPhoneNumber(phoneNumber);
        return userRepository.save(userToUpdate);
    }

    @Override
    public void updateRefreshToken(User user) {
       userRepository.save(user);
    }
}
