package com.yinnohs.gamesprer.user.infrastructure.out.repository;

import com.yinnohs.gamesprer.user.domain.exception.UserNotFoundException;
import com.yinnohs.gamesprer.user.domain.model.User;
import com.yinnohs.gamesprer.user.domain.port.out.UserRepository;
import com.yinnohs.gamesprer.user.infrastructure.document.UserDocument;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserMongoRepository implements UserRepository {

    private final UserMongoDbRepository userMongoRepository;


    @Override
    public List<User> findAll() {
        List<UserDocument> users = userMongoRepository.findAll();
        return users.stream().map(UserDocument::toUserModel).toList();
    }

    @Override
    public User findById(String userId) {
        UserDocument user = userMongoRepository.findById(userId)
                .orElseThrow(()-> new UserNotFoundException("Could not be found user with id: " + userId));

        return  UserDocument.toUserModel(user);
    }

    @Override
    public User save(User user) {
        var savedUser = userMongoRepository.save(UserDocument.fromUserModel(user));
        return UserDocument.toUserModel(savedUser);
    }

    @Override
    public Boolean deleteById(String userId) {
        userMongoRepository.findById(userId)
                .orElseThrow(()-> new UserNotFoundException("Could not be found user with id: " + userId));
        userMongoRepository.deleteById(userId);
        return true;
    }

    @Override
    public User findByEmail(String email) {
        UserDocument user = userMongoRepository.findByEmail(email)
                .orElseThrow(()-> new UserNotFoundException("Could not be found user with email: " + email));

        return  UserDocument.toUserModel(user);
    }

    @Override
    public User findByPhoneNumber(String phoneNumber) {
        UserDocument user = userMongoRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(()-> new UserNotFoundException("Could not be found user with email: " + phoneNumber));

        return  UserDocument.toUserModel(user);
    }
}
