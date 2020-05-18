package com.openclassroom.bookservice.Service;

import com.openclassroom.bookservice.Model.Authorities;
import com.openclassroom.bookservice.Repository.AuthoritiesRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthoritiesService {

    @Autowired
    private AuthoritiesRepository repoAuthorities;

    public void save(Authorities auth){
        repoAuthorities.save(auth);
    }
    public void delete(Authorities auth){
        repoAuthorities.delete(auth);
    }
}