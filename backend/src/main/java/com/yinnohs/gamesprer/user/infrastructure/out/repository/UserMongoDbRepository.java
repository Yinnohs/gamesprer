package com.yinnohs.gamesprer.user.infrastructure.out.repository;

import com.yinnohs.gamesprer.user.infrastructure.document.UserDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserMongoDbRepository extends MongoRepository<UserDocument, String> {
    Optional<UserDocument> findByEmail(String email);
    Optional<UserDocument> findByPhoneNumber(String PhoneNumber);
}
