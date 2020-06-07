package com.openclassroom.bookservice.Repository;

import com.openclassroom.bookservice.Model.User;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface UserRepository extends MongoRepository<User, String> {
    @Query("{name: /?0/i}")
    public User findByNameLike(String name);
    public User findByName(String name);
}