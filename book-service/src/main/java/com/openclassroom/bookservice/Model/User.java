package com.openclassroom.bookservice.Model;

import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import com.openclassroom.bookservice.Model.Authorities;

public class User implements UserDetails {

    private static final long serialVersionUID = 5362917154962892330L;
    @MongoId(targetType = FieldType.OBJECT_ID)
    private String id;
    private String name;
    private String address;
    private String login;
    private String password;

    private List<Authorities> authorities = new ArrayList<>();

    public User() {
    }

    public User(String name, String address, String login, String password, List<Authorities> authorities) {
        this.name = name;
        this.address = address;
        this.login = login;
        this.password = password;
        this.authorities = authorities;
    }

     // THIS IS THE PROBLEM
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO Auto-generated method stub
        return authorities;
    }

    @Override
    public String getUsername() {
        // TODO Auto-generated method stub
        return getLogin();
    }

    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return true;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public void setAuthorities(List<Authorities> authorities) {
        this.authorities = authorities;
    }
}