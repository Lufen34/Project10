package com.openclassroom.bookservice.Repository;

import com.openclassroom.bookservice.Model.Authorities;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface AuthoritiesRepository extends MongoRepository<Authorities, String> {
    
}