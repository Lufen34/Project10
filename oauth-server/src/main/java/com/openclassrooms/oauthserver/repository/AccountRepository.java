package com.openclassrooms.oauthserver.repository;

import com.openclassrooms.oauthserver.model.Account;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface AccountRepository extends MongoRepository<Account, String> {
    @Query("{name: /?0/i}")
    public Account findByNameLike(String name);
    public Account findByName(String name);
    public Account findByLogin(String login);
    public Account findByEmail(String email);
}
