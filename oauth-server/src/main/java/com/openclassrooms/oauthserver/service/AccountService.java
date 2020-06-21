package com.openclassrooms.oauthserver.service;

import com.openclassrooms.oauthserver.model.Account;
import com.openclassrooms.oauthserver.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    @Autowired
    private AccountRepository repository;

    public void save(Account user){
        repository.save(user);
    }

    public void delete(Account user){
        repository.delete(user);
    }

    public Account findByNameLike(String name) {
        return repository.findByNameLike(name);
    }

    public Account findByName(String name) {
        return repository.findByName(name);
    }
    public Account findByLogin(String login) {
        return repository.findByLogin(login);
    }

    public AccountRepository getRepository() {
        return repository;
    }

}