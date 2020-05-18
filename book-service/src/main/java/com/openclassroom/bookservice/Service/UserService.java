package com.openclassroom.bookservice.Service;

import com.openclassroom.bookservice.Model.User;
import com.openclassroom.bookservice.Repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public void save(User user){
        repository.save(user);
    }

    public void delete(User user){
        repository.delete(user);
    }

    public User findByNameLike(String name) {
        return repository.findByNameLike(name);
    }

    public User findByName(String name) {
        return repository.findByName(name);
    }
    public User findByLogin(String login) {
        return repository.findByLogin(login);
    }
}