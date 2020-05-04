package com.openclassroom.bookservice.Repository;

import com.openclassroom.bookservice.Model.User;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    
}