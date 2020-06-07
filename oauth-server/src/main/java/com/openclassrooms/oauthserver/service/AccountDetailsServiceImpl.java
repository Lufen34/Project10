package com.openclassrooms.oauthserver.service;
/*
import com.openclassrooms.oauthserver.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AccountDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private AccountService userService;

    @Override
    public UserDetails loadUserByUsername(String account_name) throws UsernameNotFoundException {

        Account user = userService.findByLogin(account_name);
        
        if (user == null) {
            throw new UsernameNotFoundException("Unable to find user : " + account_name);
        }
        return user;
    }
}*/