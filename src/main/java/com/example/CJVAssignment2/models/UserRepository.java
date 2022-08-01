package com.example.CJVAssignment2.models;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<UserModel, String> {

    UserModel findByUsername(String username);
//    UserModel findByEmail(String email);
}
