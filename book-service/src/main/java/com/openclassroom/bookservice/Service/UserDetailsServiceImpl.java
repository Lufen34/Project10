package com.openclassroom.bookservice.Service;

import java.util.Arrays;
import java.util.List;

import com.openclassroom.bookservice.Model.Authorities;
import com.openclassroom.bookservice.Model.ERoles;
import com.openclassroom.bookservice.Model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String account_name) throws UsernameNotFoundException {
    	
    	User user = userService.findByLogin(account_name);
        
        if (user == null) {
            throw new UsernameNotFoundException("Unable to find user : " + account_name);
        }
        return user;
    }
}