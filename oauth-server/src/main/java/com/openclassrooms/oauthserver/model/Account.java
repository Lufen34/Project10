package com.openclassrooms.oauthserver.model;

import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import org.springframework.security.core.userdetails.UserDetails;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Account implements UserDetails {

    private static final long serialVersionUID = 5362917154962892330L;
    @MongoId(targetType = FieldType.OBJECT_ID)
    private String id;
    private String name;
    private String address;
    private String login;
    private String email;
    private String password;
    private ERoles role;
    // String = Book ID  | Boolean = can accept
    private Map<String, Boolean> listBooksToAcceptReservations = new HashMap<>();

    public ERoles getRole() {
        return role;
    }

    public void setRole(ERoles role) {
        this.role = role;
    }

    private List<Authorities> authorities = new ArrayList<>();

    public Account() {
    }

    public Account(String name, String address, String login, String password) {
        this.name = name;
        this.address = address;
        this.login = login;
        this.password = password;
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

    @Override
    public List<Authorities> getAuthorities() {
        return authorities;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return getLogin();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Map<String, Boolean> getListBooksToAcceptReservations() {
        return listBooksToAcceptReservations;
    }

    public void setListBooksToAcceptReservations(Map<String, Boolean> listBooksToAcceptReservations) {
        this.listBooksToAcceptReservations = listBooksToAcceptReservations;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", login='" + login + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", authorities=" + authorities +
                '}';
    }
}
